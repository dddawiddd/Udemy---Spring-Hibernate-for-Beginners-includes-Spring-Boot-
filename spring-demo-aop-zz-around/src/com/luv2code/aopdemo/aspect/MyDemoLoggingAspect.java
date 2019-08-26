package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n===> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, lets execute the method
        Object result = theProceedingJoinPoint.proceed();
        // get end timestamp
        long end = System.currentTimeMillis();
        //compute duration and display it
        long duration = end - begin;
        System.out.println("\n===> Duration: " + duration / 1000 + " seconds");
        return result;
    }


    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJointPoint) {

        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n========> Executing @After (finally) on method: " + method);


    }


    @AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {

        //print out which method w are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n +++> Executing @AfterThrowing on method: " + method);
        // log the exception
        System.out.println("\n +++> The Exception is: " + theExc);

    }


    @AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n +++> Executing @AfterReturning on method: " + method);

        //print out the results of the method call
        System.out.println(" +++> result is: " + result);

        //let's post-process the data... let;s modify it

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println(" +++> result is: " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        //loop through accounts
        for (Account tempAccount : result) {
            //get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();
            //update the name on the account
            tempAccount.setName(theUpperName);
        }
    }


    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

        System.out.println("========>>> Executing @Before advice on AddAccount()");

        // display the method signature
        MethodSignature methoSig = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methoSig);

        // display method arguments

        //get arguments
        Object[] args = theJoinPoint.getArgs();

        //loop thru args
        for (Object tempArg : args) {

            System.out.println("??????????????" + tempArg);

            if (tempArg instanceof Account) {
                //downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }
}