package com.example.i_shopping.Shopping.Controller;

import com.example.i_shopping.Shopping.Dto.SellingForm;
import com.example.i_shopping.Shopping.Service.SellingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BuyingController {
    private final SellingService sellingService;

    public BuyingController(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @GetMapping("/shopping_buy_main")
    public String buy_mainage(){
        List<SellingForm> forms = sellingService.getClothes();
        System.out.println(forms);
        return "/shop/buying/shopping_buy_main";
    }

    @GetMapping("/shopping_buy_woman")
    public String buy_woman_page(){
        List<SellingForm> forms = sellingService.getWomanClothes();
        System.out.println(forms);
        return "/shop/buying/shopping_buy_woman";
    }

    @GetMapping("/shopping_buy_man")
    public String buy_man_page(){
        List<SellingForm> forms = sellingService.getManClothes();
        System.out.println(forms);
        return "/shop/buying/shopping_buy_man";
    }
}
