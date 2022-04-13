package kz.iitu.itse_1904.naga.service;

import kz.iitu.itse_1904.naga.database.Account;
import kz.iitu.itse_1904.naga.repository.AccountRepository;
import kz.iitu.itse_1904.naga.service.impl.AccountServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    AccountServiceImpl accountService;

    Account account;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        account = Account.builder().id(1L).number("test").amount(100L).isBlocked(false).build();
        ReflectionTestUtils.setField(accountService, "accountRepository", accountRepository);
    }

    @Test
    public void testSave() throws Exception {
        when(accountRepository.save(any(Account.class)))
                .thenReturn(account);
        Account result = accountService.save(account);
        Assertions.assertEquals(account, result);
    }

    @Test
    public void testDelete() throws Exception {
        accountService.delete(account);
        verify(accountRepository, times(1)).delete(eq(account));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Account> list = Arrays.asList(account);

        when(accountRepository.findAll())
                .thenReturn(new PageImpl<>(list));
        Page<Account> result = accountService.findAll();
        Assertions.assertEquals(list, result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(account, result.getContent().get(0));
        Assertions.assertEquals(1, result.getContent().get(0).getId());
    }

    @Test
    public void testFindById() throws Exception {
        when(accountRepository.findAccountById(anyLong())).thenReturn(account);

        Account result = accountService.findById(1L);
        Assert.assertEquals(account, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme