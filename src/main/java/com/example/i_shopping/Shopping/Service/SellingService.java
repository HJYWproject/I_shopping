package com.example.i_shopping.Shopping.Service;

import com.example.i_shopping.Shopping.Domain.SellingEntity;
import com.example.i_shopping.Shopping.Dto.SellingForm;
import com.example.i_shopping.Shopping.Repository.SellingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SellingService<sellingRepository> {
    @Autowired
    private final SellingRepository sellingRepository;

    public SellingService(SellingRepository sellingRepository) {
        this.sellingRepository = sellingRepository;
    }

    public Long save(SellingForm form){
            return sellingRepository.save(form.toEntity()).getId();
    }

    @Transactional
    public List<SellingEntity> getClothes() throws NullPointerException{
        List<SellingEntity> selling = new ArrayList<>();
        selling.addAll(sellingRepository.findAll());
        return selling;
    }

    public List<SellingEntity> getManClothes() throws NullPointerException{
        List<SellingEntity> m_selling = new ArrayList<>();
        m_selling.addAll(sellingRepository.findAllMan());
        return m_selling;
    }

    public List<SellingEntity> getWomanClothes() throws NullPointerException{
        List<SellingEntity> w_selling = new ArrayList<>();
        w_selling.addAll(sellingRepository.findAllWoman());

        return w_selling;
    }
}
