package kz.iitu.itse_1904.naga.service;

import kz.iitu.itse_1904.naga.database.User;
import kz.iitu.itse_1904.naga.repository.UserRepository;
import kz.iitu.itse_1904.naga.service.impl.UserServiceImpl;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        ReflectionTestUtils.setField(userService, "SENDER_EMAIL", "test@mail.com");
        ReflectionTestUtils.setField(userService, "SENDER_EMAIL_PASSWORD", "test");
        user = User.builder()
                .id(1L)
                .username("test")
                .password("test")
                .firstName("test")
                .lastName("test")
                .email("test@mail.com")
                .phone("test").build();
    }

    @Test
    void testSave() {
        when(userRepository.save(any(User.class)))
                .thenReturn(user);
        User result = userService.save(user);
        Assertions.assertEquals(user, result);
        Assertions.assertEquals(user.getId(), result.getId());
        Assertions.assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    void testDelete() {

        userService.delete(user);
        verify(userRepository, times(1)).delete(eq(user));
    }

    @Test
    void testFindAll() {

        List<User> list = Arrays.asList(user);

        Pageable pageable = Pageable.ofSize(-1);

        when(userRepository.findAll())
                .thenReturn(new PageImpl<>(list));
        Page<User> result = userService.findAll(pageable);
        Assertions.assertEquals(list, result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(user, result.getContent().get(0));
        Assertions.assertEquals(1, result.getContent().get(0).getId());
    }

//    @Test
//    void testFindById() {
//
//        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(user));
//
//        User result = userService.findById(1L);
//        Assertions.assertEquals(user, result);
//    }
//
//    @Test
//    void testAuthenticateUser() {
//        when(userRepository.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.ofNullable(user));
//        boolean result = userService.authenticateUser(user.getUsername(), user.getPassword());
//        Assertions.assertEquals(true, result);
//    }

    @Test
    void testSendMail() {
        userService.sendMail("messageText", user);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme