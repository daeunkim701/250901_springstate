package com.example.springstate.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookieController {
    @GetMapping("/set-theme")
    public String setTheme(HttpServletResponse response) {
        Cookie themeCookie = new Cookie("theme", "dark");
        themeCookie.setPath("/"); // 같은 접속 정보를 공유하는 경우에 같이 쓸 수 있게
        themeCookie.setMaxAge(60 * 60 * 24 * 30); // 30일
        themeCookie.setHttpOnly(true); // httpOnly Cookie 보안 관련 설정임
        response.addCookie(themeCookie);
        return "redirect:/show-theme";
    }

    @GetMapping("/show-theme")
    public String showTheme(HttpServletRequest request, @CookieValue(name = "theme", defaultValue = "light") String theme, Model model) {
        model.addAttribute("currentTheme", theme);
        return "theme-display";
    }

}
