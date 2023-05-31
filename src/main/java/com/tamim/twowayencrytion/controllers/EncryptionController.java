package com.tamim.twowayencrytion.controllers;

import com.tamim.twowayencrytion.services.EncryptionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class EncryptionController {

    private final EncryptionServices encryptionServices;


    @PostMapping(value = "/encode", produces = MediaType.APPLICATION_JSON_VALUE)
    public String encode(@RequestParam String prompt) {
        return encryptionServices.encode(prompt);
    }

    @GetMapping(value = "/decode", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDecodedVersion(@RequestParam String encodedString) {
        System.out.println("Time during requesting: " + System.currentTimeMillis());
        return encryptionServices.decode(encodedString);
    }
}
