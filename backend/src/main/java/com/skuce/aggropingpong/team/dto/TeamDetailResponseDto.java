package com.skuce.aggropingpong.team.dto;

import com.skuce.aggropingpong.team.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDetailResponseDto {
    private Long id;
    private String name;
    private String affiliation;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // Custom Constructor, convert data from entity to dto
    public TeamDetailResponseDto(Team entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.affiliation = entity.getAffiliation();
        this.createdDate = entity.getCreatedDate();
        this.updatedDate = entity.getUpdatedDate();
    }
}
