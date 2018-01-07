package com.tool.taxonomy.converter.excel;

import com.tool.taxonomy.dto.ExcelResearchDTO;
import com.tool.taxonomy.model.Drug;
import com.tool.taxonomy.model.Pkparameter;
import com.tool.taxonomy.model.Research;
import com.tool.taxonomy.model.Taxonomy;
import com.tool.taxonomy.repository.DrugsRepository;
import com.tool.taxonomy.repository.PkparameterRepository;
import com.tool.taxonomy.util.ResourceLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import(value = {ResearchConverter.class,
        ResourceLoader.class})
public class ResearchConverterTest {

    @Autowired
    private ResearchConverter researchConverter;

    @MockBean
    private PkparameterRepository pkparameterRepository;

    @MockBean
    private DrugsRepository drugsRepository;

    private final String ABACAVIR_SULFATE = "ABACAVIR SULFATE";
    private final String BIOAVAILABILITY = "bioavailability";
    private final String HUMAN = "human";
    private final String UNREPORTED = "unreported";
    private final String FILE_NAME = "WC500050162";
    private final String KEEP = "KEEP";
    private final String ORAL = "oral";
    private final String STUDY_NUMBER_ALL = "2";
    private final String UNIT = "%";
    private final Double VALUE = 83.0;
    private final Long PAGE_NUMBER = 5L;

    private ExcelResearchDTO excelDTO;
    private Research researchExpected;
    private List<? extends Taxonomy> taxonomies;

    @Before
    public void setUp() {
        excelDTO = new ExcelResearchDTO();
        excelDTO.sourse = null;
        excelDTO.name = ABACAVIR_SULFATE;
        excelDTO.parameter = BIOAVAILABILITY;
        excelDTO.studyNumber = null;
        excelDTO.studyNumberOld = STUDY_NUMBER_ALL;
        excelDTO.studyName = null;
        excelDTO.species = HUMAN;
        excelDTO.studyGroup = null;
        excelDTO.number = null;
        excelDTO.sex = null;
        excelDTO.age = null;
        excelDTO.route = ORAL;
        excelDTO.dose = UNREPORTED;
        excelDTO.duration = UNREPORTED;
        excelDTO.comment = null;
        excelDTO.assay = null;
        excelDTO.pkAnalysys = null;
        excelDTO.concomitantDrug = null;
        excelDTO.parameterFinal = BIOAVAILABILITY;
        excelDTO.parameterComment = null;
        excelDTO.value = VALUE;
        excelDTO.sd = null;
        excelDTO.rangeFirst = null;
        excelDTO.rangeSecond = null;
        excelDTO.unit = UNIT;
        excelDTO.t = null;
        excelDTO.fileName = FILE_NAME;
        excelDTO.page = PAGE_NUMBER;
        excelDTO.radiolabelled = null;
        excelDTO.metabolitesAndEnantiomers = null;
        excelDTO.comcomitant = null;
        excelDTO.tissueSpecific = null;
        excelDTO.september = KEEP;
        excelDTO.december = KEEP;
        excelDTO.februaryApril = KEEP;
        excelDTO.juneAugust = KEEP;
        excelDTO.aprilJune = KEEP;
        excelDTO.octoberDecember = KEEP;
        excelDTO.decemberFebruary = KEEP;
        excelDTO.augustOctober = KEEP;

        researchExpected = new Research();
        researchExpected.setSourse(null);
        researchExpected.setDrug(null);
        researchExpected.setParameter(null);
        researchExpected.setStudyNumber(null);
        researchExpected.setStudyNumberOld(STUDY_NUMBER_ALL);
        researchExpected.setStudyName(null);
        researchExpected.setSpecies(HUMAN);
        researchExpected.setStudyGroup(null);
        researchExpected.setNumber(null);
        researchExpected.setSex(null);
        researchExpected.setAge(null);
        researchExpected.setRoute(ORAL);
        researchExpected.setDose(UNREPORTED);
        researchExpected.setDuration(UNREPORTED);
        researchExpected.setComment(null);
        researchExpected.setAssay(null);
        researchExpected.setPkAnalysys(null);
        researchExpected.setConcomitantDrug(null);
        researchExpected.setParameterFinal(BIOAVAILABILITY);
        researchExpected.setParameterComment(null);
        researchExpected.setValue(VALUE);
        researchExpected.setSd(null);
        researchExpected.setRangeFirst(null);
        researchExpected.setRangeSecond(null);
        researchExpected.setUnit(UNIT);
        researchExpected.setT(null);
        researchExpected.setFileName(FILE_NAME);
        researchExpected.setPage(PAGE_NUMBER);
        researchExpected.setRadiolabelled(null);
        researchExpected.setMetabolitesAndEnantiomers(null);
        researchExpected.setComcomitant(null);
        researchExpected.setTissueSpecific(null);
        researchExpected.setSeptember(KEEP);
        researchExpected.setDecember(KEEP);
        researchExpected.setFebruaryApril(KEEP);
        researchExpected.setJuneAugust(KEEP);
        researchExpected.setAprilJune(KEEP);
        researchExpected.setOctoberDecember(KEEP);
        researchExpected.setDecemberFebruary(KEEP);
        researchExpected.setAugustOctober(KEEP);

        taxonomies = new ArrayList<>();
    }

    @Test
    public void testResearchConverter() {
        when(pkparameterRepository.findByName(anyString())).thenReturn((List<Pkparameter>) taxonomies);
        when(drugsRepository.findByName(anyString())).thenReturn((List<Drug>) taxonomies);
        Research researchActually = researchConverter.convertToPerson(excelDTO);
        assertEquals(researchActually.getSourse(), researchExpected.getSourse());
        assertEquals(researchActually.getDrug(), researchExpected.getDrug());
        assertEquals(researchActually.getParameter(), researchExpected.getParameter());
        assertEquals(researchActually.getStudyNumber(), researchExpected.getStudyNumber());
        assertEquals(researchActually.getStudyNumberOld(), researchExpected.getStudyNumberOld());
        assertEquals(researchActually.getStudyName(), researchExpected.getStudyName());
        assertEquals(researchActually.getSpecies(), researchExpected.getSpecies());
        assertEquals(researchActually.getStudyGroup(), researchExpected.getStudyGroup());
        assertEquals(researchActually.getNumber(), researchExpected.getNumber());
        assertEquals(researchActually.getSex(), researchExpected.getSex());
        assertEquals(researchActually.getAge(), researchExpected.getAge());
        assertEquals(researchActually.getRoute(), researchExpected.getRoute());
        assertEquals(researchActually.getDose(), researchExpected.getDose());
        assertEquals(researchActually.getDuration(), researchExpected.getDuration());
        assertEquals(researchActually.getComment(), researchExpected.getComment());
        assertEquals(researchActually.getAssay(), researchExpected.getAssay());
        assertEquals(researchActually.getPkAnalysys(), researchExpected.getPkAnalysys());
        assertEquals(researchActually.getConcomitantDrug(), researchExpected.getConcomitantDrug());
        assertEquals(researchActually.getParameterFinal(), researchExpected.getParameterFinal());
        assertEquals(researchActually.getParameterComment(), researchExpected.getParameterComment());
        assertEquals(researchActually.getValue(), researchExpected.getValue());
        assertEquals(researchActually.getSd(), researchExpected.getSd());
        assertEquals(researchActually.getRangeFirst(), researchExpected.getRangeFirst());
        assertEquals(researchActually.getRangeSecond(), researchExpected.getRangeSecond());
        assertEquals(researchActually.getUnit(), researchExpected.getUnit());
        assertEquals(researchActually.getT(), researchExpected.getT());
        assertEquals(researchActually.getFileName(), researchExpected.getFileName());
        assertEquals(researchActually.getPage(), researchExpected.getPage());
        assertEquals(researchActually.getRadiolabelled(), researchExpected.getRadiolabelled());
        assertEquals(researchActually.getMetabolitesAndEnantiomers(), researchExpected.getMetabolitesAndEnantiomers());
        assertEquals(researchActually.getComcomitant(), researchExpected.getComcomitant());
        assertEquals(researchActually.getTissueSpecific(), researchExpected.getTissueSpecific());
        assertEquals(researchActually.getSeptember(), researchExpected.getSeptember());
        assertEquals(researchActually.getDecember(), researchExpected.getDecember());
        assertEquals(researchActually.getFebruaryApril(), researchExpected.getFebruaryApril());
        assertEquals(researchActually.getJuneAugust(), researchExpected.getJuneAugust());
        assertEquals(researchActually.getAprilJune(), researchExpected.getAprilJune());
        assertEquals(researchActually.getOctoberDecember(), researchExpected.getOctoberDecember());
        assertEquals(researchActually.getDecemberFebruary(), researchExpected.getDecemberFebruary());
        assertEquals(researchActually.getAugustOctober(), researchExpected.getAugustOctober());
    }
}
