package com.tool.taxonomy.repository;

import com.tool.taxonomy.model.Pkparameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PkparameterRepository extends JpaRepository<Pkparameter, Long> {
    List<Pkparameter> findByName(String parameterName);
    List<Pkparameter> findByNameIn(List<String> parameterName);
}
