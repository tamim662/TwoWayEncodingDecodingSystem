package com.tamim.twowayauth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class EncryptionServices {
    private static final String SECRET_KEY = "mysecretkey-1234";



    public String decode(String encodedString) {
        System.out.println("Time before decoding: " + System.currentTimeMillis());
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = cipher.doFinal(Base64.getDecoder().decode(encodedString));

            String accountData = new String(decodedBytes);
            System.out.println("Time after decoded: " + System.currentTimeMillis());
            return accountData;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encode(String prompt) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(Arrays.copyOf(SECRET_KEY.getBytes(StandardCharsets.UTF_8), 16), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encodedBytes = cipher.doFinal(prompt.getBytes());

            return Base64.getEncoder().encodeToString(encodedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
