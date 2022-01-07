package com.example.i_shopping.Shopping.Dto;

import com.example.i_shopping.Shopping.Domain.SellingEntity;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Getter @Setter
@Component
public class SellingForm {

    private Long id;
    private String sex;
    private String username;
    private String imageProduct;
    private String title;
    private String content;
    private byte[] blob_image;

    @Builder
    public SellingForm(Long id, String sex, String username, String imageProduct, String title, String content, byte[] blob_image){
        this.id = id;
        this.sex = sex;
        this.username = username;
        this.imageProduct = imageProduct;
        this.title = title;
        this.content = content;
        this.blob_image = blob_image;
    }

    public SellingEntity toEntity(){
        return SellingEntity.builder()
                .id(id)
                .sex(sex)
                .username(username)
                .imageProduct(imageProduct)
                .blob_image(blob_image)
                .title(title)
                .content(content)
                .build();
    }

    }

