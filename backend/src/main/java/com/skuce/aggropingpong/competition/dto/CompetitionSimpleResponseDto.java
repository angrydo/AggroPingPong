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
public class CompetitionSimpleResponseDto {
    private Long id;
    private String name;
    private Type type;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public CompetitionSimpleResponseDto(Competition entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.type = entity.getType();
        this.createdDate = entity.getCreatedDate();
        this.updatedDate = entity.getUpdatedDate();
    }
}
