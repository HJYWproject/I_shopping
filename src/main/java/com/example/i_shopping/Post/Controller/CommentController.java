package com.example.i_shopping.Post.Controller;

import com.example.i_shopping.Post.Domain.CommentEntity;
import com.example.i_shopping.Post.Domain.FreePostEntity;
import com.example.i_shopping.Post.Dto.CommentForm;
import com.example.i_shopping.Post.Repository.CommentRepository;
import com.example.i_shopping.Post.Repository.FreePostRepository;
import com.example.i_shopping.Post.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    FreePostRepository freepostRepository;
    HttpSession session;
    @Autowired
    CommentRepository commentRepository;

    public CommentController(CommentService commentService) { this.commentService = commentService;}

    @GetMapping("/freepostpage/read/{id}/comment") //json으로 넘어옴
    public String getPostComments(@PathVariable Long id, Model model){
        return "redirect:/freepostpage/read/{id}";
    }

    @PostMapping("/freepostpage/read/{id}/comment/create")
    public String createComment(@PathVariable Long id, CommentForm commentForm){
        Optional<FreePostEntity> postItem = freepostRepository.findById(id);

        commentForm.setFreepostEntity(postItem.get());
        //commentRepository.save(commentForm.toEntity());
        commentService.save(commentForm);
        return "redirect:/freepostpage/read/{id}";
    }

    @PutMapping("/freepostpage/read/{id}/comment/update/{commentID}")        //댓글 수정 기능
    public String updateComment(@PathVariable Long id,@PathVariable Long commentID, CommentForm commentForm){
        Optional<FreePostEntity> postItem = freepostRepository.findById(id);
        commentForm.setFreepostEntity(postItem.get());

        commentForm.setId(commentID);
        commentService.update(commentForm);
        return "redirect:/freepostpage/read/{id}";
    }

    @DeleteMapping("/freepostpage/read/{id}/comment/delete/{commentID}")        //댓글 삭제 기능
    public String deleteComment(@PathVariable Long id, @PathVariable Long commentID){
        commentService.deleteByID(commentID);
        return "redirect:/freepostpage/read/{id}";
    }
}
