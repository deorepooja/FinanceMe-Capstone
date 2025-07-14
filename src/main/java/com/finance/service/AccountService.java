
package com.finance.service;

import com.finance.entity.Account;
import com.finance.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

    public Account createAccount(Account acc) {
        return repo.save(acc);
    }

    public Account updateAccount(Long accNo, Account acc) {
        acc.setAccountNo(accNo);
        return repo.save(acc);
    }

    public Account viewPolicy(Long accNo) {
        Optional<Account> opt = repo.findById(accNo);
        return opt.orElse(null);
    }

    public void deletePolicy(Long accNo) {
        repo.deleteById(accNo);
    }
}
