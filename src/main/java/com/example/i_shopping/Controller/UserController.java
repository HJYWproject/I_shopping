package com.example.i_shopping.Controller;

import com.example.i_shopping.Dto.AccountForm;
import com.example.i_shopping.Repository.AccountRepository;
import com.example.i_shopping.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;
    @PostMapping("/signup")
    public String createUser(AccountForm form) throws Exception{
        try {
            accountService.findByUsername((form.getUsername()));
            accountService.createUser(form);
            System.out.println("회원가입 완료");
            return "redirect:/login";        // 성공 시, 로그인 페이지로
        }catch(Exception e){
            System.out.println("회원가입 실패");
            return "redirect:/signup";
        }
    }
}

