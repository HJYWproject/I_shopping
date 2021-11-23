package com.example.i_shopping.Post.Controller;

import com.example.i_shopping.Post.Domain.FaqPostEntity;
import com.example.i_shopping.Post.Dto.FaqPostForm;
import com.example.i_shopping.Post.Repository.FaqPostRepository;
import com.example.i_shopping.Post.Service.FaqPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FaqPostController {
    HttpSession session;
    private final FaqPostService faqpostService;
    private final FaqPostRepository faqpostRepository;
    String user;

    public FaqPostController(FaqPostService faqpostService, FaqPostRepository faqpostRepository) {
        this.faqpostService = faqpostService;
        this.faqpostRepository = faqpostRepository;
    }

    @GetMapping("/faqpostpage")
    public String faqpost(Model model) throws Exception{
        List<FaqPostForm> faqpostList = faqpostService.getPostList();
        model.addAttribute("faqpostList", faqpostList);
        return "/post/faqpost/faqpostpage";
    }

    @GetMapping("/faqpostpage/faqpostedit")
    public String faqpost_edit(){ return "/post/faqpost/faqpostedit";}

    @GetMapping("/faqpostpage/read/{id}")  //수정하기 위해 읽기
    public String faqpostRead(@PathVariable("id") long id, Model model) throws Exception{
        FaqPostForm faqpostForm = faqpostService.getPost(id);

        FaqPostEntity postEntity = faqpostRepository.findById(id).get();
        FaqPostEntity faqpostEntity = faqpostRepository.findById(id).get();

        model.addAttribute("faqpostRead",faqpostForm);
        user = faqpostForm.getUserid();
        return "/post/faqpost/faqpostread";
    }

    @GetMapping("/faqpostpage/delete/{id}")  //삭제
    public String faqPostDelete(@PathVariable("id") long id, HttpServletRequest request) throws Exception{
        if(!user.equals(request.getSession().getAttribute("userid"))){
            System.out.println("아이디가 다름");
            return "redirect:/faqpostpage";
        }
        else{
            faqpostService.postDelete(id);
            return "redirect:/faqpostpage";
        }
    }

    @GetMapping("/faqpostpage/update/{id}")
    public String faqGetUpdate(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        if (!user.equals(request.getSession().getAttribute("userid"))) {
            System.out.println("아이디가 다름");
            return "redirect:/post/faqpost/faqpostpage";
        } else {
            FaqPostForm faqpostForm = faqpostService.getPost(id);
            model.addAttribute("postList", faqpostForm);
            return "/post/faqpost/faqpostupdate";
        }
    }

    @PostMapping("/faqpostpage/faqpostedit")
    public String faqpost_edit_post(FaqPostForm faqpostForm){
        System.out.println("게시글 저장 완료");
        faqpostService.save(faqpostForm);
        return "redirect:/faqpostpage";
    }

    @PostMapping("/faqpostpage/update/{id}")
    public String faqPostUpdate(@PathVariable("id") long id, FaqPostForm faqpostForm) {
        faqpostForm.setId(id);
        faqpostService.save(faqpostForm);
        return "redirect:/faqpostpage/read/{id}";
    }

}
