package com.skuce.aggropingpong.user.dto;

import com.skuce.aggropingpong.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {
    private Long id;
    private String name;
    private String email;
    private String nickname;
    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .nickname(this.nickname)
                .username(this.username)
                .password(this.password)
                .build();
    }
}
