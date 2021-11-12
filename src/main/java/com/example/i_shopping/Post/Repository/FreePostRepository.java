package com.example.i_shopping.Post.Repository;

import com.example.i_shopping.Post.Domain.FreePostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface FreePostRepository extends JpaRepository<FreePostEntity,Long> {

}
