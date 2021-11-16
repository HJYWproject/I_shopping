package com.example.i_shopping.Post.Service;

import com.example.i_shopping.Post.Dto.CommentForm;
import com.example.i_shopping.Post.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;

    @Transactional
    public Long save(CommentForm commentForm){
        return commentRepository.save(commentForm.toEntity()).getId();
    }

    @Transactional
    public Long update(CommentForm commentForm){
        return commentRepository.save(commentForm.toEntityUpdate()).getId();
    }

    @Transactional
    public void deleteByID(Long commentID) {
        commentRepository.deleteById(commentID);
    }
}