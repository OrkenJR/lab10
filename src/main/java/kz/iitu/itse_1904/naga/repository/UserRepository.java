package kz.iitu.itse_1904.naga.repository;

import kz.iitu.itse_1904.naga.database.Role;
import kz.iitu.itse_1904.naga.database.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findUserById(Long id);
    Page<User> findAllByFirstNameAndLastNameOrderByIdAsc(String firstName, String lastName, Pageable pageable);
    User findUserByUsernameAndPassword(String username, String password);
    Page<User> findAllByOrderById(Pageable pageable);
    Page<User> findAll(Pageable pageable);
    Page<User> findAllByRolesContains(Role role, Pageable pageable);

    @Query("select count(u) from users u where u.firstName = :#{#firstName}")
    Integer countUsersByFirstName(@Param("firstName") String firstName);

}
