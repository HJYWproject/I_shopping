package com.example.i_shopping.Account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")     // 메인 페이지 Controller
    public String MainPage(){
        return "main";
    }

    @GetMapping("/admin")     // 메인 페이지 Controller
    public String AdminMainPage(){
        return "admin";
    }

}
