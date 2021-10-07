package com.example.i_shopping.Repository;

import com.example.i_shopping.Domain.Account;
import com.example.i_shopping.Dto.AccountForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Boolean findByUsername(String username);
}