package com.luv2code.springdemo.controller;


import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //Spring will scan for a component that implements CustomerDAO interface
    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping("/list")
    public String listCustomer(Model theModel) {


        List<Customer> theCustomers = customerDAO.getCustomers();

        theModel.addAttribute("customers", theCustomers);


        return "list-customers";
    }
}
