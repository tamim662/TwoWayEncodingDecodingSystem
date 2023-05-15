package com.tamim.twowayauth.repositories;

import com.tamim.twowayauth.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findFirstByOrderByAccountIdAsc();
}
