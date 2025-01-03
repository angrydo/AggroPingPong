package com.skuce.aggropingpong.team.repository;

import com.skuce.aggropingpong.team.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByNameContaining(String name);
    List<Team> findByAffiliationContaining(String affiliation);
    List<Team> findByNameContainingAndAffiliationContaining(String name, String affiliation);

    Page<Team> findAll(Pageable pageable);
    Page<Team> findByNameContaining(String name, Pageable pageable);
    Page<Team> findByAffiliationContaining(String affiliation, Pageable pageable);
    Page<Team> findByNameContainingAndAffiliationContaining(String name, String affiliation, Pageable pageable);
}
