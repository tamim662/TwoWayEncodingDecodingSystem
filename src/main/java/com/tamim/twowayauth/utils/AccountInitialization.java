package com.tamim.twowayauth.utils;

import com.tamim.twowayauth.services.AccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountInitialization implements ApplicationRunner {

    private final AccountServices accountServices;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        accountServices.addMultipleAccounts();
    }
}
