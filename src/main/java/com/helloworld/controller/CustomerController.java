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
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CustomerController {

    Logger logger = Logger.getLogger(CustomerController.class.getName());

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/list")
    public ModelAndView showCustomersList() {
        ModelAndView mav = new ModelAndView("list-customers");
        List<Customer> list = (List<Customer>) customerRepository.findAll();
        mav.addObject("customers", list);
        return mav;
    }


    @PostMapping("/add")
    public ModelAndView addCustomer(@ModelAttribute Customer customer) {
        logger.log(Level.INFO, "adding");
        customerRepository.save(customer);
        return new ModelAndView("redirect:/list");
    }



    @RequestMapping("/update")
    public Customer updateCustomer(@RequestParam String newFirst, @RequestParam String newLast, @RequestParam long id){
        logger.log(Level.INFO, "updating");
        return customerRepository.findById(id)
                .map(customer -> {
                    if (StringUtils.isNotBlank(newFirst)){
                        customer.setFirstName(newFirst);
                    }
                    if (StringUtils.isNotBlank(newLast)){
                        customer.setLastName(newLast);
                    }
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    Customer customer = new Customer();
                    customer.setId(id);
                    return customerRepository.save(customer);
                });

    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        return customerRepository.findCustomerById(id);
    }
}
