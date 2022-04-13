package kz.iitu.itse_1904.naga.service;

import kz.iitu.itse_1904.naga.database.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User save(User user);

    void delete(User user);

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByName(String firstName, String lastName, Pageable pageable);

    Page<User> findAllOrderById(Pageable pageable);

    User findByUsernameAndPassword(String username, String password);

    User findById(Long id);

    void sendMail(String messageText, User user);
}
