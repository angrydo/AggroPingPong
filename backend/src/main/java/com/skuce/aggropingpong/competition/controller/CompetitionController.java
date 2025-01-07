package com.skuce.aggropingpong.competition.controller;

import com.skuce.aggropingpong.common.dto.CommonResponse;
import com.skuce.aggropingpong.competition.dto.CompetitionCreateRequestDto;
import com.skuce.aggropingpong.competition.dto.CompetitionDetailResponseDto;
import com.skuce.aggropingpong.competition.dto.CompetitionUpdateRequestDto;
import com.skuce.aggropingpong.competition.service.CompetitionReadService;
import com.skuce.aggropingpong.competition.service.CompetitionWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionWriteService competitionWriteService;
    private final CompetitionReadService competitionReadService;

    @GetMapping("/api/v1/competition/{id}")
    public ResponseEntity<CommonResponse<?>> getCompetitionById(@PathVariable Long id) {
        CompetitionDetailResponseDto responseDto = competitionReadService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    /**
     **  getCompetitionList 필요시 구현
     **/

    @GetMapping("api/v1/competition/all/page")
    public ResponseEntity<CommonResponse<?>> getCompetitionPage(
            @PageableDefault(page = 0, size = 0) Pageable pageable) {
        Page<CompetitionDetailResponseDto> responseDto = competitionReadService.findAllPage(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @GetMapping("api/v1/competition/search")
    public ResponseEntity<CommonResponse<?>> searchCompetitions(String name,
                                                                @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<CompetitionDetailResponseDto> responseDto = competitionReadService.findPageByName(name, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @PostMapping("/api/v1/competition/create")
    public ResponseEntity<CommonResponse<?>> createCompetition(@RequestBody CompetitionCreateRequestDto requestDto) {
        CompetitionDetailResponseDto responseDto = competitionWriteService.create(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @PutMapping("/api/v1/competition/update")
    public ResponseEntity<CommonResponse<?>> updateCompetition(@RequestBody CompetitionUpdateRequestDto requestDto) {
        CompetitionDetailResponseDto responseDto = competitionWriteService.update(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @DeleteMapping("/api/v1/competition/delete/{id}")
    public ResponseEntity<CommonResponse<?>> deleteCompetition(@PathVariable Long id) {
        CompetitionDetailResponseDto responseDto = competitionWriteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }
}
