package com.example.i_shopping.Shopping.Repository;

import com.example.i_shopping.Post.Dto.CommentForm;
import com.example.i_shopping.Shopping.Domain.SellingEntity;
import com.example.i_shopping.Shopping.Dto.SellingForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SellingRepository extends JpaRepository<SellingEntity,Long> {



}
