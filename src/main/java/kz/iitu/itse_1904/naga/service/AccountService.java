package kz.iitu.itse_1904.naga.service;

import kz.iitu.itse_1904.naga.database.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    Account save(Account account);
    void delete(Account account);
    Page<Account> findAll(Pageable pageable);
    Page<Account> findAllByUserId(Long id, Pageable pageable);
    Account findById(Long id);
}
