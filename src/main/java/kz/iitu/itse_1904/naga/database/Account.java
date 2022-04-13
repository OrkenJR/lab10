package kz.iitu.itse_1904.naga.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "number")
    private String number;

    @Min(value = 0, message = "Amount should not be less than 0")
    @Max(value = 10000000, message = "Amount should not be greater than 10000000")
    private Long amount;

    @AssertFalse
    private boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public static List<Account> getAccounts(){
        Account a = Account.builder()
                .id(1L)
                .number("123")
                .amount(5000L)
                .isBlocked(false)
                .build();
        Account a2 = Account.builder()
                .id(2L)
                .number("231")
                .amount(5400L)
                .isBlocked(false)
                .build();
        Account a3 = Account.builder()
                .id(3L)
                .number("321")
                .amount(5450L)
                .isBlocked(true)
                .build();
        return Arrays.asList(a, a2, a3);

    }

}
