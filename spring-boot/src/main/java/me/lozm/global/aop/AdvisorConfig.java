package me.lozm.global.aop;

import lombok.extern.slf4j.Slf4j;
import me.lozm.global.annotation.CustomAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

@Slf4j
@Aspect
@Configuration
public class AdvisorConfig {

    @Around("execution(* me.lozm.api.async..*(..))")
    public Object AsyncAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("{}", proceedingJoinPoint.getSignature());
        return proceedingJoinPoint.proceed();
    }

    @Around("@annotation(me.lozm.global.annotation.CustomAnnotation)")
    public Object customAnnotationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        CustomAnnotation annotation = methodSignature.getMethod().getAnnotation(CustomAnnotation.class);
        log.info("signature: {}, annotation: {}", signature, annotation.name() + ", " + Arrays.stream(annotation.values()).collect(joining(", ")));
        return proceedingJoinPoint.proceed();
    }

}
