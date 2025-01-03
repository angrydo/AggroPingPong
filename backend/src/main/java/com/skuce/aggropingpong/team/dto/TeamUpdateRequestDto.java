package com.skuce.aggropingpong.team.dto;

import com.skuce.aggropingpong.team.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamUpdateRequestDto {
    private Long id;
    private String name;
    private String affiliation;

    public Team toEntity() {
        return Team.builder()
                .id(this.id)
                .name(this.name)
                .affiliation(this.affiliation)
                .build();
    }
}
