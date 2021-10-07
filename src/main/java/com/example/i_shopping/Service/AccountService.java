package com.example.i_shopping.Service;

import com.example.i_shopping.Domain.Account;
import com.example.i_shopping.Dto.AccountForm;
import com.example.i_shopping.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;      // accountRepository 가져옴

    @Transactional
    public Long createUser(AccountForm form) {
            Account account = form.toEntity();
            accountRepository.save(account);        // JpaRepository에서 제공하는 save 함수
            return account.getId();
    }

    public Boolean findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
