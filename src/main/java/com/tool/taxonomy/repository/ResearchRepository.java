package com.tool.taxonomy.repository;

import com.tool.taxonomy.model.Research;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ResearchRepository extends JpaRepository<Research, Long>{
}
