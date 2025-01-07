package com.skuce.aggropingpong.competition.repository;

import com.skuce.aggropingpong.competition.domain.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Page<Competition> findAll(Pageable pageable);
    Page<Competition> findByNameContaining(String name, Pageable pageable);
}
