package com.tool.taxonomy.service;

import com.tool.taxonomy.converter.excel.ExcelConverter;
import com.tool.taxonomy.converter.excel.ResearchConverter;
import com.tool.taxonomy.dto.ExcelResearchDTO;
import com.tool.taxonomy.exception.excel.ExcelIOException;
import com.tool.taxonomy.model.Research;
import com.tool.taxonomy.repository.ResearchRepository;
import com.tool.taxonomy.util.ResourceLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import(value = {CollectionService.class,
        ExcelConverter.class,
        ResearchConverter.class,
        ResourceLoader.class})
public class CollectionServiceTest {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ResourceLoader resourceLoader;
    @MockBean
    private ExcelConverter excelConverter;

    @MockBean
    private ResearchConverter researchConverter;

    @MockBean
    private ResearchRepository researchRepository;

    private final int NUMBER_OF_INVOCATION_ZERO = 0;
    private final int NUMBER_OF_INVOCATION_ONE = 1;
    private static final long INDEX = 1;
    private static MockMultipartFile excelMultipartFile;
    private List<ExcelResearchDTO> excelDTOs;
    private Research research;
    private List<Research> researchs;

    @Before
    public void setUp() throws IOException {
        excelMultipartFile = new MockMultipartFile(
                "excel",
                "pk.xlsx",
                "application/vnd.ms-excel",
                resourceLoader.getResourceAsMultipartFileExcel("excel/pk.xlsx").getBytes());
        research = new Research();
        excelDTOs = new ArrayList<>();
        researchs = new ArrayList<>();
    }

    @Test
    public void testGetResearchById() {
        when(researchRepository.findOne(anyLong())).thenReturn(research);
        assertEquals(research, collectionService.getResearchById(INDEX));
        verify(researchRepository, times(NUMBER_OF_INVOCATION_ONE)).findOne(INDEX);
        verifyNoMoreInteractions(researchRepository);
    }

    @Test
    public void testImportResearchFromExcel() throws ExcelIOException {
        when(excelConverter.convertToExcelDTO(anyObject())).thenReturn(excelDTOs);
        when(researchConverter.convertToPerson(anyObject())).thenReturn(research);
        when(researchRepository.save(anyCollection())).thenReturn(researchs);
        assertEquals(researchs, collectionService.importResearchFromExcel(excelMultipartFile));
        verify(researchRepository, times(NUMBER_OF_INVOCATION_ONE)).save(anyCollection());
        verify(researchConverter, times(NUMBER_OF_INVOCATION_ZERO)).convertToPerson(anyObject());
        verify(excelConverter, times(NUMBER_OF_INVOCATION_ONE)).convertToExcelDTO(anyObject());
        verifyNoMoreInteractions(researchRepository);
        verifyNoMoreInteractions(researchConverter);
        verifyNoMoreInteractions(excelConverter);
    }
}
