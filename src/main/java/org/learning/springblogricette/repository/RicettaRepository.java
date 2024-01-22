package org.learning.springblogricette.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.learning.springblogricette.model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RicettaRepository extends JpaRepository<Ricetta, Integer> {
}
