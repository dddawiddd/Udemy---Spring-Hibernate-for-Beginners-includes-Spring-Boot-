package com.luv2code.springdemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations

    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage()||forServicePackage()||forDaoPackage()")
    private void forAppFlow() {

    }

    // add @Before declarations

    @Before("forAppFlow()")
    public void before(JoinPoint theJointPoint) {
        String theMethod = theJointPoint.getSignature().toShortString();
        myLogger.info("=====> in @Before calling method: " + theMethod);

        // display the arguments to the method

        // get the arguments
        Object[] args = theJointPoint.getArgs();
        //loop thru and display args
        for (Object tempArg : args) {
            myLogger.info("+++++>> argument: " + tempArg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
    public void asd(JoinPoint theJointPoint, Object theResult) {

        String theMethod = theJointPoint.getSignature().toShortString();
        myLogger.info("=====> in @AfterReturning: from method: " + theMethod);

        myLogger.info("=====> result: " + theResult);

    }

    // add @AfterReturning advice


}
