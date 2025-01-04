package com.skuce.aggropingpong.team.service;

import com.skuce.aggropingpong.common.exception.CustomNotFoundException;
import com.skuce.aggropingpong.team.domain.Team;
import com.skuce.aggropingpong.team.dto.TeamCreateRequestDto;
import com.skuce.aggropingpong.team.dto.TeamDetailResponseDto;
import com.skuce.aggropingpong.team.dto.TeamUpdateRequestDto;
import com.skuce.aggropingpong.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamWriteService {
    private final TeamRepository teamRepository;

    public TeamDetailResponseDto create(TeamCreateRequestDto requestDto) {
        return new TeamDetailResponseDto(teamRepository.save(requestDto.toEntity()));
    }

    public TeamDetailResponseDto update(TeamUpdateRequestDto requestDto) {
        Team entity = teamRepository.findById(requestDto.getId()).orElseThrow(
                () -> new CustomNotFoundException("Team not found with id: " + requestDto.getId()));
        return new TeamDetailResponseDto(teamRepository.save(entity.update(requestDto)));
    }

    public TeamDetailResponseDto delete(Long id) {
        Team entity = teamRepository.findById(id).orElseThrow(
                () -> new CustomNotFoundException("Team not found with id: " + id));
        teamRepository.deleteById(id);
        return new TeamDetailResponseDto(entity);
    }
}
