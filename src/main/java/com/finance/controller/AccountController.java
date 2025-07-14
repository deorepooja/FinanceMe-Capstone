package com.finance.controller;

import com.finance.entity.Account;
import com.finance.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/createAccount")
    public Account create(@RequestBody Account acc) {
        return service.createAccount(acc);
    }

    @PutMapping("/updateAccount/{accountNo}")
    public Account update(@PathVariable Long accountNo, @RequestBody Account acc) {
        return service.updateAccount(accountNo, acc);
    }

    @GetMapping("/viewPolicy/{accountNo}")
    public Account view(@PathVariable Long accountNo) {
        return service.viewPolicy(accountNo);
    }

    @DeleteMapping("/deletePolicy/{accountNo}")
    public String delete(@PathVariable Long accountNo) {
        service.deletePolicy(accountNo);
        return "Policy deleted for account: " + accountNo;
    }
}
