package com.helloworld.controller;

import org.apache.commons.lang3.StringUtils;
import com.helloworld.DAO.CustomerRepository;
import com.helloworld.model.Customer;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CustomerController {

    Logger logger = Logger.getLogger(CustomerController.class.getName());

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    WelcomeController welcomeController;

    @Deprecated
    @GetMapping("/list")
    public ModelAndView showCustomersList() {
        ModelAndView mav = new ModelAndView("list-customers");
        List<Customer> list = (List<Customer>) customerRepository.findAll();
        mav.addObject("customers", list);
        return mav;
    }

    @Deprecated
    @PostMapping("/add")
    public ModelAndView addCustomer(@ModelAttribute Customer customer) {
        logger.log(Level.INFO, "adding");
        customerRepository.save(customer);
        return new ModelAndView("redirect:/list");
    }

    @PostMapping("/Iter2Add")
    public String iter2AddCustomer(@RequestParam Long id) {

        String first = customerRepository.findCustomerById(id).getFirstName();
        String last = customerRepository.findCustomerById(id).getLastName();
        logger.log(Level.INFO, "showing added user "+ first + " "+ last);

        return String.format("hello %s %s", first, last);
    }

    @RequestMapping("/update")
    public ModelAndView updateCustomer(@RequestParam String newFirst, @RequestParam String newLast, @RequestParam long id){
        logger.log(Level.INFO, "updating id " + id + "new first = " +newFirst + " new last" + newLast);

        Customer customer = customerRepository.findById(id)
                .map(c -> {
                    if (StringUtils.isNotBlank(newFirst)){
                        c.setFirstName(newFirst);
                    }
                    if (StringUtils.isNotBlank(newLast)){
                        c.setLastName(newLast);
                    }
                    return customerRepository.save(c);
                })
                .orElseGet(() -> {
                    Customer c = new Customer();
                    c.setId(id);
                    c.setFirstName(newFirst);
                    c.setLastName(newLast);
                    return customerRepository.save(c);
                });

        return welcomeController.showIteration3();
    }

    @PostMapping("/iter2Add")
    public ModelAndView iter2Add(@ModelAttribute Customer customer) {
        logger.log(Level.INFO, "Adding user for iter 2");
        logger.log(Level.INFO, "Name is " + customer.getFirstName() + customer.getLastName());
        customerRepository.save(customer);

        ModelAndView mav = new ModelAndView("iteration2Greet");
        mav.addObject("customer", customer);
        return mav;
    }

    @PostMapping("/iter3Add")
    public ModelAndView iter3Add(@ModelAttribute Customer customer) {
        logger.log(Level.INFO, "Adding user for iter 3, id = " + customer.getId());
        logger.log(Level.INFO, "Name is " + customer.getFirstName() + " " + customer.getLastName());
        customerRepository.save(customer);

        ModelAndView mav = new ModelAndView("iteration3");
        List<Customer> list = (List<Customer>) customerRepository.findAll();
        logger.log(Level.INFO, "List conains id = " + list.get(0));
        mav.addObject("customers", list);
        customer = new Customer();
        mav.addObject("customer", customer);
        return mav;
    }

    @RequestMapping("/removeAll")
    public ModelAndView removeAll(){
        logger.log(Level.INFO, "Removing All Users");
        customerRepository.deleteAll();
        ModelAndView mav = new ModelAndView("iteration3");
        List<Customer> list = (List<Customer>) customerRepository.findAll();
        mav.addObject("customers", list);
        Customer customer = new Customer();
        mav.addObject("customer", customer);
        return mav;
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        return customerRepository.findCustomerById(id);
    }
}
