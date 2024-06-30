package com.gb.timer.aspect;

import com.gb.timer.TimerProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Aspect
@Data
@Component
@AllArgsConstructor
public class TimerAspect {

    private final TimerProperties properties;

    @Pointcut("within(@com.gb.timer.aspect.Timer *)")
    public void beanAnnotatedTimer() {}

    @Pointcut("@annotation(com.gb.timer.aspect.Timer)")
    public void methodAnnotatedTimer() {}

    @Around("beanAnnotatedTimer() || methodAnnotatedTimer()")
    public Object timerAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = new Date().getTime();
        Object result = proceedingJoinPoint.proceed();
        long endTime = new Date().getTime();
        log.atLevel(properties.getLogLevel())
                .log("{} - {} - время выполнения (мсек.): {}",
                proceedingJoinPoint.getTarget().getClass(),
                proceedingJoinPoint.getSignature().getName(),
                endTime - startTime);
        return result;
    }

}
