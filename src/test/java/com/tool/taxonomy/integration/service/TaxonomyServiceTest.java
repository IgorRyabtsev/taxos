package com.tool.taxonomy.integration.service;

import com.tool.taxonomy.TaxonomyApplication;
import com.tool.taxonomy.model.Research;
import com.tool.taxonomy.model.Taxonomy;
import com.tool.taxonomy.repository.DrugsRepository;
import com.tool.taxonomy.service.TaxonomyService;
import com.tool.taxonomy.util.Filter;
import com.tool.taxonomy.util.Range;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@TestPropertySource(locations = "classpath:/application-test.properties")
@SpringBootTest
@ContextConfiguration(classes = TaxonomyApplication.class)

@WebAppConfiguration
public class TaxonomyServiceTest {
    @Autowired
    private TaxonomyService taxonomyService;

    @Autowired
    private DrugsRepository drugsRepository;

    private static final String INTERFERON = "Interferon Alfa-N3";
    private static final String LAMIVUDINE = "Lamivudine; Stavudine; Nevirapine";
    private static final String ELBASVIR = "Elbasvir; Grazoprevir";
    private static final String ABACAVIR = "Abacavir Sulfate";
    private static final String ANXIOLYTICS = "Anxiolytics";
    private static final String TABLET = "tablet";
    private static final String IN_VYTRO = "in vitro";
    private static final String ORAL = "oral";
    private static final String SOLUTION = "solution";
    private static final String ROUTEONE = "1";
    private static final String ROUTETWO = "2";
    private static final String BIOAVAILABILITY = "Bioavailability";
    private static final String TIME_VALUES = "Time values";
    private static final String FILE_NAME_FIRST = "WC500050162";
    private static final String FILE_NAME_SECOND = "WC500050343";
    private static final String DRUG = "drug";
    private static final String DRUG_PREFIX = "Bqwsd";
    private static final String DRUG_PREFIX_SPEC = "Kamist";
    private static final String PKPARAMETER = "pkparameter";
    private static final String PKPARAMETER_PREFIX = "Reqtss";
    private static final String PKPARAMETER_PREFIX_SPEC = "Quali";

    private static final Long LEFT_RANGE = 6L;
    private static final Long RIGHT_RANGE = 17L;
    private static final Long RIGHT_RANGE_UNIQUE = 8L;
    private static final Long RESEARCH_ID4 = 4L;
    private static final Long RESEARCH_ID5 = 5L;
    private static final Long RESEARCH_ID6 = 6L;
    private static final Double VALUES_LEFT = 0.5;
    private static final Double VALUES_RIGHT = 1.6;
    private static final Double VALUES_RIGHT_NOT_EXISTENT = 0.6;
    private static final int EXPECTED_NUMBER_OF_TAXONOMIES = 10;
    private static final int EXPECTED_NUMBER_OF_TAXONOMIES_SPEC = 2;
    private static final int OFFSET_IN_DATABASE_ID = 1;
    private static final int OFFSET_IN_DATABASE_ID_SPEC = 12;

    private List<String> drugs;
    private List<String> studyNumberOlds;
    private List<String> routes;
    private List<String> comments;
    private Range<Double> values;
    private Range<Double> valuesNotExistent;
    private List<String> fileNames;
    private Range<Long> page;
    private Range<Long> uniquePage;
    private List<String> parameters;

    @Before
    public void setUp() {
        drugs = new ArrayList<>();
        drugs.add(INTERFERON);
        drugs.add(LAMIVUDINE);
        drugs.add(ELBASVIR);
        drugs.add(ABACAVIR);
        drugs.add(ANXIOLYTICS);

        studyNumberOlds = new ArrayList<>();
        studyNumberOlds.add(ROUTEONE);
        studyNumberOlds.add(ROUTETWO);

        routes = new ArrayList<>();
        routes.add(ORAL);
        routes.add(IN_VYTRO);

        comments = new ArrayList<>();
        comments.add(TABLET);
        comments.add(SOLUTION);

        values = new Range<>();
        values.setLeftValue(VALUES_LEFT);
        values.setRightValue(VALUES_RIGHT);

        valuesNotExistent = new Range<>();
        valuesNotExistent.setLeftValue(VALUES_LEFT);
        valuesNotExistent.setRightValue(VALUES_RIGHT_NOT_EXISTENT);

        fileNames = new ArrayList<>();
        fileNames.add(FILE_NAME_FIRST);
        fileNames.add(FILE_NAME_SECOND);

        page = new Range<>();
        page.setLeftValue(LEFT_RANGE);
        page.setRightValue(RIGHT_RANGE);

        uniquePage = new Range<>();
        uniquePage.setLeftValue(LEFT_RANGE);
        uniquePage.setRightValue(RIGHT_RANGE_UNIQUE);

        parameters = new ArrayList<>();
        parameters.add(BIOAVAILABILITY);
        parameters.add(TIME_VALUES);
        parameters.add("Tmax");


    }

    @Test
    @Transactional
    public void getResearchsByFilterUniqueRecord() {
        Filter filter = new Filter();
        filter.setName(drugs);
        filter.setStudyNumberOld(studyNumberOlds);
        filter.setRoute(routes);
        filter.setComment(comments);
        filter.setParameter(parameters);
        filter.setFileName(fileNames);
        filter.setValue(values);
        filter.setPage(uniquePage);
        List<Research> researchsByFilter = taxonomyService.getResearchsByFilter(filter);
        assertEquals(1, researchsByFilter.size());
        assertTrue(researchsByFilter.get(0).getId().equals(RESEARCH_ID5));
    }

    @Test
    @Transactional
    public void getResearchsByFilterWithChildren() {
        Filter filter = new Filter();
        filter.setName(drugs);
        filter.setStudyNumberOld(studyNumberOlds);
        filter.setRoute(routes);
        filter.setComment(comments);
        filter.setParameter(parameters);
        filter.setFileName(fileNames);
        filter.setValue(values);
        filter.setPage(page);
        List<Research> researchsByFilter = taxonomyService.getResearchsByFilter(filter);
        assertEquals(3, researchsByFilter.size());
        researchsByFilter.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : -1);
        assertTrue(researchsByFilter.get(0).getId().equals(RESEARCH_ID4));
        assertTrue(researchsByFilter.get(1).getId().equals(RESEARCH_ID5));
        assertTrue(researchsByFilter.get(2).getId().equals(RESEARCH_ID6));
    }

    @Test
    @Transactional
    public void getResearchsByFilterNoRecords() {
        Filter filter = new Filter();
        filter.setName(drugs);
        filter.setStudyNumberOld(studyNumberOlds);
        filter.setRoute(routes);
        filter.setComment(comments);
        filter.setParameter(parameters);
        filter.setFileName(fileNames);
        filter.setValue(valuesNotExistent);
        List<Research> researchsByFilter = taxonomyService.getResearchsByFilter(filter);
        assertEquals(0, researchsByFilter.size());
    }

    @Test
    @Transactional
    public void getTaxonomiesByPrefixNameDrugTenRecords() {
        List<? extends Taxonomy> taxonomiesByPrefixName = taxonomyService.getTaxonomiesByPrefixName(DRUG, DRUG_PREFIX);
        assertEquals(EXPECTED_NUMBER_OF_TAXONOMIES, taxonomiesByPrefixName.size());
        taxonomiesByPrefixName.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : -1);
        for (int i = 0; i < EXPECTED_NUMBER_OF_TAXONOMIES; i++) {
            assertEquals(i + OFFSET_IN_DATABASE_ID, (long) taxonomiesByPrefixName.get(i).getId());
        }
    }

    @Test
    @Transactional
    public void getTaxonomiesByPrefixNameDrug() {
        List<? extends Taxonomy> taxonomiesByPrefixName = taxonomyService.getTaxonomiesByPrefixName(DRUG, DRUG_PREFIX_SPEC);
        assertEquals(EXPECTED_NUMBER_OF_TAXONOMIES_SPEC, taxonomiesByPrefixName.size());
        taxonomiesByPrefixName.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : -1);
        for (int i = 0; i < EXPECTED_NUMBER_OF_TAXONOMIES_SPEC; i++) {
            assertEquals(i + OFFSET_IN_DATABASE_ID_SPEC, (long) taxonomiesByPrefixName.get(i).getId());
        }
    }

    @Test
    @Transactional
    public void getTaxonomiesByPrefixNamePkparameterTenRecords() {
        List<? extends Taxonomy> taxonomiesByPrefixName = taxonomyService.getTaxonomiesByPrefixName(PKPARAMETER, PKPARAMETER_PREFIX);
        assertEquals(EXPECTED_NUMBER_OF_TAXONOMIES, taxonomiesByPrefixName.size());
        taxonomiesByPrefixName.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : -1);
        for (int i = 0; i < EXPECTED_NUMBER_OF_TAXONOMIES; i++) {
            assertEquals(i + OFFSET_IN_DATABASE_ID, (long) taxonomiesByPrefixName.get(i).getId());
        }
    }

    @Test
    @Transactional
    public void getTaxonomiesByPrefixNamePkparameter() {
        List<? extends Taxonomy> taxonomiesByPrefixName = taxonomyService.getTaxonomiesByPrefixName(PKPARAMETER, PKPARAMETER_PREFIX_SPEC);
        assertEquals(EXPECTED_NUMBER_OF_TAXONOMIES_SPEC, taxonomiesByPrefixName.size());
        taxonomiesByPrefixName.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : Objects.equals(o1.getId(), o2.getId()) ? 0 : -1);
        for (int i = 0; i < EXPECTED_NUMBER_OF_TAXONOMIES_SPEC; i++) {
            assertEquals(i + OFFSET_IN_DATABASE_ID_SPEC, (long) taxonomiesByPrefixName.get(i).getId());
        }
    }
}
