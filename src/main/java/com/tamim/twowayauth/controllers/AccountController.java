package com.tamim.twowayauth.controllers;

import com.tamim.twowayauth.entities.Account;
import com.tamim.twowayauth.services.AccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountServices accountServices;

    @GetMapping(value = "/get-account", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> getAccounts() {
        return accountServices.getAccounts();
    }

    @GetMapping(value = "/get-encoded-version", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getEncodedVersion(@RequestParam String accountId) {
        return accountServices.getEncodedVersion(accountId);
    }

    @GetMapping(value = "/get-decoded-version", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDecodedVersion(@RequestParam String encodedString) {
        return accountServices.getDecodedVersion(encodedString);
    }
}
