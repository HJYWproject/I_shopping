package com.example.i_shopping.Post.Dto;

import com.example.i_shopping.Post.Domain.FaqPostEntity;
import lombok.*;

@Getter@Setter
@NoArgsConstructor
@ToString
public class FaqPostForm {
    private Long id;
    private String title;
    private String content;
    private String userid;

    @Builder
    public FaqPostForm(Long id, String title, String content, String userid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userid = userid;
    }

    public FaqPostEntity toEntity() {
        return FaqPostEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .userid(userid)
                .build();
    }
}
