package com.skuce.aggropingpong.team.controller;

import com.skuce.aggropingpong.common.dto.CommonResponse;
import com.skuce.aggropingpong.team.dto.TeamCreateRequestDto;
import com.skuce.aggropingpong.team.dto.TeamDetailResponseDto;
import com.skuce.aggropingpong.team.dto.TeamUpdateRequestDto;
import com.skuce.aggropingpong.team.service.TeamReadService;
import com.skuce.aggropingpong.team.service.TeamWriteService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamReadService teamReadService;
    private final TeamWriteService teamWriteService;

    @GetMapping("/api/v1/team/all/list")
    public ResponseEntity<CommonResponse<?>> getTeamList() {
        List<TeamDetailResponseDto> responseDto = teamReadService.findAllList();
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @GetMapping("/api/v1/team/all/page")
    public ResponseEntity<CommonResponse<?>> getTeamPage(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<TeamDetailResponseDto> responseDto = teamReadService.findAllPage(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @GetMapping("/api/v1/team/{id}")
    public ResponseEntity<CommonResponse<?>> getTeamById(@PathVariable Long id) {
        TeamDetailResponseDto responseDto = teamReadService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @GetMapping("/api/v1/team/search")
    public ResponseEntity<CommonResponse<?>> searchTeams(String name, String affiliation,
                                                         @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<TeamDetailResponseDto> responseDto = null;
        if (name != null && affiliation != null &&
                affiliation != null && !affiliation.isEmpty()) {
            responseDto = teamReadService.findPageByNameAndAffiliation(name, affiliation, pageable);
        } else if (name != null && !name.isEmpty()) {
            responseDto = teamReadService.findPageByName(name, pageable);
        } else if (affiliation != null && !affiliation.isEmpty()) {
            responseDto = teamReadService.findPageByAffiliation(affiliation, pageable);
        } else {
            responseDto = teamReadService.findAllPage(pageable);
        }
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @PostMapping("/api/v1/team/create")
    public ResponseEntity<CommonResponse<?>> createTeam(@RequestBody TeamCreateRequestDto requestDto) {
        TeamDetailResponseDto responseDto = teamWriteService.create(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @PutMapping("/api/v1/team/update")
    public ResponseEntity<CommonResponse<?>> updateTeam(@RequestBody TeamUpdateRequestDto requestDto) {
        TeamDetailResponseDto responseDto = teamWriteService.update(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @DeleteMapping("/api/v1/team/delete")
    public ResponseEntity<CommonResponse<?>> deleteTeam(@RequestParam("id") Long id) {
        TeamDetailResponseDto responseDto = teamWriteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }
}
