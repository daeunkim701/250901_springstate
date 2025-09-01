package com.example.springstate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;

@Controller // 스캔하는 애
public class ExampleController {
    @GetMapping("/hello") // 이쪽으로 경로를 잡아라 hello라는 경로로 이동하게 함
    public String hello(Model model) {
        model.addAttribute("message", "안녕 나는 모델이야");
        model.addAttribute("username", "모델");
        return "hello";
    }
    @PostMapping("/save-data")
    public String saveData(@RequestParam String data,
                           RedirectAttributes redirectAttributes
                           // Model model
    ) {
        Random rd = new Random();
        boolean success = rd.nextBoolean(); // 반반 확률
        if (success) {
            // model.addAttribute("message", data + "가 성공적으로 저장되었습니다.");
            redirectAttributes.addAttribute("message", data + "가 성공적으로 저장되었습니다.");
            redirectAttributes.addFlashAttribute("message2", data + "가 성공적으로 저장되었습니다.");
        } else {
            // model.addAttribute("message", data + "의 저장에 실패했습니다.");
            redirectAttributes.addAttribute("message", data + "의 저장에 실패했습니다.");
            redirectAttributes.addFlashAttribute("message2", data + "의 저장에 실패했습니다.");
        }
//        return "result";
        return "redirect:/result"; // PRG
    }
    @GetMapping("/result")
    public String result(@RequestParam String message, Model model) {
        // redirectAttributes.addAttribute 로 전달하면 RequestParam (QueryString, SearchParam이라고 불리는 ? 이후에 있는 값들...)
        model.addAttribute("message", message);
        return "result";
    }
}
