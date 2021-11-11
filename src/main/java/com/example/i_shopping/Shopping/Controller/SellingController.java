package com.example.i_shopping.Shopping.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellingController {

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
}
