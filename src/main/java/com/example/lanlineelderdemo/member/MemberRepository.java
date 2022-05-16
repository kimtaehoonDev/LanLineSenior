package com.example.lanlineelderdemo.member;

import com.example.lanlineelderdemo.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Admin, Long> {

    public Optional<Admin> findByLoginId(String loginId);
}
