package com.example.i_shopping.Shopping.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @GetMapping("/shopping_main")
    public String basket_page(){
        return "/shop/shopping_main";
    }
}
