package com.example.i_shopping.Shopping.Dto;

import com.example.i_shopping.Shopping.Domain.SellingEntity;
import lombok.*;

@Data
@NoArgsConstructor
@Getter @Setter
public class SellingForm {

    private Long id;
    private String sex;
    private String username;
    private String imageProduct;
    private String title;
    private String content;

    @Builder
    public SellingForm(Long id, String sex, String username, String imageProduct, String title, String content){
        this.id = id;
        this.sex = sex;
        this.username = username;
        this.imageProduct = imageProduct;
        this.title = title;
        this.content = content;
    }

    public SellingEntity toEntity(){
        return SellingEntity.builder()
                .id(id)
                .sex(sex)
                .username(username)
                .imageProduct(imageProduct)
                .title(title)
                .content(content)
                .build();
    }

    }

