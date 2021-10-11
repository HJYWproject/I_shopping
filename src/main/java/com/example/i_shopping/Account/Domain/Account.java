package com.example.i_shopping.Account.Domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id @Column(name = "user_id")       // SQL에서 자동 생성되도록 돕는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // 자동 생성 (프라이머리 키 자동 증가)
    private Long id;

    private String username;
    private String password;
    private String role;

    @Builder
    public Account(Long id, String username, String password, String role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
