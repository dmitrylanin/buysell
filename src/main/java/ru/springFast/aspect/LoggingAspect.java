package ru.springFast.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.springFast.models.Product;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * ru.springFast.controllers.*.*(..))")
    public void controllerLog(){}

    @Before("controllerLog()")
    public void afterThrowing(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null){
            request = attributes.getRequest();
        }
        if(request != null){
            log.info("NEW REQUEST: IP: {}, URL: {}, HTTP_METHOD: {}, CONTROLLER_METHOD: {}.{}",
                    request.getRemoteAddr(),
                    request.getRequestURL().toString(),
                    request.getMethod(),
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
        }
    }

    @Around("controllerLog()")
    public Object timeDuration(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        log.info("Execution method: {}--{}. Execution time: {} ms",
                //название класса
                joinPoint.getSignature().getDeclaringTypeName(),
                //название вызываемого метода
                joinPoint.getSignature().getName(),
                executionTime);

        return proceed;
    }

    /*
        Отдельный метод, отлавливающий только методы, отмеченные моей собственной аннотацией
     */
    @AfterReturning(value = "@annotation(ToDBLog)", returning = "returnObject")
    public void afterReturningProductList(Object returnObject){
        log.info("Product list had size {} elements", ((List<Product>)(returnObject)).size());
    }

    @Before("@annotation(ToDBLog)")
    public void beforeExcecution(JoinPoint jp){
        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs();
        String argsString = args.length > 0 ? Arrays.toString(args) : "METHOD HAS NO ARGUMENTS";

        log.info("RUN SERVICE: SERVICE_METHOD: {}.{}. METHOD ARGUMENTS: [{}]",
                className, methodName, argsString);
    }
}