package com.tool.taxonomy.converter.csv;

import com.tool.taxonomy.exception.ExceptionMessages;
import com.tool.taxonomy.exception.csv.CsvIOException;
import com.tool.taxonomy.model.Drug;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class CSVDrugConverter extends AbstractCSVConverter<Drug>{

    @Override
    public Drug importTaxonomy(final MultipartFile multipartFile) throws IOException, CsvIOException {
        final List<Drug> taxonomies = new LinkedList<>();
        final CSVReader reader = new CSVReader(new InputStreamReader(multipartFile.getInputStream()));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine.length < NUMBER_OF_COLUMNS)
                throw new CsvIOException(ExceptionMessages.CSV_NOT_ENOUGH_COLUMNS);
            taxonomies.add(new Drug(getId(nextLine[ID]), getId(nextLine[PARENT_ID]), nextLine[NAME]));
        }
        final Drug drugTreeRoot = new Drug(MOCK_ID, MOCK_PARENT_ID, MOCK_NAME);
        taxonomies.add(drugTreeRoot);
        prepareForSaving(taxonomies);
        return drugTreeRoot;
    }

    @Override
    final List<Drug> prepareForSaving(final List<Drug> taxonomies) {
        final Map<Long, Drug> prepareMap = new HashMap<>();
        taxonomies.forEach(t -> prepareMap.put(t.getId(), t));
        for (final Map.Entry<Long, Drug> entry : prepareMap.entrySet()) {
            final Drug drugValue = entry.getValue();
            if (drugValue.getParentId() == null) continue;
            if (!prepareMap.containsKey(drugValue.getParentId()))
                throw new IllegalStateException(ExceptionMessages.NOT_EXIST_NODE);
            final Drug drugParent = prepareMap.get(drugValue.getParentId());
            drugValue.setParent(drugParent);
            final Set<Drug> children = drugParent.getChildren();
            children.add(drugValue);
        }
        taxonomies.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : -1);
        return taxonomies;
    }
}
