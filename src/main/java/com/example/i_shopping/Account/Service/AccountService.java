package com.example.i_shopping.Account.Service;

import com.example.i_shopping.Account.Domain.Account;
import com.example.i_shopping.Account.Dto.AccountForm;
import com.example.i_shopping.Account.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;      // accountRepository 가져옴

    @Transactional
    public void save(AccountForm form) throws UsernameNotFoundException {
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encoder = passwordEncoder.encode(form.getUsername());
        System.out.println(encoder);
        form.setPassword(encoder);
        System.out.println(form.getPassword());
        accountRepository.save(form.toEntity());
    }

    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
