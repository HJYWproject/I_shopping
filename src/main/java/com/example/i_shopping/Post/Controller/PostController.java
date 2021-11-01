package com.example.i_shopping.Post.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping("/post")
    public String freepost(){
        return "/post/post";
    }
}
