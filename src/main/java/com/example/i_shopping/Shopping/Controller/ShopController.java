package com.example.i_shopping.Shopping.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @GetMapping("/posthome")
    public String mainpost(){
        return "/post/posthome";
    }

    @GetMapping("/freepostpage")
    public String freepost(){
        return "/post/freepostpage";
    }

    @GetMapping("/faqpostpage")
    public String faqpost(){
        return "/post/faqpostpage";
    }

    @GetMapping("/questionpostpage")
    public String questionpost(){
        return "/post/questionpostpage";
    }
}
