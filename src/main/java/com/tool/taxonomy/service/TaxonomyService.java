package com.tool.taxonomy.service;

import com.tool.taxonomy.converter.csv.CSVDrugConverter;
import com.tool.taxonomy.converter.csv.CSVPkparameterConverter;
import com.tool.taxonomy.exception.ExceptionMessages;
import com.tool.taxonomy.exception.csv.CsvIOException;
import com.tool.taxonomy.model.Drug;
import com.tool.taxonomy.model.Pkparameter;
import com.tool.taxonomy.model.Research;
import com.tool.taxonomy.model.Taxonomy;
import com.tool.taxonomy.repository.DrugsRepository;
import com.tool.taxonomy.repository.PkparameterRepository;
import com.tool.taxonomy.util.Filter;
import com.tool.taxonomy.util.criteria.PredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;

@Service
public class TaxonomyService {
    private final String DRUG_TAXONOMY = "drug";
    private final String PKPARAMETER_TAXONOMY = "pkparameter";
    private final int NUMBER_OF_RECORDS = 10;

    @Autowired
    private DrugsRepository drugsRepository;

    @Autowired
    private PkparameterRepository pkparameterRepository;

    @Autowired
    private CSVPkparameterConverter csvPkparameterConverter;

    @Autowired
    private CSVDrugConverter csvDrugConverter;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PredicateBuilder predicateBuilder;

    private final String NAMED_QUERY_FIND_DRUGS_BY_NAME_START_WITH = "findDrugsByNameStartWith";
    private final String NAMED_QUERY_FIND_PKPARAMETER_BY_NAME_START_WITH = "findPkparameterByNameStartWith";
    public List<? extends Taxonomy> getTaxonomiesByPrefixName(final String taxonomyName, final String prefix) {
        switch (taxonomyName) {
            case DRUG_TAXONOMY:
                return (List<Drug>) entityManager.createNamedQuery(NAMED_QUERY_FIND_DRUGS_BY_NAME_START_WITH)
                        .setParameter("nameLike", prefix)
                        .setMaxResults(NUMBER_OF_RECORDS)
                        .getResultList();
            case PKPARAMETER_TAXONOMY:
                return (List<Pkparameter>) entityManager.createNamedQuery(NAMED_QUERY_FIND_PKPARAMETER_BY_NAME_START_WITH)
                        .setParameter("nameLike", prefix)
                        .setMaxResults(NUMBER_OF_RECORDS)
                        .getResultList();
            default:
                throw new IllegalStateException(ExceptionMessages.NO_SUCH_TAXONOMY);
        }
    }

    public Taxonomy importTaxonomyFromCSV(final MultipartFile multipartFile, final String name)
            throws IOException, CsvIOException {
        if (multipartFile.isEmpty())
            throw new CsvIOException(ExceptionMessages.EMPTY_INPUT_FILE);
        switch (name) {
            case DRUG_TAXONOMY:
                return drugsRepository.save(csvDrugConverter.importTaxonomy(multipartFile));
            case PKPARAMETER_TAXONOMY:
                return pkparameterRepository.save(csvPkparameterConverter.importTaxonomy(multipartFile));
            default:
                throw new IllegalStateException(ExceptionMessages.NO_SUCH_TAXONOMY_IMPORTER);
        }
    }

    public List<Research> getResearchsByFilter(final Filter filter) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Research> query = criteriaBuilder.createQuery(Research.class);
        final Root<Research> researchRoot = query.from(Research.class);
        final List<Predicate> predicates = predicateBuilder.buildPredicates(researchRoot, filter, criteriaBuilder);
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();
    }
}
