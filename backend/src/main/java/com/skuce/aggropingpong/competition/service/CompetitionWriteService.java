package com.skuce.aggropingpong.competition.service;

import com.skuce.aggropingpong.common.exception.CustomValidateException;
import com.skuce.aggropingpong.competition.dto.CompetitionCreateRequestDto;
import com.skuce.aggropingpong.competition.dto.CompetitionDetailResponseDto;
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
}
