package com.skuce.aggropingpong.competition.service;

import com.skuce.aggropingpong.common.exception.CustomNotFoundException;
import com.skuce.aggropingpong.competition.domain.Competition;
import com.skuce.aggropingpong.competition.dto.CompetitionDetailResponseDto;
import com.skuce.aggropingpong.competition.repository.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompetitionReadService {

    private final CompetitionRepository competitionRepository;

    public CompetitionDetailResponseDto findById(Long id) {
        Competition entity = competitionRepository.findById(id).orElseThrow(
                () -> new CustomNotFoundException("Competition not found with id: " + id));
        return new CompetitionDetailResponseDto(entity);
    }

    public Page<CompetitionDetailResponseDto> findAllPage(Pageable pageable) {
        Page<Competition> entities = competitionRepository.findAll(pageable);
        return entities.map(CompetitionDetailResponseDto::new);
    }

    public Page<CompetitionDetailResponseDto> findPageByName(String name, Pageable pageable) {
        Page<Competition> entities = competitionRepository.findByNameContaining(name, pageable);
        return entities.map(CompetitionDetailResponseDto::new);
    }
}
