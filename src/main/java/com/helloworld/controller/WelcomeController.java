package com.helloworld.controller;


import org.apache.commons.lang3.StringUtils;
import com.helloworld.DAO.CustomerRepository;
import com.helloworld.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class WelcomeController {

    @Autowired
    CustomerRepository customerRepository;


    @GetMapping({"/"})
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping({"/hello"})
    public String hello(@RequestParam(value = "myName", defaultValue = "world") String name) {
        return String.format("hello %s", name);
    }

    @RequestMapping("/goodbye")
    public String bye() {
        return "bye";
    }


    @GetMapping("/addCustomerForm")
    public ModelAndView addCustomerForm() {
        ModelAndView mav = new ModelAndView("add-customer");
        Customer customer = new Customer();
        mav.addObject("customer", customer);
        return mav;
    }

    @RequestMapping("/showIteration1")
    public ModelAndView showIteration1() {
        ModelAndView mav = new ModelAndView("iteration1");
        return mav;
    }

}


