package com.example.i_shopping.Account.Controller;

import com.example.i_shopping.Account.Dto.AccountForm;
import com.example.i_shopping.Account.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;

    @GetMapping("/login")     // 로그인 페이지 Controller
    public String LoginPage(){
        return "login";
    }

    @GetMapping("/signup")     // 회원가입 페이지 Controller
    public String SignupPage(Model model) {
        model.addAttribute("userForm",new AccountForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(AccountForm form) throws Exception {
        try {
            accountService.save(form);
            System.out.println("회원가입 완료");
            return "redirect:/login";        // 성공 시, 로그인 페이지로
        } catch (Exception e) {
            System.out.println("회원가입 실패");
            return "redirect:/signup";
        }
    }

}