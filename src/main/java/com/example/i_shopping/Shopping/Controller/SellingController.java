package com.example.i_shopping.Shopping.Controller;

import com.example.i_shopping.Shopping.Dto.SellingForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SellingController {

    @GetMapping("/shopping_credit_check")
    public String credit_checkpage(){
        return "/shop/shopping_credit_check";
    }

    @GetMapping("/shopping_sell_main")
    public String sell_mainage(){
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

    @PostMapping("/shopping_sell_man")
    public String sell_man_post(SellingForm form) throws Exception {

        return "";
    }

}
