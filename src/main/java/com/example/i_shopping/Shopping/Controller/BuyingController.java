package com.example.i_shopping.Shopping.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuyingController {

    @GetMapping("/shopping_buy_main")
    public String buy_mainage(){
        return "/shop/buying/shopping_sell_main";
    }

    @GetMapping("/shopping_buy_woman")
    public String buy_woman_page(){
        return "/shop/buying/shopping_sell_woman";
    }

    @GetMapping("/shopping_buy_man")
    public String buy_man_page(){
        return "/shop/buying/shopping_sell_man";
    }
}
