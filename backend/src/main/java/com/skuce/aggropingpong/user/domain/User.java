package com.skuce.aggropingpong.user.domain;

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
    @Column
    private String gender;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String nickname;
    @Column
    private String username;
    @Column
    private String password;
    //timestamp쓸줄모르겠음
    //<----생년월일
    //<----생성일자
    //<----수정일자

    @Column
    private String region;
    @Column
    private String tier;
    @Column
    private String orgType;
    @Column
    private String orgSubType;
    @Column
    private String orgTeamname;



}
