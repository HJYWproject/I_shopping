package com.example.i_shopping.Shopping.Domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellingEntity {
    @Id
    @Column(name = "user_id")       // SQL에서 자동 생성되도록 돕는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // 자동 생성 (프라이머리 키 자동 증가)
    private Long id;

    @Column(name= "username")
    private String username;

    @Column(name= "imageProduct")
    private String imageProduct;

    @Column(name= "title")
    private String title;

    @Column(name= "content")
    private String content;


    @Builder
    public SellingEntity(Long id, String username, String imageProduct, String title, String content){
        this.id = id;
        this.username = username;
        this.imageProduct = imageProduct;
        this.title = title;
        this.content = content;
    }


}
