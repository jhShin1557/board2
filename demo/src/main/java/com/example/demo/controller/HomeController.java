package com.example.demo.controller;

import com.example.demo.domain.ReferUrl;
import com.example.demo.domain.User;
import com.example.demo.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController {

    private final UserDao userDao;

    @Autowired
    public HomeController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String main(@SessionAttribute(name = "member", required = false) User member, Model model, HttpServletRequest request) {

        ReferUrl.savePreviousUrl(request);

        if (member == null) {
            return "page1/home";
        }
        User loginMember = userDao.findById(member.getId());
        model.addAttribute("member", loginMember);
        return "page1/home";
    }


    @GetMapping("/join")
    public String joinForm(@ModelAttribute("user") User user) {
        return "page1/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("user") User user, BindingResult bindingResult) {

        if (!StringUtils.hasText(user.getId())) {
            bindingResult.addError(new FieldError("user", "id","아이디: 필수 정보입니다."));
        }

        if (!StringUtils.hasText(user.getPassword())) {
            bindingResult.addError(new FieldError("user", "password", "비밀번호: 필수 정보입니다."));
        }

        if (!StringUtils.hasText(user.getName())) {
            bindingResult.addError(new FieldError("user", "name", "이름: 필수 정보입니다."));
        }

        if (!StringUtils.hasText(user.getNickname())) {
            bindingResult.addError(new FieldError("user", "nickname", "닉네임: 필수 정보입니다."));
        }

        if (user.getGender() == null) {
            bindingResult.addError(new FieldError("user", "gender", "성별: 필수 정보입니다."));
        }

        if (bindingResult.hasErrors()) {
            log.warn("회원가입 null 오류");
            return "page1/join";
        }

        User existUser = userDao.isExist(user);
        if (existUser != null) {
            bindingResult.addError(new FieldError("user", "id", user.getId(), false, null, null, "사용중인 아이디입니다."));
            return "page1/join";
        }

        userDao.join(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("user") User user, HttpServletRequest request) {
        ReferUrl.savePreviousUrl(request);
        return "page1/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request) {

        if (!StringUtils.hasText(user.getId())) {
            bindingResult.addError(new FieldError("user", "id","아이디를 입력해 주세요."));
        }

        if (!StringUtils.hasText(user.getPassword())) {
            bindingResult.addError(new FieldError("user", "password", "비밀번호를 입력해 주세요."));
        }

        if (bindingResult.hasErrors()) {
            log.warn("로그인 null 오류");
            return "page1/login";
        }

        User isCorrect = userDao.isCorrect(user);

        if (isCorrect == null) {
            bindingResult.addError(new ObjectError("user",  "아이디 또는 비밀번호를 잘못 입력했습니다."));
            return "page1/login";
        }

        User member = isCorrect;
        HttpSession session = request.getSession();
        session.setAttribute("member", member);
        String previousUrl = (String) session.getAttribute("previousUrl");

        if (previousUrl != null) {
            return "redirect:" + previousUrl;
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage(@SessionAttribute(name = "member", required = false) User member, Model model) {
        model.addAttribute("member", member);
        return "page1/myPage";
    }

}
