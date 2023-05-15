package com.tamim.twowayauth;

import com.tamim.twowayauth.repositories.AccountRepository;
import com.tamim.twowayauth.services.AccountServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwoWayAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoWayAuthApplication.class, args);
    }

}
