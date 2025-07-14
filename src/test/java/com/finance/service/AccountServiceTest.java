package com.finance.service;

import com.finance.entity.Account;
import com.finance.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        Account account = new Account(1001L, "Amit", "Health Policy");
        when(accountRepository.save(account)).thenReturn(account);

        Account saved = accountService.createAccount(account);
        assertEquals("Amit", saved.getName());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testViewPolicy() {
        Account account = new Account(1002L, "Sneha", "Life Policy");
        when(accountRepository.findById(1002L)).thenReturn(Optional.of(account));

        Account found = accountService.viewPolicy(1002L);
        assertNotNull(found);
        assertEquals("Life Policy", found.getPolicyDetails());
        verify(accountRepository, times(1)).findById(1002L);
    }

    @Test
    void testUpdateAccount() {
        Account updated = new Account(1003L, "Raj", "Updated Policy");
        when(accountRepository.save(updated)).thenReturn(updated);

        Account result = accountService.updateAccount(1003L, updated);
        assertEquals("Updated Policy", result.getPolicyDetails());
        verify(accountRepository, times(1)).save(updated);
    }

    @Test
    void testDeletePolicy() {
        Long accNo = 1004L;
        doNothing().when(accountRepository).deleteById(accNo);

        accountService.deletePolicy(accNo);
        verify(accountRepository, times(1)).deleteById(accNo);
    }
}
