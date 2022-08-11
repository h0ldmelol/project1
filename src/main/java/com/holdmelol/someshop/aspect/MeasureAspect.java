package com.holdmelol.someshop.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class MeasureAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(LoggingUserController)")
    private void annotationMethod(){}

    @Before("annotationMethod()")
    public void afterCall(ProceedingJoinPoint jp) {
        logger.info("after " + jp.toString());
    }


}
