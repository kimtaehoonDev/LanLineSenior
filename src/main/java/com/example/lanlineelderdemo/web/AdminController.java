package com.example.lanlineelderdemo.web;

import com.example.lanlineelderdemo.web.form.admin.CreateForm;
import com.example.lanlineelderdemo.web.form.admin.LoginForm;
import com.example.lanlineelderdemo.member.MemberService;
import com.example.lanlineelderdemo.utils.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;

    @GetMapping("/info")
    public String adminForm() {
        return "adminPage";
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("createForm") CreateForm form) {
        return "adminRegisterForm";
    }

    @GetMapping("/login") // TODO 이거 숨겨버릴까 고민 한번 해보자.
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "loginForm";
    }

    @PostMapping("/login")// TODO 이거 숨겨버릴까 고민 한번 해보자.
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                        HttpServletRequest request) {
        try {
            Long loginMemberId = memberService.login(form.getLoginId(), form.getPassword());
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_ADMIN, loginMemberId);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            bindingResult.reject("loginFail", e.getMessage());
            return "loginForm";
        }
    }

    @PostMapping
    public String addAdmin(String loginId, String password) {
        memberService.addAdmin(loginId, password);
        return "redirect:/admin/login";
    }
}
