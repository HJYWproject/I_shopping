package com.example.i_shopping.Shopping.Controller;

import com.example.i_shopping.Account.Service.AccountService;
import com.example.i_shopping.Shopping.Dto.SellingForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class SellingController {
    private final AccountService accountService;

    public SellingController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/shopping_credit_check")
    public String credit_checkpage(){
        return "/shop/selling/shopping_credit_check";
    }

    @GetMapping("/shopping_sell_main")
    public String sell_mainpage(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String userid = session.getAttribute("userid").toString();
            String credit_check = (accountService.loadUserByUsername(userid).getCredit_check());
            session.setAttribute("credit_check", credit_check);
            return "/shop/selling/shopping_sell_main";
        }catch(Exception e){
            return "/shop/selling/shopping_sell_main";
        }
    }

    @GetMapping("/shopping_sell_woman")
    public String sell_woman_page(HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        String check = session.getAttribute("credit_check").toString();
        if (Objects.equals(check, "0")) {
            System.out.println("인증이 필요합니다.");
            return "/shop/selling/shopping_sell_woman";
        }
        else
            return "/shop/selling/shopping_sell_main";
    }

    @GetMapping("/shopping_sell_man")
    public String sell_man_page(HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        String check = session.getAttribute("credit_check").toString();
        if (Objects.equals(check, "0")) {
            System.out.println("인증이 필요합니다.");
            return "/shop/selling/shopping_sell_man";
        }
        else
            return "/shop/selling/shopping_sell_main";
    }

    @PostMapping("/shopping_credit_auth")
    public String credit_auth(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();

            String userid = session.getAttribute("userid").toString();

            accountService.credit_auth_change(userid, "1");
            String credit_check = (accountService.loadUserByUsername(userid).getCredit_check());
            System.out.println("신용 인증 성공");
            session.setAttribute("credit_check", credit_check);
            return "shop/selling/shopping_sell_main";
        } catch (Exception e) {
            System.out.println("신용 인증 실패");
            return "shop/selling/shopping_sell_main";
        }
    }

    @PostMapping("/shopping_sell_man")
    public String sell_man_Post(SellingForm form, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String user = session.getAttribute("userid").toString();
        form.setUsername(user);
        System.out.println(form);

        return "/shop/selling/shopping_sell_main";
    }
}
