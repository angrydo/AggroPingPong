package com.skuce.aggropingpong.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;

@Getter
@Setter
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
    //<----생년월일
    //<----생성일자
    //<----수정일자
    private String region;
    private String tier;
    private String orgType;
    private String orgSubType;
    private String orgTeamname;
}
