package me.lozm.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class AdvisorConfig {

    @Around("execution(* me.lozm.api.async..*(..))")
    public Object AsyncAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("{}", proceedingJoinPoint.getSignature());
        return proceedingJoinPoint.proceed();
    }

}
