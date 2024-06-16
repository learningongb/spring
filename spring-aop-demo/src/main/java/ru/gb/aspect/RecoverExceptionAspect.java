package ru.gb.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RecoverExceptionAspect {

    @Pointcut("@annotation(ru.gb.aspect.RecoverException)")
    public void methodRecoverException() {}

    @Around("methodRecoverException()")
    public Object recoverException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return proceedingJoinPoint.proceed();
        } catch (Exception e) {
            if (recover(proceedingJoinPoint, e)) {
                log.info("************ Исключение обрабатывается здесь");
                log.warn("{} - {}: {}", proceedingJoinPoint.getTarget().getClass(),
                        proceedingJoinPoint.getSignature().getName(),
                        e.getMessage());
            } else {
                log.info("************ Исключение пробрасывается выше");
                throw e;
            }
        }
        return null;
    }

    private boolean recover(ProceedingJoinPoint proceedingJoinPoint, Exception e) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        RecoverException recoverException = signature.getMethod().getAnnotation(RecoverException.class);
        if (recoverException == null)
            return false;
        for (Class<? extends RuntimeException> aClass : recoverException.noRecoverFor()) {
            if (aClass.isAssignableFrom(e.getClass())) {
                return false;
            }
        }
        return true;
    }
}
