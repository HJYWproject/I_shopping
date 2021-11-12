package com.example.i_shopping.Post.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionPostController {

    @GetMapping("/questionpostpage")
    public String questionpost(){
        return "/post/questionpostpage";
    }
}
