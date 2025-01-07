package com.skuce.aggropingpong.competition.domain;

import com.skuce.aggropingpong.competition.dto.CompetitionUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime applyStartDate;
    private LocalDateTime applyEndDate;

    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    // Method, for updating data
    public Competition update(CompetitionUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.type = requestDto.getType();
        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.applyStartDate = requestDto.getApplyStartDate();
        this.applyEndDate = requestDto.getApplyEndDate();
        return this;
    }
}
