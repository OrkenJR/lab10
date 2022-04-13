package kz.iitu.itse_1904.naga;

import kz.iitu.itse_1904.naga.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan
@EnableJpaRepositories
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableScheduling
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)

public class Lab1Application {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Lab1Application.class);
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);


    }




}
