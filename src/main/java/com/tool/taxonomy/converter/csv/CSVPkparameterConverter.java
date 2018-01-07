package com.tool.taxonomy.converter.csv;

import com.tool.taxonomy.exception.ExceptionMessages;
import com.tool.taxonomy.exception.csv.CsvIOException;
import com.tool.taxonomy.model.Pkparameter;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class CSVPkparameterConverter extends AbstractCSVConverter<Pkparameter> {

    @Override
    public final Pkparameter importTaxonomy(final MultipartFile multipartFile) throws IOException, CsvIOException {
        final List<Pkparameter> taxonomies = new LinkedList<>();
        final CSVReader reader = new CSVReader(new InputStreamReader(multipartFile.getInputStream()));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine.length < NUMBER_OF_COLUMNS)
                throw new CsvIOException(ExceptionMessages.CSV_NOT_ENOUGH_COLUMNS);
            taxonomies.add(new Pkparameter(getId(nextLine[ID]), getId(nextLine[PARENT_ID]), nextLine[NAME]));
        }
        final Pkparameter pkParameterTreeRoot = new Pkparameter(MOCK_ID, MOCK_PARENT_ID, MOCK_NAME);
        taxonomies.add(pkParameterTreeRoot);
        prepareForSaving(taxonomies);
        return pkParameterTreeRoot;
    }

    @Override
    final List<Pkparameter> prepareForSaving(final List<Pkparameter> taxonomies) {
        final Map<Long, Pkparameter> prepareMap = new HashMap<>();
        taxonomies.forEach(t -> prepareMap.put(t.getId(), t));
        for (final Map.Entry<Long, Pkparameter> entry : prepareMap.entrySet()) {
            final Pkparameter pkparameterValue = entry.getValue();
            if (pkparameterValue.getParentId() == null) continue;
            if (!prepareMap.containsKey(pkparameterValue.getParentId()))
                throw new IllegalStateException(ExceptionMessages.NOT_EXIST_NODE);
            final Pkparameter pkparameterParent = prepareMap.get(pkparameterValue.getParentId());
            pkparameterValue.setParent(pkparameterParent);
            final Set<Pkparameter> children = pkparameterParent.getChildren();
            children.add(pkparameterValue);
        }
        taxonomies.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : -1);
        return taxonomies;
    }
}
