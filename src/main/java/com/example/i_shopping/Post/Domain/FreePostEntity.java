package com.example.i_shopping.Post.Domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreePostEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name = "userid")
    private String userid;

    @Builder
    public FreePostEntity(Long id, String title, String content, String userid){
        this.id = id;
        this.title = title;
        this.content = content;
        this.userid = userid;
    }
}
