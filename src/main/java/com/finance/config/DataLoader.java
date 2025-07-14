package com.finance.config;

import com.finance.entity.Account;
import com.finance.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(AccountRepository repo) {
        return args -> {
            repo.save(new Account(1001L, "Amit Shah", "Health Insurance"));
            repo.save(new Account(1002L, "Sneha Patil", "Life Insurance"));
            repo.save(new Account(1003L, "Raj Mehta", "Vehicle Insurance"));
        };
    }
}
