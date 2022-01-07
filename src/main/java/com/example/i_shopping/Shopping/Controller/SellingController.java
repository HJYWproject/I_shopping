package com.example.i_shopping.Shopping.Controller;

import com.example.i_shopping.Account.Service.AccountService;
import com.example.i_shopping.Shopping.Dto.SellingForm;
import com.example.i_shopping.Shopping.Service.SellingService;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

import java.nio.file.Files;
import java.sql.Blob;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class SellingController {
    private final AccountService accountService;
    private final SellingService sellingService;

    public SellingController(AccountService accountService, SellingService sellingService) {
        this.accountService = accountService;
        this.sellingService = sellingService;
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
        //if (Objects.equals(check, "0")) {
        System.out.println(check);
        if (!Objects.equals(check, "1")) {
            System.out.println("인증이 필요합니다.");
            return "/shop/selling/shopping_sell_main";
        }
        else
            return "/shop/selling/shopping_sell_woman";
    }

    @GetMapping("/shopping_sell_man")
    public String sell_man_page(HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        String check = session.getAttribute("credit_check").toString();
        if (!Objects.equals(check, "1")) {
            System.out.println("인증이 필요합니다.");
            return "/shop/selling/shopping_sell_main";
        }
        else
            return "/shop/selling/shopping_sell_man";
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
    public String sell_man_Post(SellingForm form, HttpServletRequest request ) throws Exception{
        HttpSession session = request.getSession();
        String user = session.getAttribute("userid").toString();

        byte[] tmp = Base64Utils.decodeFromUrlSafeString(form.getImageProduct());
        System.out.println(tmp);

        form.setUsername(user);
        form.setSex("m");

        System.out.println(form);
        sellingService.save(form);
        return "shop/selling/shopping_sell_main";
    }

    @PostMapping("/shopping_sell_woman")
    public String sell_woman_Post(SellingForm form, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String user = session.getAttribute("userid").toString();
        form.setUsername(user);
        form.setSex("w");
        System.out.println(form);
        sellingService.save(form);
        return "shop/selling/shopping_sell_main";
    }
}
