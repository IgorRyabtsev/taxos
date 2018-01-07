package com.tool.taxonomy.service;

import com.tool.taxonomy.converter.csv.CSVDrugConverter;
import com.tool.taxonomy.converter.csv.CSVPkparameterConverterTest;
import com.tool.taxonomy.exception.csv.CsvIOException;
import com.tool.taxonomy.model.Drug;
import com.tool.taxonomy.model.Pkparameter;
import com.tool.taxonomy.model.Research;
import com.tool.taxonomy.model.Taxonomy;
import com.tool.taxonomy.repository.DrugsRepository;
import com.tool.taxonomy.repository.PkparameterRepository;
import com.tool.taxonomy.util.Filter;
import com.tool.taxonomy.util.criteria.PredicateBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import(value = {TaxonomyService.class,
        CSVDrugConverter.class,
        CSVPkparameterConverterTest.class})
public class TaxonomyServiceTest {

    @MockBean
    private DrugsRepository drugsRepository;

    @MockBean
    private CSVPkparameterConverterTest csvPkparameterConverter;

    @MockBean
    private CSVDrugConverter csvDrugConverter;

    @MockBean
    private PkparameterRepository pkparameterRepository;

    @MockBean
    private EntityManager entityManager;


    @MockBean
    private PredicateBuilder predicateBuilder;

    @Autowired
    TaxonomyService taxonomyService;

    private static final String DRUG = "drug";
    private static final String PREFIX = "prefix";
    private static final String NOT_EXISTENT_TAXONOMY_NAME = "notExistentTaxonomyName";
    private static final String PKPARAMETER = "pkparameter";
    private static final String FIND_DRUG_BY_NAME_START_WITH = "findDrugsByNameStartWith";
    private static final String FIND_PKPARAMETER_BY_NAME_START_WITH = "findPkparameterByNameStartWith";
    private final int NUMBER_OF_INVOCATION_ZERO = 0;
    private final int NUMBER_OF_INVOCATION_ONE = 1;

    private static final String csvFileContent = "1,1,\"first\"\n2,1,\"second\"";
    private static final String emptyCsvFileContent = "";

    private static MockMultipartFile csvMultipartTaxonomyFile;
    private static MockMultipartFile emptyCsvMultipartTaxonomyFile;
    private static Drug drug;
    private static Pkparameter pkparameter;
    private static List<? extends Taxonomy> taxonomy;
    private static List<Predicate> predicates;

    @BeforeClass
    public static void setUp() throws IOException {
        csvMultipartTaxonomyFile = new MockMultipartFile(
                "csvFile",
                "taxonomyFile.csv",
                "text/csv",
                csvFileContent.getBytes());
        emptyCsvMultipartTaxonomyFile = new MockMultipartFile(
                "csvFile",
                "emptyTaxonomyFile.csv",
                "text/csv",
                emptyCsvFileContent.getBytes());
        drug = new Drug();
        pkparameter = new Pkparameter();
        taxonomy = new ArrayList<>();
        predicates = new ArrayList<>();
    }

    @Test
    public void testImportDrugTaxonomyFromCSV() throws IOException, CsvIOException {
        when(drugsRepository.save(any(Drug.class))).thenReturn(drug);
        assertEquals(drug, taxonomyService.importTaxonomyFromCSV(csvMultipartTaxonomyFile, DRUG));
        verify(drugsRepository, times(NUMBER_OF_INVOCATION_ONE)).save(any(Drug.class));
        verify(pkparameterRepository, times(NUMBER_OF_INVOCATION_ZERO)).save(any(Pkparameter.class));
        verifyNoMoreInteractions(drugsRepository);
        verifyNoMoreInteractions(pkparameterRepository);
    }

    @Test
    public void testImportPkparameterTaxonomyFromCSV() throws IOException, CsvIOException {
        when(pkparameterRepository.save(any(Pkparameter.class))).thenReturn(pkparameter);
        assertEquals(pkparameter, taxonomyService.importTaxonomyFromCSV(csvMultipartTaxonomyFile, PKPARAMETER));
        verify(drugsRepository, times(NUMBER_OF_INVOCATION_ZERO)).save(any(Drug.class));
        verify(pkparameterRepository, times(NUMBER_OF_INVOCATION_ONE)).save(any(Pkparameter.class));
        verifyNoMoreInteractions(drugsRepository);
        verifyNoMoreInteractions(pkparameterRepository);
    }

    @Test(expected = IllegalStateException.class)
    public void testImportNotExistentTaxonomyFromCSV() throws IOException, CsvIOException {
        assertEquals(pkparameter, taxonomyService.importTaxonomyFromCSV(csvMultipartTaxonomyFile, NOT_EXISTENT_TAXONOMY_NAME));
    }

    @Test(expected = CsvIOException.class)
    public void testImportTaxonomyFromCSVThatEmpty() throws IOException, CsvIOException {
        assertEquals(pkparameter, taxonomyService.importTaxonomyFromCSV(emptyCsvMultipartTaxonomyFile, NOT_EXISTENT_TAXONOMY_NAME));
    }

    @Test
    public void testGetDrugsByPrefixName() {
        final Query query = mock(Query.class);
        when(entityManager.createNamedQuery(FIND_DRUG_BY_NAME_START_WITH)).thenReturn(query);
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        when(query.setMaxResults(anyInt())).thenReturn(query);
        when(query.getResultList()).thenReturn(taxonomy);
        assertEquals(taxonomy, taxonomyService.getTaxonomiesByPrefixName(DRUG, PREFIX));
        verify(entityManager, times(NUMBER_OF_INVOCATION_ONE)).createNamedQuery(FIND_DRUG_BY_NAME_START_WITH);
        verify(entityManager, times(NUMBER_OF_INVOCATION_ZERO)).createNamedQuery(FIND_PKPARAMETER_BY_NAME_START_WITH);
        verifyNoMoreInteractions(entityManager);
    }

    @Test
    public void testGetPkparameterByPrefixName() {
        final Query query = mock(Query.class);
        when(entityManager.createNamedQuery(FIND_PKPARAMETER_BY_NAME_START_WITH)).thenReturn(query);
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        when(query.setMaxResults(anyInt())).thenReturn(query);
        when(query.getResultList()).thenReturn(taxonomy);
        assertEquals(taxonomy, taxonomyService.getTaxonomiesByPrefixName(PKPARAMETER, PREFIX));
        verify(entityManager, times(NUMBER_OF_INVOCATION_ZERO)).createNamedQuery(FIND_DRUG_BY_NAME_START_WITH);
        verify(entityManager, times(NUMBER_OF_INVOCATION_ONE)).createNamedQuery(FIND_PKPARAMETER_BY_NAME_START_WITH);
        verifyNoMoreInteractions(entityManager);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetNotExistentTaxonomyByPrefixName() {
        assertEquals(taxonomy, taxonomyService.getTaxonomiesByPrefixName(NOT_EXISTENT_TAXONOMY_NAME, PREFIX));
    }

    @Test
    public void testGetResearchsByFilter() {
        final Filter filter = new Filter();
        final TypedQuery typedQuery = mock(TypedQuery.class);
        final CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        final CriteriaQuery query = mock(CriteriaQuery.class);
        final Root<Research> researchRoot = mock(Root.class);
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(anyObject())).thenReturn(query);
        when(query.from(Research.class)).thenReturn(researchRoot);
        when(predicateBuilder.buildPredicates(researchRoot, filter, criteriaBuilder)).thenReturn(predicates);
        when(query.where(predicates.toArray(new Predicate[predicates.size()]))).thenReturn(null);
        when(entityManager.createQuery(query)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(taxonomy);
        assertEquals(taxonomy, taxonomyService.getResearchsByFilter(filter));
        verify(entityManager, times(NUMBER_OF_INVOCATION_ONE)).getCriteriaBuilder();
        verify(entityManager, times(NUMBER_OF_INVOCATION_ONE)).createQuery(query);
        verify(typedQuery, times(NUMBER_OF_INVOCATION_ONE)).getResultList();
        verify(query, times(NUMBER_OF_INVOCATION_ONE)).from(Research.class);
        verify(query, times(NUMBER_OF_INVOCATION_ONE)).where(predicates.toArray(new Predicate[predicates.size()]));
        verify(predicateBuilder, times(NUMBER_OF_INVOCATION_ONE)).buildPredicates(researchRoot, filter, criteriaBuilder);
        verifyNoMoreInteractions(entityManager);
        verifyNoMoreInteractions(predicateBuilder);
    }


}
