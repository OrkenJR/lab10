package kz.iitu.itse_1904.naga.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
@Slf4j
public class BeforeAspect {

    @Before("kz.iitu.itse_1904.naga.aop.MainAspect.inServiceLayer()")
    public void insideServiceLayer(JoinPoint joinPoint) {
        log.info("You are using service layer class - {}", joinPoint.getSignature().getDeclaringType());
    }

    @Before("kz.iitu.itse_1904.naga.aop.MainAspect.serviceOperation()")
    public void doServiceLogging(JoinPoint joinPoint) {
        log.info("In service layer executed {}, with args: {}", joinPoint.getSignature().toShortString(), Arrays.asList(joinPoint.getArgs()));
    }

    @Before("kz.iitu.itse_1904.naga.aop.MainAspect.inRepositoryLayer()")
    public void insideRepositoryLayer(JoinPoint joinPoint) {
        log.info("You are using repo layer = {}", joinPoint.getSignature().getDeclaringType());
    }

    @Before("kz.iitu.itse_1904.naga.aop.MainAspect.repositoryOperation()")
    public void doRepositoryLogging(JoinPoint joinPoint) {
        log.info("In repository layer executed {}, with args: {}", joinPoint.getSignature().toShortString(), Arrays.asList(joinPoint.getArgs()));
    }


}
