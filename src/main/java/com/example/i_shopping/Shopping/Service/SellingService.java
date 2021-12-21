package com.example.i_shopping.Shopping.Service;

import com.example.i_shopping.Shopping.Dto.SellingForm;
import com.example.i_shopping.Shopping.Repository.SellingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SellingService {
    static SellingRepository sellingRepository;

    @Transactional
    public static Long save(SellingForm sellingForm){
        return sellingRepository.save(sellingForm.toEntity()).getId();
    }

}
