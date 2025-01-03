package com.skuce.aggropingpong.team.service;

import com.skuce.aggropingpong.common.exception.CustomNotFoundException;
import com.skuce.aggropingpong.team.domain.Team;
import com.skuce.aggropingpong.team.dto.TeamDetailResponseDto;
import com.skuce.aggropingpong.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamReadService {

    private final TeamRepository teamRepository;

    public TeamDetailResponseDto findById(Long id) {
        Team entity = teamRepository.findById(id).orElseThrow(
                () -> new CustomNotFoundException("Team not found with id: " + id));
        return new TeamDetailResponseDto(entity);
    }

    // 리스트
    public List<TeamDetailResponseDto> findAllList() {
        List<Team> entities = teamRepository.findAll();
        return entities.stream().map(TeamDetailResponseDto::new).toList();
    }

    public List<TeamDetailResponseDto> findListByName(String name) {
        List<Team> entities = teamRepository.findByNameContaining(name);
        return entities.stream().map(TeamDetailResponseDto::new).toList();
    }

    public List<TeamDetailResponseDto> findListByAffiliation(String affiliation) {
        List<Team> entities = teamRepository.findByAffiliationContaining(affiliation);
        return entities.stream().map(TeamDetailResponseDto::new).toList();
    }

    public List<TeamDetailResponseDto> findListByNameAndAffiliation(String name, String affiliation) {
        List<Team> entities = teamRepository.findByNameContainingAndAffiliationContaining(name, affiliation);
        return entities.stream().map(TeamDetailResponseDto::new).toList();
    }

    // 페이징
    public Page<TeamDetailResponseDto> findAllPage(Pageable pageable) {
        Page<Team> entities = teamRepository.findAll(pageable);
        return entities.map(TeamDetailResponseDto::new);
    }

    public Page<TeamDetailResponseDto> findPageByName(String name, Pageable pageable) {
        Page<Team> entities = teamRepository.findByNameContaining(name, pageable);
        return entities.map(TeamDetailResponseDto::new);
    }

    public Page<TeamDetailResponseDto> findPageByAffiliation(String affiliation, Pageable pageable) {
        Page<Team> entities = teamRepository.findByAffiliationContaining(affiliation, pageable);
        return entities.map(TeamDetailResponseDto::new);
    }

    public Page<TeamDetailResponseDto> findPageByNameAndAffiliation(String name, String affiliation, Pageable pageable) {
        Page<Team> entities = teamRepository.findByNameContainingAndAffiliationContaining(name, affiliation, pageable);
        return entities.map(TeamDetailResponseDto::new);
    }
}
