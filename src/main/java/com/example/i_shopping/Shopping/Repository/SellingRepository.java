package com.example.i_shopping.Shopping.Repository;

import com.example.i_shopping.Post.Dto.CommentForm;
import com.example.i_shopping.Shopping.Domain.SellingEntity;
import com.example.i_shopping.Shopping.Dto.SellingForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SellingRepository extends JpaRepository<SellingEntity,Long> {

    @Query(value = "SELECT * from selling_entity where sex = 'm'", nativeQuery = true)
    List<SellingEntity> findAllMan();

    @Query(value = "SELECT * from selling_entity where sex = 'w'", nativeQuery = true)
    List<SellingEntity> findAllWoman();
}
