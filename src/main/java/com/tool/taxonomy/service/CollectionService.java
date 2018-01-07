package com.tool.taxonomy.service;

import com.tool.taxonomy.converter.excel.ExcelConverter;
import com.tool.taxonomy.converter.excel.ResearchConverter;
import com.tool.taxonomy.dto.ExcelResearchDTO;
import com.tool.taxonomy.exception.ExceptionMessages;
import com.tool.taxonomy.exception.excel.ExcelIOException;
import com.tool.taxonomy.model.Research;
import com.tool.taxonomy.repository.ResearchRepository;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class CollectionService {

    private static final String EXCEL_FORMAT = ".xlsx";

    @Autowired
    private ExcelConverter excelConverter;

    @Autowired
    private ResearchConverter researchConverter;

    @Autowired
    private ResearchRepository researchRepository;

    public Research getResearchById(final long id) {
        return researchRepository.findOne(id);
    }

    public List<Research> importResearchFromExcel(final MultipartFile file) throws ExcelIOException {
        if (file.isEmpty()) {
            throw new ExcelIOException(ExceptionMessages.EMPTY_INPUT_FILE);
        }
        final List<ExcelResearchDTO> excelDTOs = excelConverter.convertToExcelDTO(getWorkbook(file));
        final List<Research> researchs = new LinkedList<>();
        excelDTOs.forEach(ex -> {
            final Research research = researchConverter.convertToPerson(ex);
            researchs.add(research);
        });
        return researchRepository.save(researchs);
    }

    private XSSFWorkbook getWorkbook(final MultipartFile file) throws ExcelIOException {
        if (file.getOriginalFilename().endsWith(EXCEL_FORMAT)) {
            try (final ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes())) {
                return new XSSFWorkbook(bis);
            } catch (IOException e) {
                throw new ExcelIOException(ExceptionMessages.EXCEL_FILE_READING_FAILED);
            }
        } else {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXCEL_FILE);
        }
    }
}
