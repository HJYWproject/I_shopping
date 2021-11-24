package com.example.i_shopping.Shopping.Service;

import com.example.i_shopping.Account.Domain.Account;
import com.example.i_shopping.Account.Repository.AccountRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SellingService {

    private final AccountRepository accountRepository;

    public SellingService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getcredit(String username) throws UsernameNotFoundException, NullPointerException{
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
