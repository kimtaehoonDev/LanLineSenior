package com.example.lanlineelderdemo.member;

import com.example.lanlineelderdemo.domain.Admin;
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

    public Long login(String loginId, String password) {
        Admin admin = findMember(loginId);
        validatePasswordIsSame(admin, password);
        validateGrantAdmin(admin);
        return admin.getId();
    }

    private void validateGrantAdmin(Admin admin) {
        if (!admin.getIsGrant()) {
            throw new IllegalArgumentException("권한이 아직 부여되지 않았습니다.");
        }
    }

    private void validatePasswordIsSame(Admin admin, String password) {
        if (!passwordEncoder.matches(password, admin.getPassword())){
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 잘못되었습니다.");
        }
    }

    private Admin findMember(String loginId) {
        Optional<Admin> parsingMember = memberRepository.findByLoginId(loginId);
        if (parsingMember.isEmpty()) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 잘못되었습니다.");
        }
        return parsingMember.get();
    }

    @Transactional
    public Long addAdmin(String loginId, String password) {
        validateDuplicateLoginId(loginId);
        Admin admin = new Admin(loginId, passwordEncoder.encode(password));
        memberRepository.save(admin);
        return admin.getId();
    }

    private void validateDuplicateLoginId(String loginId) {
        Optional<Admin> memberOptional = memberRepository.findByLoginId(loginId);
        if (!memberOptional.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 admin 이름입니다.");
        }
    }
}
