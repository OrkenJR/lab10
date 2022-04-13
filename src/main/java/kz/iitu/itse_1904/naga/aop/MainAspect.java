package kz.iitu.itse_1904.naga.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MainAspect {


    @Pointcut("within(kz.iitu.itse_1904.naga.repository..*)")
    public void inRepositoryLayer(){}

    @Pointcut("within(kz.iitu.itse_1904.naga.service..*)")
    public void inServiceLayer(){}

    @Pointcut("execution(* kz.iitu.itse_1904.naga.service.*.*(..))")
    public void serviceOperation(){}

    @Pointcut("execution(* kz.iitu.itse_1904.naga.repository.*.*(..))")
    public void repositoryOperation(){}

}
