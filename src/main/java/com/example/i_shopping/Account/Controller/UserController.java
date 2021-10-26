package com.example.i_shopping.Account.Controller;

import com.example.i_shopping.Account.Dto.AccountForm;
import com.example.i_shopping.Account.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;

    @GetMapping("/login")     // 로그인 페이지 Controller
    public String LoginPage(){
        return "account/login";
    }

    @GetMapping("/signup")     // 회원가입 페이지 Controller
    public String SignupPage(Model model) {
        model.addAttribute("userForm",new AccountForm());
        return "account/signup";
    }
    @GetMapping("/usercheck")
    public String UserCheckpage(){
        return "account/usercheck";
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

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @PostMapping("/usercheck")
    public String UserCheck(HttpServletRequest request){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        HttpSession session = request.getSession();
        String pwd = request.getParameter("password");      //입력한 비밀번호 1
        String encoded = accountService.loadUserByUsername(session.getAttribute("userid").toString()).getPassword().replace("{bcrypt}","");         //로그인 되어있는 계정의 비밀번호 가져오기
        if(encoder.matches(pwd, encoded)) { //비밀번호 비교 matches
            return "account/mypage";
        }
        else{
            System.out.println("비밀번호 다름");
            return "account/usercheck";
        }
    }
}