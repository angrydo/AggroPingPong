package com.skuce.aggropingpong.post.dto;

import com.skuce.aggropingpong.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSimpleResponseDto {
    private Long id;
    private String title;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // Custom Constructor, convert data from entity to dto
    public PostSimpleResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.createdDate = entity.getCreatedDate();
        this.updatedDate = entity.getUpdatedDate();
    }
}
