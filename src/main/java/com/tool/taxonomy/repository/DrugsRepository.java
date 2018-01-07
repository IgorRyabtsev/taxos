package com.tool.taxonomy.repository;

import com.tool.taxonomy.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DrugsRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByName(String drugName);
    List<Drug> findByNameIn(List<String> drugName);
}
