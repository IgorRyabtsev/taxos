package com.tool.taxonomy.converter.excel;

import com.tool.taxonomy.dto.ExcelResearchDTO;
import com.tool.taxonomy.util.ResourceLoader;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(value = {ExcelConverter.class,
        ResourceLoader.class})
public class ExcelConverterTest {

    @Autowired
    private ExcelConverter excelConverter;

    @Autowired
    private ResourceLoader resourceLoader;

    private final String ABACAVIR_SULFATE = "ABACAVIR SULFATE";
    private final String BIOAVAILABILITY = "bioavailability";
    private final String HUMAN = "human";
    private final String UNREPORTED = "unreported";
    private final String FILE_NAME = "WC500050162";
    private final String KEEP = "KEEP";
    private final String PLASMA_PROTEIN_BINGING = "plasma protein binding";
    private final String ORAL = "oral";
    private final String STUDY_NUMBER_ALL = "2";
    private final String UNIT = "%";
    private final Double VALUE_FIRST_ROW = 83.0;
    private final Double VALUE_SECOND_ROW = 49.0;
    private final Long PAGE_NUMBER = 5L;

    private static MockMultipartFile excelMultipartFile;
    private XSSFWorkbook xssfWorkbook;
    private List<ExcelResearchDTO> excelDTOsExpected;

    @Before
    public void setUp() throws IOException {
        excelMultipartFile = new MockMultipartFile(
                "excel",
                "pk.xlsx",
                "application/vnd.ms-excel",
                resourceLoader.getResourceAsMultipartFileExcel("excel/pk.xlsx").getBytes());
        final ByteArrayInputStream bis = new ByteArrayInputStream(excelMultipartFile.getBytes());
        xssfWorkbook = new XSSFWorkbook(bis);
        excelDTOsExpected = new ArrayList<>();
        final ExcelResearchDTO excelDTOFirstRow = new ExcelResearchDTO();
        excelDTOFirstRow.sourse = null;
        excelDTOFirstRow.name = ABACAVIR_SULFATE;
        excelDTOFirstRow.parameter = BIOAVAILABILITY;
        excelDTOFirstRow.studyNumber = null;
        excelDTOFirstRow.studyNumberOld = STUDY_NUMBER_ALL;
        excelDTOFirstRow.studyName = null;
        excelDTOFirstRow.species = HUMAN;
        excelDTOFirstRow.studyGroup = null;
        excelDTOFirstRow.number = null;
        excelDTOFirstRow.sex = null;
        excelDTOFirstRow.age = null;
        excelDTOFirstRow.route = ORAL;
        excelDTOFirstRow.dose = UNREPORTED;
        excelDTOFirstRow.duration = UNREPORTED;
        excelDTOFirstRow.comment = null;
        excelDTOFirstRow.assay = null;
        excelDTOFirstRow.pkAnalysys = null;
        excelDTOFirstRow.concomitantDrug = null;
        excelDTOFirstRow.parameterFinal = BIOAVAILABILITY;
        excelDTOFirstRow.parameterComment = null;
        excelDTOFirstRow.value = VALUE_FIRST_ROW;
        excelDTOFirstRow.sd = null;
        excelDTOFirstRow.rangeFirst = null;
        excelDTOFirstRow.rangeSecond = null;
        excelDTOFirstRow.unit = UNIT;
        excelDTOFirstRow.t = null;
        excelDTOFirstRow.fileName = FILE_NAME;
        excelDTOFirstRow.page = PAGE_NUMBER;
        excelDTOFirstRow.radiolabelled = null;
        excelDTOFirstRow.metabolitesAndEnantiomers = null;
        excelDTOFirstRow.comcomitant = null;
        excelDTOFirstRow.tissueSpecific = null;
        excelDTOFirstRow.september = KEEP;
        excelDTOFirstRow.december = KEEP;
        excelDTOFirstRow.februaryApril = KEEP;
        excelDTOFirstRow.juneAugust = KEEP;
        excelDTOFirstRow.aprilJune = KEEP;
        excelDTOFirstRow.octoberDecember = KEEP;
        excelDTOFirstRow.decemberFebruary = KEEP;
        excelDTOFirstRow.augustOctober = KEEP;
        excelDTOsExpected.add(excelDTOFirstRow);

        final ExcelResearchDTO excelDTOSecondRow = new ExcelResearchDTO();
        excelDTOSecondRow.sourse = null;
        excelDTOSecondRow.name = ABACAVIR_SULFATE;
        excelDTOSecondRow.parameter = PLASMA_PROTEIN_BINGING;
        excelDTOSecondRow.studyNumber = null;
        excelDTOSecondRow.studyNumberOld = STUDY_NUMBER_ALL;
        excelDTOSecondRow.studyName = null;
        excelDTOSecondRow.species = HUMAN;
        excelDTOSecondRow.studyGroup = null;
        excelDTOSecondRow.number = null;
        excelDTOSecondRow.sex = null;
        excelDTOSecondRow.age = null;
        excelDTOSecondRow.route = ORAL;
        excelDTOSecondRow.dose = UNREPORTED;
        excelDTOSecondRow.duration = UNREPORTED;
        excelDTOSecondRow.comment = null;
        excelDTOSecondRow.assay = null;
        excelDTOSecondRow.pkAnalysys = null;
        excelDTOSecondRow.concomitantDrug = null;
        excelDTOSecondRow.parameterFinal = PLASMA_PROTEIN_BINGING;
        excelDTOSecondRow.parameterComment = null;
        excelDTOSecondRow.value = VALUE_SECOND_ROW;
        excelDTOSecondRow.sd = null;
        excelDTOSecondRow.rangeFirst = null;
        excelDTOSecondRow.rangeSecond = null;
        excelDTOSecondRow.unit = UNIT;
        excelDTOSecondRow.t = null;
        excelDTOSecondRow.fileName = FILE_NAME;
        excelDTOSecondRow.page = PAGE_NUMBER;
        excelDTOSecondRow.radiolabelled = null;
        excelDTOSecondRow.metabolitesAndEnantiomers = null;
        excelDTOSecondRow.comcomitant = null;
        excelDTOSecondRow.tissueSpecific = null;
        excelDTOSecondRow.september = KEEP;
        excelDTOSecondRow.december = KEEP;
        excelDTOSecondRow.februaryApril = KEEP;
        excelDTOSecondRow.juneAugust = KEEP;
        excelDTOSecondRow.aprilJune = KEEP;
        excelDTOSecondRow.octoberDecember = KEEP;
        excelDTOSecondRow.decemberFebruary = KEEP;
        excelDTOSecondRow.augustOctober = KEEP;
        excelDTOsExpected.add(excelDTOSecondRow);

    }

    @Test
    public void testExcelConverter() {
        List<ExcelResearchDTO> excelDTOsActualy = excelConverter.convertToExcelDTO(xssfWorkbook);
        assertEquals(excelDTOsExpected.size(), excelDTOsActualy.size());
        for (int i = 0; i < excelDTOsExpected.size(); i++) {
            assertEquals(excelDTOsExpected.get(i).sourse, excelDTOsActualy.get(i).sourse);
            assertEquals(excelDTOsExpected.get(i).name, excelDTOsActualy.get(i).name);
            assertEquals(excelDTOsExpected.get(i).parameter, excelDTOsActualy.get(i).parameter);
            assertEquals(excelDTOsExpected.get(i).studyNumber, excelDTOsActualy.get(i).studyNumber);
            assertEquals(excelDTOsExpected.get(i).studyNumberOld, excelDTOsActualy.get(i).studyNumberOld);
            assertEquals(excelDTOsExpected.get(i).studyName, excelDTOsActualy.get(i).studyName);
            assertEquals(excelDTOsExpected.get(i).species, excelDTOsActualy.get(i).species);
            assertEquals(excelDTOsExpected.get(i).studyGroup, excelDTOsActualy.get(i).studyGroup);
            assertEquals(excelDTOsExpected.get(i).number, excelDTOsActualy.get(i).number);
            assertEquals(excelDTOsExpected.get(i).sex, excelDTOsActualy.get(i).sex);
            assertEquals(excelDTOsExpected.get(i).age, excelDTOsActualy.get(i).age);
            assertEquals(excelDTOsExpected.get(i).route, excelDTOsActualy.get(i).route);
            assertEquals(excelDTOsExpected.get(i).dose, excelDTOsActualy.get(i).dose);
            assertEquals(excelDTOsExpected.get(i).duration, excelDTOsActualy.get(i).duration);
            assertEquals(excelDTOsExpected.get(i).comment, excelDTOsActualy.get(i).comment);
            assertEquals(excelDTOsExpected.get(i).assay, excelDTOsActualy.get(i).assay);
            assertEquals(excelDTOsExpected.get(i).pkAnalysys, excelDTOsActualy.get(i).pkAnalysys);
            assertEquals(excelDTOsExpected.get(i).concomitantDrug, excelDTOsActualy.get(i).concomitantDrug);
            assertEquals(excelDTOsExpected.get(i).parameterFinal, excelDTOsActualy.get(i).parameterFinal);
            assertEquals(excelDTOsExpected.get(i).parameterComment, excelDTOsActualy.get(i).parameterComment);
            assertEquals(excelDTOsExpected.get(i).value, excelDTOsActualy.get(i).value);
            assertEquals(excelDTOsExpected.get(i).sd, excelDTOsActualy.get(i).sd);
            assertEquals(excelDTOsExpected.get(i).rangeFirst, excelDTOsActualy.get(i).rangeFirst);
            assertEquals(excelDTOsExpected.get(i).rangeSecond, excelDTOsActualy.get(i).rangeSecond);
            assertEquals(excelDTOsExpected.get(i).unit, excelDTOsActualy.get(i).unit);
            assertEquals(excelDTOsExpected.get(i).t, excelDTOsActualy.get(i).t);
            assertEquals(excelDTOsExpected.get(i).fileName, excelDTOsActualy.get(i).fileName);
            assertEquals(excelDTOsExpected.get(i).page, excelDTOsActualy.get(i).page);
            assertEquals(excelDTOsExpected.get(i).radiolabelled, excelDTOsActualy.get(i).radiolabelled);
            assertEquals(excelDTOsExpected.get(i).metabolitesAndEnantiomers, excelDTOsActualy.get(i).metabolitesAndEnantiomers);
            assertEquals(excelDTOsExpected.get(i).comcomitant, excelDTOsActualy.get(i).comcomitant);
            assertEquals(excelDTOsExpected.get(i).tissueSpecific, excelDTOsActualy.get(i).tissueSpecific);
            assertEquals(excelDTOsExpected.get(i).september, excelDTOsActualy.get(i).september);
            assertEquals(excelDTOsExpected.get(i).december, excelDTOsActualy.get(i).december);
            assertEquals(excelDTOsExpected.get(i).februaryApril, excelDTOsActualy.get(i).februaryApril);
            assertEquals(excelDTOsExpected.get(i).juneAugust, excelDTOsActualy.get(i).juneAugust);
            assertEquals(excelDTOsExpected.get(i).aprilJune, excelDTOsActualy.get(i).aprilJune);
            assertEquals(excelDTOsExpected.get(i).octoberDecember, excelDTOsActualy.get(i).octoberDecember);
            assertEquals(excelDTOsExpected.get(i).decemberFebruary, excelDTOsActualy.get(i).decemberFebruary);
            assertEquals(excelDTOsExpected.get(i).augustOctober, excelDTOsActualy.get(i).augustOctober);
        }
    }
}
