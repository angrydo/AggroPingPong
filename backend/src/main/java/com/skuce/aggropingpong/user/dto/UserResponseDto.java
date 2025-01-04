package com.skuce.aggropingpong.user.dto;

import com.skuce.aggropingpong.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String gender;
    private String nickname;
    private String email;
    private String username;
    private String password;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.gender = entity.getGender();
        this.nickname = entity.getNickname();
        this.email = entity.getName();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
    }
}
