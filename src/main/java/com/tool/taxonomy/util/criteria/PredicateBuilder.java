package com.tool.taxonomy.util.criteria;

import com.tool.taxonomy.model.Drug;
import com.tool.taxonomy.model.Pkparameter;
import com.tool.taxonomy.model.Research;
import com.tool.taxonomy.repository.DrugsRepository;
import com.tool.taxonomy.repository.PkparameterRepository;
import com.tool.taxonomy.util.Filter;
import com.tool.taxonomy.util.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Component
public class PredicateBuilder {
    private static final String PARAMETER = "parameter";
    private static final String DRUG = "drug";
    private static final String SOURCE = "source";
    private static final String STUDY_NUMBER = "studyNumber";
    private static final String ID = "id";
    private static final String VALUE = "value";
    private static final String SD = "sd";
    private static final String RANGE_FIRST = "rangeFirst";
    private static final String RANGE_SECOND = "rangeSecond";
    private static final String NUMBER = "number";
    private static final String PAGE = "page";
    private static final String STUDY_NUMBER_OLD = "studyNumberOld";
    private static final String STUDY_NAME = "studyName";
    private static final String SPECIES = "species";
    private static final String STUDY_GROUP = "studyGroup";
    private static final String SEX = "sex";
    private static final String AGE = "age";
    private static final String ROUTE = "route";
    private static final String DOSE = "dose";
    private static final String DURATION = "duration";
    private static final String COMMENT = "comment";
    private static final String ASSAY = "assay";
    private static final String PK_ANALYSYS = "pkAnalysys";
    private static final String CONCOMITANT_DRUG = "concomitantDrug";
    private static final String PARAMETER_FINAL = "parameterFinal";
    private static final String PARAMETER_COMMENT = "parameterComment";
    private static final String UNIT = "unit";
    private static final String T = "t";
    private static final String FILE_NAME = "fileName";
    private static final String RADIOLABELLED = "radiolabelled";
    private static final String METABOLITES_AND_ENANTIOMERS = "metabolitesAndEnantiomers";
    private static final String COMCOMITANT = "comcomitant";
    private static final String TISSUE_SPECIFIC = "tissueSpecific";
    private static final String SEPTEMBER = "september";
    private static final String DECEMBER = "december";
    private static final String FEBRUARY_APRIL = "februaryApril";
    private static final String APRIL_JUNE = "aprilJune";
    private static final String JUNE_AUGUST = "juneAugust";
    private static final String AUGUST_OCTOBER = "augustOctober";
    private static final String OCTOBER_DECEMBER = "octoberDecember";
    private static final String DECEMBER_FEBRUARY = "decemberFebruary";

    @Autowired
    private DrugsRepository drugsRepository;

    @Autowired
    private PkparameterRepository pkparameterRepository;

    public List<Predicate> buildPredicates(final Root<Research> researchRoot, final Filter filter,
                                           final CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        final Join<Research, Pkparameter> joinedParameter = researchRoot.join(PARAMETER);
        final Join<Research, Drug> joinedDrug = researchRoot.join(DRUG);
        predicates = addPredicate(filter.getSourse(), SOURCE, predicates, researchRoot);
        predicates = addPredicate(filter.getStudyNumber(), STUDY_NUMBER, predicates, researchRoot);

        predicates = addPredicateForRangeDouble(filter.getValue(), VALUE, predicates, researchRoot, criteriaBuilder);
        predicates = addPredicateForRangeDouble(filter.getSd(), SD, predicates, researchRoot, criteriaBuilder);
        predicates = addPredicateForRangeDouble(filter.getRangeFirst(), RANGE_FIRST, predicates, researchRoot, criteriaBuilder);
        predicates = addPredicateForRangeDouble(filter.getRangeSecond(), RANGE_SECOND, predicates, researchRoot, criteriaBuilder);

        predicates = addPredicateForRangeLong(filter.getNumber(), NUMBER, predicates, researchRoot, criteriaBuilder);
        predicates = addPredicateForRangeLong(filter.getPage(), PAGE, predicates, researchRoot, criteriaBuilder);

        predicates = addPredicate(filter.getStudyNumberOld(), STUDY_NUMBER_OLD, predicates, researchRoot);
        predicates = addPredicate(filter.getStudyName(), STUDY_NAME, predicates, researchRoot);
        predicates = addPredicate(filter.getSpecies(), SPECIES, predicates, researchRoot);
        predicates = addPredicate(filter.getStudyGroup(), STUDY_GROUP, predicates, researchRoot);
        predicates = addPredicate(filter.getSex(), SEX, predicates, researchRoot);
        predicates = addPredicate(filter.getAge(), AGE, predicates, researchRoot);
        predicates = addPredicate(filter.getRoute(), ROUTE, predicates, researchRoot);
        predicates = addPredicate(filter.getDose(), DOSE, predicates, researchRoot);
        predicates = addPredicate(filter.getDuration(), DURATION, predicates, researchRoot);
        predicates = addPredicate(filter.getComment(), COMMENT, predicates, researchRoot);
        predicates = addPredicate(filter.getAssay(), ASSAY, predicates, researchRoot);
        predicates = addPredicate(filter.getPkAnalysys(), PK_ANALYSYS, predicates, researchRoot);
        predicates = addPredicate(filter.getConcomitantDrug(), CONCOMITANT_DRUG, predicates, researchRoot);
        predicates = addPredicate(filter.getParameterFinal(), PARAMETER_FINAL, predicates, researchRoot);
        predicates = addPredicate(filter.getParameterComment(), PARAMETER_COMMENT, predicates, researchRoot);
        predicates = addPredicate(filter.getUnit(), UNIT, predicates, researchRoot);
        predicates = addPredicate(filter.getT(), T, predicates, researchRoot);
        predicates = addPredicate(filter.getFileName(), FILE_NAME, predicates, researchRoot);
        predicates = addPredicate(filter.getRadiolabelled(), RADIOLABELLED, predicates, researchRoot);
        predicates = addPredicate(filter.getMetabolitesAndEnantiomers(), METABOLITES_AND_ENANTIOMERS, predicates, researchRoot);
        predicates = addPredicate(filter.getComcomitant(), COMCOMITANT, predicates, researchRoot);
        predicates = addPredicate(filter.getTissueSpecific(), TISSUE_SPECIFIC, predicates, researchRoot);
        predicates = addPredicate(filter.getSeptember(), SEPTEMBER, predicates, researchRoot);
        predicates = addPredicate(filter.getDecember(), DECEMBER, predicates, researchRoot);
        predicates = addPredicate(filter.getFebruaryApril(), FEBRUARY_APRIL, predicates, researchRoot);
        predicates = addPredicate(filter.getAprilJune(), APRIL_JUNE, predicates, researchRoot);
        predicates = addPredicate(filter.getJuneAugust(), JUNE_AUGUST, predicates, researchRoot);
        predicates = addPredicate(filter.getAugustOctober(), AUGUST_OCTOBER, predicates, researchRoot);
        predicates = addPredicate(filter.getOctoberDecember(), OCTOBER_DECEMBER, predicates, researchRoot);
        predicates = addPredicate(filter.getDecemberFebruary(), DECEMBER_FEBRUARY, predicates, researchRoot);

        List<Long> drugIds = getAllDrugsId(filter.getName());
        List<Long> pkParameterIds = getAllPkParameterId(filter.getParameter());
        if (!drugIds.isEmpty())
            predicates.add(joinedDrug.get(ID).in(drugIds));
        if (!pkParameterIds.isEmpty())
            predicates.add(joinedParameter.get(ID).in(pkParameterIds));

        return predicates;
    }

    private List<Long> getAllPkParameterId(final List<String> pkParameterNames) {
        if (pkParameterNames == null) return new LinkedList<>();
        final List<Pkparameter> parametersByName = pkparameterRepository.findByNameIn(pkParameterNames);
        final List<Long> parameterIds = new LinkedList<>();
        final Set<Long> parameterIdsSet = new HashSet<>();
        parametersByName.forEach(p -> dfsParameter(p, parameterIdsSet));
        parameterIds.addAll(parameterIdsSet);
        return parameterIds;
    }

    private void dfsParameter(final Pkparameter pkparameter, final Set<Long> parameterIdsSet) {
        parameterIdsSet.add(pkparameter.getId());
        pkparameter.getChildren().forEach(p -> dfsParameter(p, parameterIdsSet));
    }

    private List<Long> getAllDrugsId(final List<String> drugNames) {
        if (drugNames == null) return new LinkedList<>();
        final List<Drug> drugsByName = drugsRepository.findByNameIn(drugNames);
        final List<Long> drugIds = new LinkedList<>();
        final Set<Long> drugIdsSet = new HashSet<>();
        drugsByName.forEach(d -> dfsDrug(d, drugIdsSet));
        drugIds.addAll(drugIdsSet);
        return drugIds;
    }

    private void dfsDrug(final Drug drug, final Set<Long> drugIdsSet) {
        drugIdsSet.add(drug.getId());
        drug.getChildren().forEach(d -> dfsDrug(d, drugIdsSet));
    }

    private List<Predicate> addPredicateForRangeDouble(final Range<Double> property, final String nameOfProperty,
                                                       final List<Predicate> predicates, final Root<Research> researchRoot, final CriteriaBuilder criteriaBuilder) {
        if (property != null)
            predicates.add(criteriaBuilder.between(researchRoot.get(nameOfProperty), property.getLeftValue(), property.getRightValue()));
        return predicates;
    }

    private List<Predicate> addPredicateForRangeLong(final Range<Long> property, final String nameOfProperty,
                                                     final List<Predicate> predicates, final Root<Research> researchRoot, final CriteriaBuilder criteriaBuilder) {
        if (property != null)
            predicates.add(criteriaBuilder.between(researchRoot.get(nameOfProperty), property.getLeftValue(), property.getRightValue()));
        return predicates;
    }

    private List<Predicate> addPredicate(final List<String> property, final String nameOfProperty,
                                         final List<Predicate> predicates, final Root<Research> researchRoot) {
        if (property != null && !property.isEmpty())
            predicates.add(researchRoot.get(nameOfProperty).in(property));
        return predicates;
    }

}
