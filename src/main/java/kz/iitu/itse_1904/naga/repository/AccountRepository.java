package kz.iitu.itse_1904.naga.repository;

import kz.iitu.itse_1904.naga.database.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    Account findAccountById(Long id);
    Page<Account> findAccountsByOrderById(Pageable pageable);
    Page<Account> findAccountsByOrderByAmountAsc(Pageable pageable);

    @Query("select count(a) from Account a where a.isBlocked = false ")
    Integer countNotBlockedAccounts();

    @Query("select count(a) from Account a where a.isBlocked = true ")
    Integer countBlockedAccounts();

    Page<Account> findAccountsByUser_Email(String email, Pageable pageable);
    Page<Account> findAll(Pageable pageable);
    Page<Account> findAll();
    Page<Account> findAccountsByUser_Id(Long id, Pageable pageable);

}
