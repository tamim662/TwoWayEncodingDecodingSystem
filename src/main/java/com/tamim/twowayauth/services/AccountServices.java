package com.tamim.twowayauth.services;

import com.tamim.twowayauth.entities.Account;
import com.tamim.twowayauth.entities.Encoded;
import com.tamim.twowayauth.repositories.AccountRepository;
import com.tamim.twowayauth.repositories.EncodedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServices {
    private static final String SECRET_KEY = "mysecretkey-IQDX";

    private final AccountRepository accountRepository;
    private final EncodedRepository encodedRepository;
    public void addMultipleAccounts() {
        // Create multiple Account objects
        Account account1 = new Account("1", "John Doe", "john@example.com");
        Account account2 = new Account("2", "Jane Smith", "jane@example.com");
        Account account3 = new Account("3", "Alice Johnson", "alice@example.com");

        // Save the accounts to the database
        accountRepository.saveAll(Arrays.asList(account1, account2, account3));
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public String getEncodedVersion(String accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        String encodedString = encodeAccount(account);
        Encoded encoded = new Encoded();
        encoded.setEncodedString(encodedString);
        encodedRepository.save(encoded);
        return encodedString;
    }

    private String encodeAccount(Account account) {
        try {
            String accountData = account.getAccountId() + ":" + account.getName() + ":" + account.getEmail();

            SecretKeySpec secretKey = new SecretKeySpec(Arrays.copyOf(SECRET_KEY.getBytes(StandardCharsets.UTF_8), 16), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encodedBytes = cipher.doFinal(accountData.getBytes());

            return Base64.getEncoder().encodeToString(encodedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getDecodedVersion(String encodedString) {
        return decodeAccount(encodedString);
    }

    private Account decodeAccount(String encodedString) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = cipher.doFinal(Base64.getDecoder().decode(encodedString));

            String accountData = new String(decodedBytes);
            String[] accountFields = accountData.split(":");

            if (accountFields.length == 3) {
                String accountId = accountFields[0];
                String name = accountFields[1];
                String email = accountFields[2];

                return new Account(accountId, name, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
