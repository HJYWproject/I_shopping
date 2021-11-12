package com.example.i_shopping.Post.Dto;

import com.example.i_shopping.Post.Domain.FreePostEntity;
import lombok.*;

@Getter@Setter
@NoArgsConstructor
@ToString
public class FreePostForm {
    private Long id;
    private String title;
    private String content;
    private String userid;

    @Builder
    public FreePostForm(Long id, String title, String content, String userid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userid = userid;
    }

    public FreePostEntity toEntity() {
        return FreePostEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .userid(userid)
                .build();
    }
}
