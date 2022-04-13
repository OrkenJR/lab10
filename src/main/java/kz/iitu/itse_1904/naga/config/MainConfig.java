package kz.iitu.itse_1904.naga.config;

import org.springframework.context.annotation.*;

@Configuration
@Profile("main")
@PropertySource("classpath:application.properties")
public class MainConfig {

//    @Bean
//    @Scope("singleton")
//    public MyRepository<User> userRepository(){
//        return new UserRepository();
//    }
//
//    @Bean
//    @DependsOn("userRepository")
//    @Lazy
//    @Scope("singleton")
//    public UserService userService(){
//        System.out.println("MAIN");
//        return new UserServiceImpl(userRepository());
//    }
//
//    @Bean
//    @Scope("singleton")
//    public MyRepository<Account> accountRepository(){
//        return new AccountRepository();
//    }
//
//    @Bean
//    @DependsOn("accountRepository")
//    @Lazy
//    @Scope("singleton")
//    public AccountService accountService(){
//        return new AccountServiceImpl(accountRepository());
//    }

}
