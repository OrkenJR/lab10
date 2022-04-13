package kz.iitu.itse_1904.naga.database;


import kz.iitu.itse_1904.naga.validator.CustomConstraint;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email(message = "Email is not valid")
    private String email;

    @NotBlank
    private String firstName;

    @CustomConstraint
    private String lastName;

    private String phone;

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy("amount")
    private List<Account> accounts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;


    public static List<User> getUserList(){
        User u = User.builder()
                .id(1L)
                .username("user1")
                .password("test")
                .email("test@gmail.com")
                .firstName("user")
                .lastName("user")
                .phone("test")
                .build();
        User u2 = User.builder()
                .id(2L)
                .username("user2")
                .password("test")
                .email("test@gmail.com")
                .firstName("user2")
                .lastName("user2")
                .phone("test")
                .build();
        User u3 = User.builder()
                .id(3L)
                .username("user3")
                .password("test")
                .email("test@gmail.com")
                .firstName("user3")
                .lastName("user3")
                .phone("test")
                .build();
        return Arrays.asList(u, u2, u3);
    }
}
