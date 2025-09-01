package com.example.springstate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 스캔하는 애
public class ExampleController {
    @GetMapping("/hello") // 이쪽으로 경로를 잡아라 hello라는 경로로 이동하게 함
    public String hello(Model model) {
        model.addAttribute("message", "안녕 나는 모델이야");
        model.addAttribute("username", "모델");
        return "hello";
    }
}
