package com.example.i_shopping.Account.Service;

import com.example.i_shopping.Account.Domain.Account;
import com.example.i_shopping.Account.Dto.AccountForm;
import com.example.i_shopping.Account.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;      // accountRepository 가져옴

    @Transactional
    public void save(AccountForm form) throws UsernameNotFoundException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        form.setPassword(encoder.encode(form.getPassword()));
        accountRepository.save(form.toEntity());
    }


    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println(username);
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void deleteUser(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        System.out.println(account);
    }
}
