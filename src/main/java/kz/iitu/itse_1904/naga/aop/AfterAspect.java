package kz.iitu.itse_1904.naga.aop;

import kz.iitu.itse_1904.naga.database.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;
import java.util.List;

@Aspect
@Slf4j
public class AfterAspect {

    @After("kz.iitu.itse_1904.naga.aop.MainAspect.inServiceLayer()")
    public void insideServiceLayer(JoinPoint joinPoint){
        log.info("#AFTER - service layer class - {}", joinPoint.getSignature().getDeclaringType());
    }

    @After("kz.iitu.itse_1904.naga.aop.MainAspect.serviceOperation()")
    public void doServiceLogging(JoinPoint joinPoint){
        log.info("In service layer finished execution {}, with args: {}", joinPoint.getSignature().toShortString(), Arrays.asList(joinPoint.getArgs()));
    }

    @After("kz.iitu.itse_1904.naga.aop.MainAspect.inRepositoryLayer()")
    public void insideRepositoryLayer(JoinPoint joinPoint){
        log.info("#AFTER - Repo layer = {}", joinPoint.getSignature().getDeclaringType());
    }

    @After("kz.iitu.itse_1904.naga.aop.MainAspect.repositoryOperation()")
    public void doRepositoryLogging(JoinPoint joinPoint){
        log.info("In repository layer finished execution {}, with args: {}", joinPoint.getSignature().toShortString(), Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "execution(* kz.iitu.itse_1904.naga.service.impl.UserServiceImpl.findAll())", returning = "returnValue")
    public void logServiceFindAll(JoinPoint joinPoint, Object returnValue){
        log.info("Method {} returned {}", joinPoint.getSignature().toShortString(), (List<User>)returnValue);
    }

    @AfterThrowing(value = "kz.iitu.itse_1904.naga.aop.MainAspect.serviceOperation()", throwing = "e")
    public void logServiceExceptions(JoinPoint joinPoint, Exception e) throws Throwable{
        log.info("Method {} in {} threw exception - {}", joinPoint.getSignature().toShortString(), joinPoint.getSignature().getDeclaringType().getSimpleName(), e.toString());
    }

    @AfterThrowing(value = "kz.iitu.itse_1904.naga.aop.MainAspect.repositoryOperation()", throwing = "e")
    public void logRepositoryExceptions(JoinPoint joinPoint, Exception e) throws Throwable{
        log.info("Method {} in {} threw exception - {}", joinPoint.getSignature().toShortString(), joinPoint.getSignature().getDeclaringType().getSimpleName(), e.toString());
    }


}
