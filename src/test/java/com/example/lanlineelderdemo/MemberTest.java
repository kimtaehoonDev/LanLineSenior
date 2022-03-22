package com.example.lanlineelderdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class MemberTest {
    @Autowired MemberRepository memberRepository;

    @Test
    void 테스트() {
        Member member = new Member();
        member.setName("김");
        memberRepository.save(member);
    }

}