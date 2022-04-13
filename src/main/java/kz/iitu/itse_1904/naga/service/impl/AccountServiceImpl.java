package kz.iitu.itse_1904.naga.service.impl;

import kz.iitu.itse_1904.naga.database.Account;
import kz.iitu.itse_1904.naga.repository.AccountRepository;
import kz.iitu.itse_1904.naga.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }

    public void saveList(List<Account> accounts) {
        accountRepository.saveAll(accounts);
    }

    @Cacheable("Account")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Cacheable("Account")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Account> findAll() {
        return accountRepository.findAll();
    }

    @Cacheable("Account")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Account> findAllByUserId(Long id, Pageable pageable) {
        return accountRepository.findAccountsByUser_Id(id, pageable);
    }

    @Cacheable("Account")
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Account findById(Long id) {
        return accountRepository.findAccountById(id);
    }

//    @Async
//    @Scheduled(fixedRateString = "${fixedRate.commission}")
    public void getCommissionForTechSupport() {
        saveList(findAll().stream().peek(account -> account.setAmount(account.getAmount() - 100)).collect(Collectors.toList()));
    }

//    @Async
//    @Scheduled(fixedDelayString = "${fixedDelay.tax}")
    public void getTax() {
        saveList(findAll().stream().peek(account -> account.setAmount(account.getAmount() - 1000)).collect(Collectors.toList()));
    }

//    @Scheduled(initialDelayString = "${initialDelay.bonus}", fixedDelayString = "${fixedDelay.bonus}")
    public void giveBonusJustForFun() {
        saveList(findAll().stream().peek(account -> account.setAmount(account.getAmount() + 2000)).collect(Collectors.toList()));
    }

    @PostConstruct
    public void created() {
        System.out.println("AccountServiceImpl bean is created");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("AccountServiceImpl is being to destroyed");
    }
}
