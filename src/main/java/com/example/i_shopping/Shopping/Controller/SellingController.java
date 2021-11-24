package com.example.i_shopping.Shopping.Controller;

import com.example.i_shopping.Account.Service.AccountService;
import com.example.i_shopping.Shopping.Dto.SellingForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
        String credit_check = (accountService.loadUserByUsername(userid).getCredit_check());
        session.setAttribute("credit_check",credit_check);


        return "/shop/selling/shopping_sell_main";
    }

    @GetMapping("/shopping_sell_woman")
    public String sell_woman_page(){
        return "/shop/selling/shopping_sell_woman";
    }

    @GetMapping("/shopping_sell_man")
    public String sell_man_page(){
        return "/shop/selling/shopping_sell_man";
    }

    @PostMapping("/shopping_credit_check")
    public String credit_auth(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();

        accountService.credit_auth_change(userid,"1");
        String credit_check = (accountService.loadUserByUsername(userid).getCredit_check());
        session.setAttribute("credit_check",credit_check);
        return "redirect:/shopping_sell_main";

    }

    @PostMapping("/shopping_sell_man")
    public String sell_man_post(SellingForm form) throws Exception {

        return "";
    }

}
