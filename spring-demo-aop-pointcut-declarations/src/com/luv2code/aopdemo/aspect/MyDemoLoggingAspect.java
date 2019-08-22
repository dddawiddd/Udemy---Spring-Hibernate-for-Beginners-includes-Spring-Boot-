package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    //@Before("execution(public void addAccount())")
    //@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
    //@Before("execution(public void add*())")
    //@Before("execution(* add*())")
    //@Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
    //@Before("execution(* add*( ..))")
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n========>>> Executing @Before advice on AddAccount()");
    }

    @Before("forDaoPackage()")
    public void performApiAnalytics(){
        System.out.println("++++++++++>>> Perform API analytics");
    }



}