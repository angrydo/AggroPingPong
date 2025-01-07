package com.skuce.aggropingpong.competition.dto;

import com.skuce.aggropingpong.competition.domain.Competition;
import com.skuce.aggropingpong.competition.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionCreateRequestDto {
    private String name;
    private Type type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime applyStartDate;
    private LocalDateTime applyEndDate;

    public Competition toEntity() {
        return Competition.builder()
                .name(this.name)
                .type(this.type)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .applyStartDate(this.applyStartDate)
                .applyEndDate(this.applyEndDate)
                .build();
    }
}
