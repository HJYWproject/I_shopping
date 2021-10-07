package com.example.i_shopping.Dto;

import com.example.i_shopping.Domain.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class AccountForm {

    private Long id;
    private String username;
    private String password;
    private String role;

    @Builder
    public AccountForm(Long id, String username, String password, String role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account toEntity(){
        return Account.builder()
                .id(id)
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))     // BCryptPasswordEncoder  == 스프링 시큐리티에서 제공, 비밀번호 암호화
                .role(role)
                .build();
    }

}
