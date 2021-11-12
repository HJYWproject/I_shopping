package com.example.i_shopping.Post.Controller;

import com.example.i_shopping.Post.Domain.FreePostEntity;
import com.example.i_shopping.Post.Dto.FreePostForm;
import com.example.i_shopping.Post.Repository.FreePostRepository;
import com.example.i_shopping.Post.Service.FreePostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FreePostController {
    HttpSession session;
    String user;
    private final FreePostService freepostService;
    private final FreePostRepository freepostRepository;
    public FreePostController(FreePostService freepostService, FreePostRepository freepostRepository) {
        this.freepostService = freepostService;
        this.freepostRepository = freepostRepository;
    }

    @GetMapping("/posthome")
    public String mainpost(){ return "/post/posthome";
    }

    @GetMapping("/freepostpage/edit")
    public String freepost_edit(){ return "/post/freepost/freepostedit";
    }

    @GetMapping("/freepostpage/read/{id}")  //수정하기 위해 읽기
    public String freepostRead(@PathVariable("id") long id, Model model) throws Exception{
        FreePostForm freepostForm = freepostService.getPost(id);

        FreePostEntity postEntity = freepostRepository.findById(id).get();
        //List<CommentEntity> commentEntityList= commentRepository.findCommentsByPostEntity(postEntity);

        model.addAttribute("postRead", freepostForm);
        //model.addAttribute("commentList",commentEntityList);
        user = freepostForm.getUserid();
        return "/post/freepost/freepostpage/read";
    }

    @GetMapping("/freepostpage/delete/{id}")  //삭제
    public String PostDelete(@PathVariable("id") long id, HttpServletRequest request) throws Exception{
        if(!user.equals(request.getSession().getAttribute("userid"))){
            System.out.println("아이디가 다름");
            return "redirect:/post/freepost/freepostpage";
        }
        else{
            freepostService.postDelete(id);
            return "redirect:/post/freepost/freepostpage";
        }
    }

    @GetMapping("/freepostpage/update/{id}")
    public String GetUpdate(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (!user.equals(request.getSession().getAttribute("userid"))) {
            System.out.println("아이디가 다름");
            return "redirect:/post/freepost/freepostpage";
        } else {
            FreePostForm freepostForm = freepostService.getPost(id);
            model.addAttribute("postList", freepostForm);
            return "/post/freepost/freepostpage/update";
        }
    }
    @GetMapping("/freepostpage")
    public String freepost(Model model){
        List<FreePostForm> freepostList = freepostService.getPostList();
        model.addAttribute("freepostList", freepostList);
        return "/post/freepost/freepostpage";
    }

    @CrossOrigin(origins="*")
    @PostMapping("/freepostpage/edit")
    public String freepost_edit_post(FreePostForm freepostForm){
        System.out.println("???");
        System.out.println(freepostForm.getContent());
        //freepostService.save(freepostForm);
        System.out.println(freepostForm.getContent());
        return "redirect:/freepostpage";
    }

    @PutMapping("/freepostpage/update/{id}")
    public String PostUpdate(@PathVariable("id") long id, FreePostForm freepostForm) {
        freepostForm.setId(id);
        freepostService.save(freepostForm);
        return "redirect:/post/freepost/freepostpage";
    }

}
