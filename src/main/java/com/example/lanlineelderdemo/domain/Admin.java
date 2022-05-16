package com.example.lanlineelderdemo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private Boolean isGrant;

    public Admin(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
        isGrant = Boolean.FALSE;
    }
}