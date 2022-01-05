package com.example.i_shopping.Shopping.Controller;

import com.example.i_shopping.Shopping.Dto.SellingForm;
import com.example.i_shopping.Shopping.Dto.SellingManForm;
import com.example.i_shopping.Shopping.Service.SellingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class BuyingController {
    private final SellingService sellingService;

    public BuyingController(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @GetMapping("/shopping_buy_main")
    public String buy_mainage(Model model){
        List<SellingForm> all_forms = sellingService.getClothes();
        model.addAttribute("All_Clothes", all_forms);
        //System.out.println(all_forms);
        return "/shop/buying/shopping_buy_main";
    }

    @GetMapping("/shopping_buy_woman")
    public String buy_woman_page(Model model){
        List<SellingManForm> woman_forms = sellingService.getWomanClothes();
        model.addAttribute("Woman_Clothes", woman_forms);
        //System.out.println(forms);
        return "/shop/buying/shopping_buy_woman";
    }

    @GetMapping("/shopping_buy_man")
    public String buy_man_page(Model model){
        List<SellingForm> man_forms = sellingService.getManClothes();
        model.addAttribute("Man_Clothes", man_forms);
        //System.out.println(forms);
        return "/shop/buying/shopping_buy_man";
    }
}
