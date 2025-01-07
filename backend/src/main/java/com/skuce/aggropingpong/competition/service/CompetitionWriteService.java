package com.skuce.aggropingpong.competition.service;

import com.skuce.aggropingpong.common.exception.CustomNotFoundException;
import com.skuce.aggropingpong.common.exception.CustomValidateException;
import com.skuce.aggropingpong.competition.domain.Competition;
import com.skuce.aggropingpong.competition.dto.CompetitionCreateRequestDto;
import com.skuce.aggropingpong.competition.dto.CompetitionDetailResponseDto;
import com.skuce.aggropingpong.competition.dto.CompetitionUpdateRequestDto;
import com.skuce.aggropingpong.competition.repository.CompetitionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CompetitionWriteService {

    private final CompetitionRepository competitionRepository;

    public CompetitionDetailResponseDto create(CompetitionCreateRequestDto requestDto) {
        if (requestDto.getName() == null || requestDto.getName().isEmpty())
            throw new CustomValidateException("The competition name must not be empty");
        return new CompetitionDetailResponseDto(competitionRepository.save(requestDto.toEntity()));
    }

    public CompetitionDetailResponseDto update(CompetitionUpdateRequestDto requestDto) {
        if (requestDto.getName() == null || requestDto.getName().isEmpty())
            throw new CustomValidateException("The competition name must not be empty.");

        Competition entity = competitionRepository.findById(requestDto.getId()).orElseThrow(
                () -> new CustomNotFoundException("competition not found with id: " + requestDto.getId()));
        return new CompetitionDetailResponseDto(competitionRepository.save(entity.update(requestDto)));
    }

    public CompetitionDetailResponseDto delete(Long id) {
        Competition entity = competitionRepository.findById(id).orElseThrow(
                () -> new CustomNotFoundException("competition not found with id: " + id));
        competitionRepository.deleteById(id);
        return new CompetitionDetailResponseDto(entity);
    }
}
