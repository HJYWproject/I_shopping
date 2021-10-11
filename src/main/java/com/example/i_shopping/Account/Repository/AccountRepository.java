package com.example.i_shopping.Account.Repository;

import com.example.i_shopping.Account.Domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Boolean findByUsername(String username);
}