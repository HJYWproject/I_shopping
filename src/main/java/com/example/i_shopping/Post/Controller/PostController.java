package com.example.i_shopping.Post.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

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
