package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AroundDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from spring container
        TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("\nMain Program: AroundDemoApp");

        System.out.println("Calling getFortune");

        String data = theFortuneService.getFortune();
        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");


        //close the context
        context.close();
    }
}