package com.example.i_shopping.Shopping.Dto;

import com.example.i_shopping.Account.Domain.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SellingForm {

    private Long id;
    private String username;
    private String password;
    private String role;
    private String credit_check;
    private String phone_check;

    @Builder
    public SellingForm(Long id, String username, String password, String role, String credit_check){
        this.id = id;
        this.username = username;
        this.password = "{bcrypt}"+password;
        this.role = role;
        this.credit_check = credit_check;
    }

    public Account toEntity(){
        return Account.builder()
                .id(id)
                .username(username)
                .password(password)     // BCryptPasswordEncoder  == 스프링 시큐리티에서 제공, 비밀번호 암호화
                .role(role)
                .credit_check(credit_check)
                .build();
    }

}
