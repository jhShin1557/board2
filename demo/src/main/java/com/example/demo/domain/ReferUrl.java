package com.example.demo.domain;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class ReferUrl {
    public static void savePreviousUrl(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String previousUrl = request.getHeader("Referer");
        session.setAttribute("previousUrl", previousUrl);
    }
}
