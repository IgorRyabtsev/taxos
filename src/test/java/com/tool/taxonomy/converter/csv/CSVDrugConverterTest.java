package com.tool.taxonomy.converter.csv;

import com.tool.taxonomy.exception.csv.CsvIOException;
import com.tool.taxonomy.model.Drug;
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
@Import(value = {CSVDrugConverter.class,
        ResourceLoader.class})
public class CSVDrugConverterTest {
    @Autowired
    private CSVDrugConverter csvDrugConverter;

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
    private Drug drugFictiveRoot;
    @Before
    public void setUp() throws IOException {
        csvMultipartFile = new MockMultipartFile(
                "csvFile",
                "taxonomyFile.csv",
                "text/csv",
                resourceLoader.getResourceAsMultipartFileCsv("csv/taxonomy.csv").getBytes());
        drugFictiveRoot = new Drug(FICTIVE_ID, FICTIVE_PARENT_ID, FICTIVE_NAME);
        final Set<Drug> drugFictiveChildren = new HashSet<>();
        final Drug drugAngiotensin = new Drug(ANGIOTENSIN_ID, FICTIVE_ID, ANGIOTENSIN);
        final Set<Drug> drugAngiotensinChildren = new HashSet<>();

        final Drug drugRamipril = new Drug(RAMIPRIL_ID, ANGIOTENSIN_ID, RAMIPRIL);
        final Drug drugProstaglandins = new Drug(RROSTAGLANDINS_ID, FICTIVE_ID, RROSTAGLANDINS);

        drugFictiveRoot.setParent(null);
        drugFictiveChildren.add(drugAngiotensin);
        drugFictiveChildren.add(drugProstaglandins);
        drugFictiveRoot.setChildren(drugFictiveChildren);

        drugAngiotensin.setParent(drugFictiveRoot);
        drugAngiotensinChildren.add(drugRamipril);
        drugAngiotensin.setChildren(drugAngiotensinChildren);
        drugRamipril.setParent(drugAngiotensin);
        drugProstaglandins.setParent(drugFictiveRoot);
    }

    @Test
    public void testImportTaxonomy() throws IOException, CsvIOException {
        final Drug drugsRoot = csvDrugConverter.importTaxonomy(csvMultipartFile);
        checkEquality(drugFictiveRoot, drugsRoot);
    }

    private void checkEquality(final Drug drugActual, final Drug drugExpected) {
        assertEquals(drugExpected.getName(), drugActual.getName());
        assertEquals(drugExpected.getId(), drugActual.getId());
        assertEquals(drugExpected.getParentId(), drugActual.getParentId());
        final List<Drug> childrenExpected = new ArrayList<>(drugExpected.getChildren());
        final List<Drug> childrenActual = new ArrayList<>(drugActual.getChildren());
        childrenExpected.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : -1);
        childrenActual.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : -1);
        assertEquals(childrenExpected.size(), childrenActual.size());
        for (int i = 0; i < childrenExpected.size(); i++) {
            checkEquality(childrenActual.get(i), childrenExpected.get(i));
        }
    }
}
