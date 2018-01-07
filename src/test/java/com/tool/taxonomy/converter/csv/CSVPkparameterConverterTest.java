package com.tool.taxonomy.converter.csv;

import com.tool.taxonomy.exception.csv.CsvIOException;
import com.tool.taxonomy.model.Pkparameter;
import com.tool.taxonomy.util.ResourceLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(value = {CSVPkparameterConverter.class,
        ResourceLoader.class})
public class CSVPkparameterConverterTest {

    @Autowired
    private CSVPkparameterConverter csvPkparameterConverter;

    @Autowired
    private ResourceLoader resourceLoader;

    private static final long FICTIVE_ID = -1;
    private static final Long FICTIVE_PARENT_ID = null;
    private static final long ANGIOTENSIN_ID = 1;
    private static final long RAMIPRIL_ID = 2;
    private static final long RROSTAGLANDINS_ID = 31;

    private static final String FICTIVE_NAME = null;
    private static final String ANGIOTENSIN = "Angiotensin converting enzyme inhibitors";
    private static final String RAMIPRIL = "Ramipril";
    private static final String RROSTAGLANDINS = "Prostaglandins";
    private static MockMultipartFile csvMultipartFile;
    private Pkparameter pkparameterFictiveRoot;
    @Before
    public void setUp() throws IOException {
        csvMultipartFile = new MockMultipartFile(
                "csvFile",
                "taxonomyFile.csv",
                "text/csv",
                resourceLoader.getResourceAsMultipartFileCsv("csv/taxonomy.csv").getBytes());
        pkparameterFictiveRoot = new Pkparameter(FICTIVE_ID, FICTIVE_PARENT_ID, FICTIVE_NAME);
        final Set<Pkparameter> pkparameterFictiveChildren = new HashSet<>();
        final Pkparameter pkparameterAngiotensin = new Pkparameter(ANGIOTENSIN_ID, FICTIVE_ID, ANGIOTENSIN);
        final Set<Pkparameter> pkparameterAngiotensinChildren = new HashSet<>();

        final Pkparameter pkparameterRamipril = new Pkparameter(RAMIPRIL_ID, ANGIOTENSIN_ID, RAMIPRIL);
        final Pkparameter pkparameterProstaglandins = new Pkparameter(RROSTAGLANDINS_ID, FICTIVE_ID, RROSTAGLANDINS);

        pkparameterFictiveRoot.setParent(null);
        pkparameterFictiveChildren.add(pkparameterAngiotensin);
        pkparameterFictiveChildren.add(pkparameterProstaglandins);
        pkparameterFictiveRoot.setChildren(pkparameterFictiveChildren);

        pkparameterAngiotensin.setParent(pkparameterFictiveRoot);
        pkparameterAngiotensinChildren.add(pkparameterRamipril);
        pkparameterAngiotensin.setChildren(pkparameterAngiotensinChildren);
        pkparameterRamipril.setParent(pkparameterAngiotensin);
        pkparameterProstaglandins.setParent(pkparameterFictiveRoot);
    }

    @Test
    public void testImportTaxonomy() throws IOException, CsvIOException {
        final Pkparameter pkparameterRoot = csvPkparameterConverter.importTaxonomy(csvMultipartFile);
        checkEquality(pkparameterRoot, pkparameterFictiveRoot);
    }

    private void checkEquality(final Pkparameter pkparameterActual, final Pkparameter pkparameterExpected) {
        assertEquals(pkparameterExpected.getName(), pkparameterActual.getName());
        assertEquals(pkparameterExpected.getId(), pkparameterActual.getId());
        assertEquals(pkparameterExpected.getParentId(), pkparameterActual.getParentId());
        final List<Pkparameter> childrenExpected = new ArrayList<>(pkparameterExpected.getChildren());
        final List<Pkparameter> childrenActual = new ArrayList<>(pkparameterActual.getChildren());
        childrenExpected.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : (int) FICTIVE_ID);
        childrenActual.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : (int) FICTIVE_ID);
        assertEquals(childrenExpected.size(), childrenActual.size());
        for (int i = 0; i < childrenExpected.size(); i++) {
            checkEquality(childrenActual.get(i), childrenExpected.get(i));
        }
    }
}
