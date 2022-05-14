package com.example.lanlineelderdemo.member;

import com.example.lanlineelderdemo.domain.Member;
import com.example.lanlineelderdemo.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    //Join 기능은 만들지 않기.? sql에 바로 집어넣을거.

    public Long login(String loginId, String password) {
        Member member = findMember(loginId);
        validatePasswordIsSame(member, password);
        validateGrantAdmin(member);
        return member.getId();
    }

    private void validateGrantAdmin(Member member) {
        if (!member.getIsGrant()) {
            throw new IllegalArgumentException("권한이 아직 부여되지 않았습니다.");
        }
    }

    private void validatePasswordIsSame(Member member, String password) {
        if (!passwordEncoder.matches(password, member.getPassword())){
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 잘못되었습니다.");
        }
    }

    private Member findMember(String loginId) {
        Optional<Member> parsingMember = memberRepository.findByLoginId(loginId);
        if (parsingMember.isEmpty()) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 잘못되었습니다.");
        }
        return parsingMember.get();
    }

    @Transactional
    public Long addAdmin(String loginId, String password) {
        validateDuplicateLoginId(loginId);
        Member member = new Member(loginId, passwordEncoder.encode(password));
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateLoginId(String loginId) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);
        if (!memberOptional.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 admin 이름입니다.");
        }
    }
}
