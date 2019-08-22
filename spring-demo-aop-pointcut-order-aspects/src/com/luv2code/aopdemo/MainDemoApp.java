package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
        MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
        //call the business method
        Account myAccount = new Account();

        theAccountDAO.addAccount(myAccount, true);
        theAccountDAO.doWork();

        //call the accountdao getter/setter method
        theAccountDAO.setName("foobar");
        theAccountDAO.setServiceCode("silver");

        System.out.println(theAccountDAO.getName());
        String code = theAccountDAO.getServiceCode();


        theMembershipDAO.addSillyMember();
        theMembershipDAO.goToSleep();

        //close the context
        context.close();
    }
}