package kz.iitu.itse_1904.naga.controller;

import kz.iitu.itse_1904.naga.database.Account;
import kz.iitu.itse_1904.naga.database.Role;
import kz.iitu.itse_1904.naga.database.User;
import kz.iitu.itse_1904.naga.repository.AccountRepository;
import kz.iitu.itse_1904.naga.service.AccountService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.mockito.Mockito.*;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountControllerTest {
    @Mock
    AccountService service;
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    AccountController accountController;

    Account account;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        account = Account.builder().id(1L).number("test").amount(100L).isBlocked(false).build();
        ReflectionTestUtils.setField(service, "accountRepository", accountRepository);
        ReflectionTestUtils.setField(accountController, "service", service);

    }
//    @Test
//    void testGetAllAccounts() {
//        when(service.findAll(any())).thenReturn(new PageImpl<>(Account.getAccounts()));
//
//        ResponseEntity<Page<Account>> result = accountController.getAllAccounts(null);
//        Assertions.assertEquals(result.getBody().getContent().size(), Account.getAccounts().size());
//    }
//
//    @Test
//    void testGetAccountById() {
//        when(service.findById(anyLong())).thenReturn(new Account(Long.valueOf(1), "number", Long.valueOf(1), true, new User(Long.valueOf(1), "email", "firstName", "lastName", "phone", "username", "password", Arrays.<Account>asList(null), Arrays.<Role>asList(new Role(0L)))));
//
//        ResponseEntity<Account> result = accountController.getAccountById(Long.valueOf(1));
//        Assertions.assertEquals(null, result);
//    }
//
//    @Test
//    void testGetAccountsByUserId() {
//        when(service.findAllByUserId(anyLong(), any())).thenReturn(null);
//
//        ResponseEntity<Page<Account>> result = accountController.getAccountsByUserId(Long.valueOf(1), null);
//        Assertions.assertEquals(null, result);
//    }
//
//    @Test
//    void testSaveAccount() {
//        when(service.save(any())).thenReturn(new Account(Long.valueOf(1), "number", Long.valueOf(1), true, new User(Long.valueOf(1), "email", "firstName", "lastName", "phone", "username", "password", Arrays.<Account>asList(null), Arrays.<Role>asList(new Role(0L)))));
//
//        ResponseEntity<Account> result = accountController.saveAccount(new Account(Long.valueOf(1), "number", Long.valueOf(1), true, new User(Long.valueOf(1), "email", "firstName", "lastName", "phone", "username", "password", Arrays.<Account>asList(null), Arrays.<Role>asList(new Role(0L)))));
//        Assertions.assertEquals(null, result);
//    }
//
//    @Test
//    void testDeleteAccount() {
//        when(service.findById(anyLong())).thenReturn(new Account(Long.valueOf(1), "number", Long.valueOf(1), true, new User(Long.valueOf(1), "email", "firstName", "lastName", "phone", "username", "password", Arrays.<Account>asList(null), Arrays.<Role>asList(new Role(0L)))));
//
//        ResponseEntity<String> result = accountController.deleteAccount(Long.valueOf(1));
//        Assertions.assertEquals(null, result);
//    }
//
//    @Test
//    void testUpdateAccount() {
//        when(service.save(any())).thenReturn(new Account(Long.valueOf(1), "number", Long.valueOf(1), true, new User(Long.valueOf(1), "email", "firstName", "lastName", "phone", "username", "password", Arrays.<Account>asList(null), Arrays.<Role>asList(new Role(0L)))));
//
//        ResponseEntity<Account> result = accountController.updateAccount(new Account(Long.valueOf(1), "number", Long.valueOf(1), true, new User(Long.valueOf(1), "email", "firstName", "lastName", "phone", "username", "password", Arrays.<Account>asList(null), Arrays.<Role>asList(new Role(0L)))));
//        Assertions.assertEquals(null, result);
//    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme