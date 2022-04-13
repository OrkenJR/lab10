package kz.iitu.itse_1904.naga.config;

import kz.iitu.itse_1904.naga.aop.AfterAspect;
import kz.iitu.itse_1904.naga.aop.BeforeAspect;
import kz.iitu.itse_1904.naga.aop.MainAspect;
import kz.iitu.itse_1904.naga.util.CurrencyFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.joda.JodaTimeFormatterRegistrar;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;


@Configuration
//@Profile("test")
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class TestConfig {

    @ConfigurationProperties(prefix="spring.datasource")
    @Bean
    public DataSource getDataSource() {

        return DataSourceBuilder
                .create()
                .build();
    }

    @Value("${email.sender}")
    private String sender;

    @Value("${email.sender.password}")
    private String password;

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    @Bean(initMethod = "info", destroyMethod = "destroy")
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
//        return new UserServiceImpl(userRepository(), sender, password);
//    }

//    @Bean(initMethod = "info", destroyMethod = "destroy")
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

    @Bean
    public MainAspect mainAspect(){
        return new MainAspect();
    }

    @Bean
    public BeforeAspect beforeAspect(){
        return new BeforeAspect();
    }

    @Bean
    public AfterAspect afterAspect(){
        return new AfterAspect();
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis (RequestHandlerSelectors.basePackage ("kz.iitu.itse_1904.naga.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
