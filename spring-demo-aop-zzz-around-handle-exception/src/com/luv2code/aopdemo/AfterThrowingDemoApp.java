package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterThrowingDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        // call method to find the accounts
        List<Account> theAccounts = null;
        try {
            // add a boolean flag to simulate exception
            boolean tripWire = true;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\nMain Program ... caught exception: " + exc);
        }

        // display the accounts
        System.out.println("\nMain Program: AfterThrowingDemoApp");
        System.out.println("....");
        System.out.println(theAccounts);
        System.out.println("\n");


        //close the context
        context.close();
    }
}