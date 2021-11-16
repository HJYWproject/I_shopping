package com.example.i_shopping.Post.Dto;

import com.example.i_shopping.Post.Domain.CommentEntity;
import com.example.i_shopping.Post.Domain.FreePostEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentForm {
    private Long id;
    private String title;
    private String content;
    private String userid;
    private FreePostEntity freepostEntity;

    @Builder
    public CommentForm(Long id, String title, String content, String userid, FreePostEntity freepostEntity) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.freepostEntity = freepostEntity;
    }

    public CommentEntity toEntity() {
        CommentEntity commentEntity = CommentEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .userid(userid)
                .freepostEntity(freepostEntity)
                .build();
        return commentEntity;
    }

    public CommentEntity toEntityUpdate() {
        CommentEntity commentEntityUpdate = CommentEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .userid(userid)
                .freepostEntity(freepostEntity)
                .build();
        return commentEntityUpdate;
    }

}