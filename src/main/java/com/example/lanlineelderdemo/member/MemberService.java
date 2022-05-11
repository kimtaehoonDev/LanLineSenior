package com.example.lanlineelderdemo.member;

import com.example.lanlineelderdemo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    //Join 기능은 만들지 않기.? sql에 바로 집어넣을거.

    public Long login(String loginId, String password) {
        Member member = findMember(loginId);
        System.out.println("CHECK, Service: "+member);
        if (member.checkPasswordIsSame(password)) {
            return member.getId();
        }
        throw new IllegalArgumentException("아이디 혹은 비밀번호가 잘못되었습니다.");
    }

    private Member findMember(String loginId) {
        Optional<Member> parsingMember = memberRepository.findByLoginId(loginId);
        if (parsingMember.isEmpty()) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 잘못되었습니다.");
        }
        return parsingMember.get();
    }
}
