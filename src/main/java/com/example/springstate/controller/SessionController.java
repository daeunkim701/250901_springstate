package com.example.springstate.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class SessionController {

    @GetMapping("/visit")
//    public String trackVisit(HttpServletRequest request, Model model) {
    public String trackVisit(HttpSession session,
//                             @SessionAttribute("visitCount") Integer visitCount,
                             @SessionAttribute(value = "visitCount", required = false) Integer visitCount,
                             Model model) {
        // JSESSIONID - 식별자 -> 서버가 붙이는 식별자
//        HttpSession session = request.getSession();

        // 세션에서 visitCount -> String -> attribute
//        Integer visitCount = (Integer) session.getAttribute("visitCount");
//        Integer visitCount = (Integer) session.getAttribute("visitCount");

        if (visitCount == null) { // 개발자 도구-Application-Cookies 지우면 1부터 다시 시작함
            visitCount = 1;
        } else {
            visitCount++; // 새로고침 할 때마다 1씩 올라감
        }
        session.setAttribute("visitCount", visitCount);
        model.addAttribute("visitCount", visitCount);
        return "visit-count";
    }

    @GetMapping("/logout-session")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/visit";
    }
}