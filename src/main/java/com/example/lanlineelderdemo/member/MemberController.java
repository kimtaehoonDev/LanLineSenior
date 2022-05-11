package com.example.lanlineelderdemo.member;

import com.example.lanlineelderdemo.SessionConst;
import com.example.lanlineelderdemo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/admin/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "loginForm";
    }

    @PostMapping("/admin/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "loginForm";
        }
        try {
            Long loginMemberId = memberService.login(form.getLoginId(), form.getPassword());
            System.out.println("CHECK, Controller: " + loginMemberId);
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_ADMIN, loginMemberId);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            bindingResult.reject("loginFail", e.getMessage());
            return "loginForm";
        }
        // TODO 아이디, 패스워드 입력해도 틀린 이유 찾기.
    }
}
