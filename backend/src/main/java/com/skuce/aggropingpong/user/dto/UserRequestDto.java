package com.skuce.aggropingpong.user.dto;

import com.skuce.aggropingpong.user.domain.User;
import lombok.*;
import javax.management.relation.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private Role role;
    private String gender;
    private String name;
    private String email;
    private String nickname;
    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .role(this.role)
                .gender(this.gender)
                .name(this.name)
                .email(this.email)
                .nickname(this.nickname)
                .username(this.username)
                .password(this.password)
                .build();
    }
}
