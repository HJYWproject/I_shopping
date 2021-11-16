package com.example.i_shopping.Post.Controller;

import com.example.i_shopping.Post.Domain.CommentEntity;
import com.example.i_shopping.Post.Domain.FreePostEntity;
import com.example.i_shopping.Post.Dto.FreePostForm;
import com.example.i_shopping.Post.Repository.CommentRepository;
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
    private final CommentRepository commentRepository;
    public FreePostController(FreePostService freepostService, FreePostRepository freepostRepository, CommentRepository commentRepository) {
        this.freepostService = freepostService;
        this.freepostRepository = freepostRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/posthome")
    public String mainpost(){ return "/post/posthome";
    }

    @GetMapping("/freepostpage/freepostedit")
    public String freepost_edit(){ return "/post/freepost/freepostedit";
    }

    @GetMapping("/freepostpage/read/{id}")  //수정하기 위해 읽기
    public String freepostRead(@PathVariable("id") long id, Model model) throws Exception{
        FreePostForm freepostForm = freepostService.getPost(id);

        FreePostEntity postEntity = freepostRepository.findById(id).get();
        FreePostEntity freepostEntity = freepostRepository.findById(id).get();
        List<CommentEntity> commentEntityList= commentRepository.findCommentsByFreepostEntity(freepostEntity);
        model.addAttribute("commentList", commentEntityList);


        model.addAttribute("postRead", freepostForm);
        user = freepostForm.getUserid();
        return "/post/freepost/freepostread";
    }

    @GetMapping("/freepostpage/delete/{id}")  //삭제
    public String PostDelete(@PathVariable("id") long id, HttpServletRequest request) throws Exception{
        if(!user.equals(request.getSession().getAttribute("userid"))){
            System.out.println("아이디가 다름");
            return "redirect:/freepostpage";
        }
        else{
            freepostService.postDelete(id);
            return "redirect:/freepostpage";
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
            return "/post/freepost/freepostupdate";
        }
    }
    @GetMapping("/freepostpage")
    public String freepost(Model model){
        List<FreePostForm> freepostList = freepostService.getPostList();
        model.addAttribute("freepostList", freepostList);
        System.out.println(model);
        return "/post/freepost/freepostpage";
    }

    //@CrossOrigin(origins="*")
    @PostMapping("/freepostpage/freepostedit")
    public String freepost_edit_post(FreePostForm freepostForm){
        System.out.println("게시글 저장 완료");
        freepostService.save(freepostForm);
        return "redirect:/freepostpage";
    }

    @PostMapping("/freepostpage/update/{id}")
    public String PostUpdate(@PathVariable("id") long id, FreePostForm freepostForm) {
        freepostForm.setId(id);
        freepostService.save(freepostForm);
        return "redirect:/freepostpage/read/{id}";
    }

}
