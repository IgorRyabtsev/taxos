package com.tool.taxonomy.converter.excel;

import com.tool.taxonomy.dto.ExcelResearchDTO;
import com.tool.taxonomy.model.Drug;
import com.tool.taxonomy.model.Pkparameter;
import com.tool.taxonomy.model.Research;
import com.tool.taxonomy.repository.DrugsRepository;
import com.tool.taxonomy.repository.PkparameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResearchConverter {

    @Autowired
    private DrugsRepository drugsRepository;

    @Autowired
    private PkparameterRepository pkparameterRepository;

    private final int INDEX_OF_TAXONOMY = 0;
    public Research convertToPerson(final ExcelResearchDTO excelDTO) {
        final Research research = new Research();
        research.setId(excelDTO.id);
        research.setSourse(excelDTO.sourse);
        research.setStudyNumber(excelDTO.studyNumber);
        research.setStudyNumberOld(excelDTO.studyNumberOld);
        research.setStudyName(excelDTO.studyName);
        research.setSpecies(excelDTO.species);
        research.setStudyGroup(excelDTO.studyGroup);
        research.setNumber(excelDTO.number);
        research.setSex(excelDTO.sex);
        research.setAge(excelDTO.age);
        research.setRoute(excelDTO.route);
        research.setDose(excelDTO.dose);
        research.setDuration(excelDTO.duration);
        research.setComment(excelDTO.comment);
        research.setAssay(excelDTO.assay);
        research.setPkAnalysys(excelDTO.pkAnalysys);
        research.setConcomitantDrug(excelDTO.concomitantDrug);
        research.setParameterFinal(excelDTO.parameterFinal);
        research.setParameterComment(excelDTO.parameterComment);
        research.setValue(excelDTO.value);
        research.setSd(excelDTO.sd);
        research.setRangeFirst(excelDTO.rangeFirst);
        research.setRangeSecond(excelDTO.rangeSecond);
        research.setUnit(excelDTO.unit);
        research.setT(excelDTO.t);
        research.setFileName(excelDTO.fileName);
        research.setPage(excelDTO.page);
        research.setRadiolabelled(excelDTO.radiolabelled);
        research.setMetabolitesAndEnantiomers(excelDTO.metabolitesAndEnantiomers);
        research.setComcomitant(excelDTO.comcomitant);
        research.setTissueSpecific(excelDTO.tissueSpecific);
        research.setSeptember(excelDTO.september);
        research.setDecember(excelDTO.december);
        research.setFebruaryApril(excelDTO.februaryApril);
        research.setJuneAugust(excelDTO.juneAugust);
        research.setAprilJune(excelDTO.aprilJune);
        research.setOctoberDecember(excelDTO.octoberDecember);
        research.setDecemberFebruary(excelDTO.decemberFebruary);
        research.setAugustOctober(excelDTO.augustOctober);
        research.setDrug(findNecessaryDrug(excelDTO.name));
        research.setParameter(findNecessaryParameter(excelDTO.parameter));
        return research;
    }

    private Pkparameter findNecessaryParameter(final String parameterName) {
        final List<Pkparameter> necessaryParameter = pkparameterRepository.findByName(parameterName);
        if (necessaryParameter.isEmpty()) return null;
        return necessaryParameter.get(INDEX_OF_TAXONOMY);
    }

    private Drug findNecessaryDrug(final String drugName) {
        final List<Drug> necessaryDrug = drugsRepository.findByName(drugName);
        if (necessaryDrug.isEmpty()) return null;
        return necessaryDrug.get(INDEX_OF_TAXONOMY);
    }
}
