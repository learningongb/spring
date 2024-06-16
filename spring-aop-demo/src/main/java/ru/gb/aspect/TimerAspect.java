package ru.gb.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("within(@ru.gb.aspect.Timer *)")
    public void beanAnnotatedTimer() {}

    @Pointcut("@annotation(ru.gb.aspect.Timer)")
    public void methodAnnotatedTimer() {}

    @Around("beanAnnotatedTimer() || methodAnnotatedTimer()")
    public Object timerAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = new Date().getTime();
        Object result = proceedingJoinPoint.proceed();
        long endTime = new Date().getTime();
        log.info("{} - {} - время выполнения (мсек.): {}",
                proceedingJoinPoint.getTarget().getClass(),
                proceedingJoinPoint.getSignature().getName(),
                endTime - startTime);
        return result;
    }

}
