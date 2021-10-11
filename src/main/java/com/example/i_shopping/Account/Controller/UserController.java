package com.example.i_shopping.Account.Controller;

import com.example.i_shopping.Account.Dto.AccountForm;
import com.example.i_shopping.Account.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;

    @PostMapping("/signup")
    public String createUser(AccountForm form) throws Exception {
        try {
            accountService.findByUsername((form.getUsername()));
            accountService.createUser(form);
            System.out.println("회원가입 완료");
            return "redirect:/login";        // 성공 시, 로그인 페이지로
        } catch (Exception e) {
            System.out.println("회원가입 실패");
            return "redirect:/signup";
        }
    }


    @PostMapping("/login")
    public String LoginUser(AccountForm form) throws Exception {
        try {
            accountService.findByUsername((form.getUsername()));
            System.out.println("로그인 완료");
            return "redirect:/";        // 성공 시, 로그인 페이지로
        } catch (Exception e) {
            System.out.println("로그인 실패");
            return "redirect:/login";
        }
    }
}