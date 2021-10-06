package com.example.i_shopping.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")     // 메인 페이지 Controller
    public String MainPage(){
        return "main";
    }

    @GetMapping("/login")     // 로그인 페이지 Controller
    public String LoginPage(){
        return "login";
    }

    @GetMapping("/signup")     // 회원가입 페이지 Controller
    public String SignupPage(){
        return "signup";
    }
}
