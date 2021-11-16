package com.example.i_shopping.Post.Repository;

import com.example.i_shopping.Post.Domain.CommentEntity;
import com.example.i_shopping.Post.Domain.FreePostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    //void deleteByID(Long id);

    List<CommentEntity> findCommentsByFreepostEntity(FreePostEntity freepostEntity);

}
