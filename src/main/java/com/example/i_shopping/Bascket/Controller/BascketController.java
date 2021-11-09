package com.example.i_shopping.Bascket.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BascketController {

    @GetMapping("/market_bascket")
    public String basket_page(){
        return "/account/bascket";
    }

}
