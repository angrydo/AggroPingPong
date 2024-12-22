package com.skuce.aggropingpong.user.domain;

import com.skuce.aggropingpong.user.dto.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Role타입 import jakarta.persistence.*;-> 머임이거?
    @Enumerated(EnumType.STRING)
    private Role role;
    private String gender;
    private String name;
    private String email;
    private String nickname;
    private String username;
    private String password;
    //timestamp쓸줄모르겠음
    //<----생년월일
    //<----생성일자
    //<----수정일자
    private String region;
    private String tier;
    private String orgType;
    private String orgSubType;
    private String orgTeamname;

    public User update(UserUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.nickname = requestDto.getNickname();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        return this;
    }
}
