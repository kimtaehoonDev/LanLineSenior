package com.example.lanlineelderdemo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private Boolean isGrant;

    public Member(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
        isGrant = Boolean.FALSE;
    }
}