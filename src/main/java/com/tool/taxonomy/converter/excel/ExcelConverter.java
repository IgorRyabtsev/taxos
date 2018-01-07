package com.tool.taxonomy.converter.excel;

import com.tool.taxonomy.dto.ExcelResearchDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class ExcelConverter {
    private static final String SHEET_NAME = "Sheet1";
    private static final int FIRST_ROW = 1;

    public List<ExcelResearchDTO> convertToExcelDTO(final XSSFWorkbook workbook) {
        if (workbook != null) {
            final Sheet sheet = workbook.getSheet(SHEET_NAME);
            return convertToExcelDTO(sheet, (row, rowNumber) -> convertDataToExcelDTO(row, rowNumber + 1));
        }
        return new ArrayList<>();
    }

    private List<ExcelResearchDTO> convertToExcelDTO(final Sheet sheet, final BiFunction<Row, Integer, ExcelResearchDTO> conversionFunction) {
        final List<ExcelResearchDTO> excelDTOS = new ArrayList<>();
        int numberOfRows = sheet.getPhysicalNumberOfRows();
        for (int i = FIRST_ROW; i < numberOfRows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                ExcelResearchDTO excelDTO = conversionFunction.apply(row, i);
                excelDTOS.add(excelDTO);
            }
        }
        return excelDTOS;
    }

    private ExcelResearchDTO convertDataToExcelDTO(final Row row, final int currentRowNumber) {
        final ExcelResearchDTO excelDTO = new ExcelResearchDTO();
        excelDTO.id = (long) (currentRowNumber - 1);
        excelDTO.sourse = getStringCellValue(ExcelColumnMapping.Sourse, row, currentRowNumber);
        excelDTO.name = getStringCellValue(ExcelColumnMapping.Name, row, currentRowNumber);
        excelDTO.parameter = getStringCellValue(ExcelColumnMapping.Parameter, row, currentRowNumber);
        excelDTO.studyNumber = getStringCellValue(ExcelColumnMapping.StudyNumber, row, currentRowNumber);
        excelDTO.studyNumberOld = getStringCellValue(ExcelColumnMapping.StudyNumberOld, row, currentRowNumber);
        excelDTO.studyName = getStringCellValue(ExcelColumnMapping.StudyName, row, currentRowNumber);
        excelDTO.species = getStringCellValue(ExcelColumnMapping.Species, row, currentRowNumber);
        excelDTO.studyGroup = getStringCellValue(ExcelColumnMapping.StudyGroup, row, currentRowNumber);
        excelDTO.number = getLongCellValue(ExcelColumnMapping.Num, row, currentRowNumber);
        excelDTO.sex = getStringCellValue(ExcelColumnMapping.Sex, row, currentRowNumber);
        excelDTO.age = getStringCellValue(ExcelColumnMapping.Age, row, currentRowNumber);
        excelDTO.route = getStringCellValue(ExcelColumnMapping.Route, row, currentRowNumber);
        excelDTO.dose = getStringCellValue(ExcelColumnMapping.Dose, row, currentRowNumber);
        excelDTO.duration = getStringCellValue(ExcelColumnMapping.Duration, row, currentRowNumber);
        excelDTO.comment = getStringCellValue(ExcelColumnMapping.Comment, row, currentRowNumber);
        excelDTO.assay = getStringCellValue(ExcelColumnMapping.Assay, row, currentRowNumber);
        excelDTO.pkAnalysys = getStringCellValue(ExcelColumnMapping.PkAnalysys, row, currentRowNumber);
        excelDTO.concomitantDrug = getStringCellValue(ExcelColumnMapping.ConcomitantDrug, row, currentRowNumber);
        excelDTO.parameterFinal = getStringCellValue(ExcelColumnMapping.ParameterFinal, row, currentRowNumber);
        excelDTO.parameterComment = getStringCellValue(ExcelColumnMapping.ParameterComment, row, currentRowNumber);
        excelDTO.value = getDoubleCellValue(ExcelColumnMapping.Value, row, currentRowNumber);
        excelDTO.sd = getDoubleCellValue(ExcelColumnMapping.Sd, row, currentRowNumber);
        excelDTO.rangeFirst = getDoubleCellValue(ExcelColumnMapping.RangeFirst, row, currentRowNumber);
        excelDTO.rangeSecond = getDoubleCellValue(ExcelColumnMapping.RangeSecond, row, currentRowNumber);
        excelDTO.unit = getStringCellValue(ExcelColumnMapping.Unit, row, currentRowNumber);
        excelDTO.t = getStringCellValue(ExcelColumnMapping.T, row, currentRowNumber);
        excelDTO.fileName = getStringCellValue(ExcelColumnMapping.FileName, row, currentRowNumber);
        excelDTO.page = getLongCellValue(ExcelColumnMapping.Page, row, currentRowNumber);
        excelDTO.radiolabelled = getStringCellValue(ExcelColumnMapping.Radiolabelled, row, currentRowNumber);
        excelDTO.metabolitesAndEnantiomers = getStringCellValue(ExcelColumnMapping.MetabolitesAndEnantiomers, row, currentRowNumber);
        excelDTO.comcomitant = getStringCellValue(ExcelColumnMapping.Comcomitant, row, currentRowNumber);
        excelDTO.tissueSpecific = getStringCellValue(ExcelColumnMapping.TissueSpecific, row, currentRowNumber);
        excelDTO.september = getStringCellValue(ExcelColumnMapping.September, row, currentRowNumber);
        excelDTO.december = getStringCellValue(ExcelColumnMapping.December, row, currentRowNumber);
        excelDTO.februaryApril = getStringCellValue(ExcelColumnMapping.FebruaryApril, row, currentRowNumber);
        excelDTO.juneAugust = getStringCellValue(ExcelColumnMapping.JuneAugust, row, currentRowNumber);
        excelDTO.aprilJune = getStringCellValue(ExcelColumnMapping.AprilJune, row, currentRowNumber);
        excelDTO.octoberDecember = getStringCellValue(ExcelColumnMapping.OctoberDecember, row, currentRowNumber);
        excelDTO.decemberFebruary = getStringCellValue(ExcelColumnMapping.DecemberFebruary, row, currentRowNumber);
        excelDTO.augustOctober = getStringCellValue(ExcelColumnMapping.AugustOctober, row, currentRowNumber);

        return excelDTO;
    }

    private String getStringCellValue(final ExcelColumnMapping columnMapping, final Row row, final int rowNumber) {
        final Cell cell = getCell(columnMapping, row, rowNumber);
        if (!isCellEmpty(cell) && (cell.getCellTypeEnum() == CellType.STRING))
            return cell.getStringCellValue();
        if (!isCellEmpty(cell) && (cell.getCellTypeEnum() == CellType.NUMERIC))
            return String.valueOf((long) cell.getNumericCellValue());
        return null;
    }

    private Long getLongCellValue(final ExcelColumnMapping columnMapping, final Row row, final int rowNumber) {
        final Cell cell = getCell(columnMapping, row, rowNumber);
        if (!isCellEmpty(cell) && cell.getCellTypeEnum() == CellType.NUMERIC) {
            return (long) cell.getNumericCellValue();
        }
        return null;
    }

    private Double getDoubleCellValue(final ExcelColumnMapping columnMapping, final Row row, int rowNumber) {
        final Cell cell = getCell(columnMapping, row, rowNumber);
        if (!isCellEmpty(cell) && cell.getCellTypeEnum() == CellType.NUMERIC)
            return cell.getNumericCellValue();
        return null;
    }

    private Cell getCell(final ExcelColumnMapping columnMapping, final Row row, final int rowNumber) {
        final CellReference ref = new CellReference(columnMapping.getTitle() + (rowNumber + 1));
        return row.getCell(ref.getCol());
    }

    private static boolean isCellEmpty(final Cell cell) {
        return cell == null || cell.getCellTypeEnum() == CellType.BLANK
                || (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue().isEmpty());

    }

}
