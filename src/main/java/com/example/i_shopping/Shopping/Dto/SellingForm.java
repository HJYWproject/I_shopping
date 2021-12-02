package com.example.i_shopping.Shopping.Dto;

import com.example.i_shopping.Account.Domain.Account;
import lombok.*;

@Data
@NoArgsConstructor
@Getter @Setter
public class SellingForm {

    private Long id;
    private String username;
    private String imageProduct;
    private String title;
    private String content;

    @Builder
    public SellingForm(Long id, String username, String imageProduct, String title, String content){
        this.id = id;
        this.username = username;
        this.imageProduct = imageProduct;
        this.title = title;
        this.content = content;
    }

    public Account toEntity(){
        return Account.builder()
                .id(id)
                .username(username)
                //.imageProduct(imageProduct)
                //.title(title)
                //.content(content)
                .build();
    }

    }

