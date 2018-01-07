package com.tool.taxonomy.controller;

import com.tool.taxonomy.TaxonomyApplication;
import com.tool.taxonomy.model.Drug;
import com.tool.taxonomy.model.Research;
import com.tool.taxonomy.model.Taxonomy;
import com.tool.taxonomy.service.CollectionService;
import com.tool.taxonomy.service.TaxonomyService;
import com.tool.taxonomy.util.Filter;
import com.tool.taxonomy.util.ResourceLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaxonomyController.class)
@ContextConfiguration(classes = {TaxonomyApplication.class})
@Import(value = {TaxonomyController.class,
        ResourceLoader.class})
public class TaxonomyControllerTest {

    @MockBean
    private CollectionService collectionService;

    @MockBean
    private TaxonomyService taxonomyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResourceLoader resourceLoader;

    private final String TAXONOMY = "taxonomy";
    private final String TAXONOMY_NAME = "taxonomyName";
    private final String COLLECTION = "collection";
    private final String PREFIX = "prefix";
    private final String FILTER = "filter";
    private final String ID = "id";
    private final String ID_VALUE = "1";

    private final int NUMBER_OF_INVOCATION = 1;

    private static MockMultipartFile csvMultipartFile;
    private static MockMultipartFile excelMultipartFile;
    private Taxonomy taxonomy;
    private List<Research> researchs;
    private List<? extends Taxonomy> taxonomies;

    private Research research;
    private Filter filter;

    @Before
    public void setUp() throws IOException {
        csvMultipartFile = new MockMultipartFile(
                "csvFile",
                "taxonomy.csv",
                "text/csv",
                resourceLoader.getResourceAsMultipartFileCsv("csv/taxonomy.csv").getBytes());
        excelMultipartFile = new MockMultipartFile(
                "excel",
                "pk.xlsx",
                "application/vnd.ms-excel",
                resourceLoader.getResourceAsMultipartFileExcel("excel/pk.xlsx").getBytes());
        taxonomy = new Drug();
        researchs = new ArrayList<>();
        taxonomies = new ArrayList<>();
        filter = new Filter();
    }

    @Test
    public void testImportTaxonomy() throws Exception {
        doReturn(taxonomy).when(taxonomyService).importTaxonomyFromCSV(csvMultipartFile, TAXONOMY_NAME);
        mockMvc.perform(fileUpload("/rest/importTaxonomy")
                .file(TAXONOMY, csvMultipartFile.getBytes())
                .param(TAXONOMY_NAME, TAXONOMY_NAME))
                .andExpect(status().isOk());
        verify(taxonomyService, times(NUMBER_OF_INVOCATION)).importTaxonomyFromCSV(any(), eq(TAXONOMY_NAME));
        verifyNoMoreInteractions(taxonomyService);
    }

    @Test
    public void testImportCollection() throws Exception {
        when(collectionService.importResearchFromExcel(excelMultipartFile)).thenReturn(researchs);
        mockMvc.perform(fileUpload("/rest/importCollection")
                .file(COLLECTION, excelMultipartFile.getBytes()))
                .andExpect(status().isOk());
        verify(collectionService, times(NUMBER_OF_INVOCATION)).importResearchFromExcel(any());
        verifyNoMoreInteractions(collectionService);
    }

    @Test
    public void testGetRecord() throws Exception {
        when(collectionService.getResearchById(1L)).thenReturn(research);
        mockMvc.perform(get("/rest/getRecord")
                .param(ID, ID_VALUE))
                .andExpect(status().isOk());

        verify(collectionService, times(NUMBER_OF_INVOCATION)).getResearchById(1L);
        verifyNoMoreInteractions(collectionService);
    }


    @Test
    public void testSuggestTaxonomy() throws Exception {
        doReturn(taxonomies).when(taxonomyService).getTaxonomiesByPrefixName(TAXONOMY_NAME, PREFIX);
        mockMvc.perform(get("/rest/suggestTaxonomy")
                .param(TAXONOMY_NAME, TAXONOMY_NAME)
                .param(PREFIX, PREFIX))
                .andExpect(status().isOk());
        verify(taxonomyService, times(NUMBER_OF_INVOCATION)).getTaxonomiesByPrefixName(TAXONOMY_NAME, PREFIX);
        verifyNoMoreInteractions(taxonomyService);
    }

    @Test
    public void testSearchTaxonomy() throws Exception {
        when(taxonomyService.getResearchsByFilter(filter)).thenReturn(researchs);
        mockMvc.perform(get("/rest/search")
                .requestAttr(FILTER, filter))
                .andExpect(status().isOk());

        verify(taxonomyService, times(NUMBER_OF_INVOCATION)).getResearchsByFilter(anyObject());
        verifyNoMoreInteractions(taxonomyService);
    }
}
