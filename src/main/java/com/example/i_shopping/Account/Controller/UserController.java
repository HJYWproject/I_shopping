package com.example.i_shopping.Account.Controller;

import com.example.i_shopping.Account.Dto.AccountForm;
import com.example.i_shopping.Account.Repository.AccountRepository;
import com.example.i_shopping.Account.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @GetMapping("/login")     // 로그인 페이지 Controller
    public String LoginPage() {
        return "account/login";
    }

    @GetMapping("/signup")     // 회원가입 페이지 Controller
    public String SignupPage(Model model) {
        model.addAttribute("userForm", new AccountForm());
        return "account/signup";
    }

    @GetMapping("/usercheck")
    public String UserCheckpage() {
        return "account/usercheck";
    }

    @GetMapping("/delete_user")
    public String DeleteUserGet() {
        return "account/delete_user";
    }

    @GetMapping("/change_pwd")
    public String ChangePassword() {
        return "account/change_pwd";
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
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @PostMapping("/usercheck")
    public String UserCheck(HttpServletRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        HttpSession session = request.getSession();
        String pwd = request.getParameter("password");      //입력한 비밀번호 1
        String encoded = accountService.loadUserByUsername(session.getAttribute("userid").toString()).getPassword().replace("{bcrypt}", "");         //로그인 되어있는 계정의 비밀번호 가져오기
        if (encoder.matches(pwd, encoded)) { //비밀번호 비교 matches
            return "account/mypage";
        } else {
            System.out.println("비밀번호 다름");
            return "account/usercheck";
        }
    }

    @PostMapping("/delete_user")
    public String DeleteUserPost(HttpServletRequest request, HttpServletResponse response) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        HttpSession session = request.getSession();
        String pwd = request.getParameter("password");      //입력한 비밀번호 1
        String encoded = accountService.loadUserByUsername(session.getAttribute("userid").toString()).getPassword().replace("{bcrypt}", "");         //로그인 되어있는 계정의 비밀번호 가져오기
        if (encoder.matches(pwd, encoded)) { //비밀번호 비교 matches
            //여기에 회원 탈퇴 메소드
            accountService.deleteUser(session.getAttribute("userid").toString());

            // 로그아웃 메소드
            new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/";
        } else {
            System.out.println("비밀번호 다름");
            return "account/delete_user";
        }
    }

    @PostMapping("/change_pwd")
    public String ChangePwd(HttpServletRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        HttpSession session = request.getSession();
        String old_pwd = request.getParameter("password1");      //입력한 비밀번호 1
        String new_pwd = request.getParameter("password2");      //입력한 비밀번호 1
        String new_pwd_check = request.getParameter("password3");      //입력한 비밀번호 1
        String encoded = accountService.loadUserByUsername(session.getAttribute("userid").toString()).getPassword().replace("{bcrypt}", "");         //로그인 되어있는 계정의 비밀번호 가져오기
        if (encoder.matches(old_pwd, encoded)) { //비밀번호 비교 matches
            if (Objects.equals(new_pwd, new_pwd_check)) {
                accountService.changepassword(new_pwd);
                return "/";
            } else {
                System.out.println("비밀번호 확인 일치하지 않음");
                return "account/change_pwd";
            }
        }
        else{
                System.out.println("현재 비밀번호 다름");
                return "account/change_pwd";
            }
            return "redirect:/";
        }

    }