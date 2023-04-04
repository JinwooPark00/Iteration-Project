package com.helloworld.controller;


import com.helloworld.DAO.UserRepository;
import com.helloworld.model.User;
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
    UserRepository userRepository;


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


    @GetMapping("/addUserForm")
    public ModelAndView addUserForm() {
        ModelAndView mav = new ModelAndView("add-user");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/showIteration1")
    public ModelAndView showIteration1() {
        ModelAndView mav = new ModelAndView("iteration1");
        return mav;
    }

    @RequestMapping("/showIteration2")
    public ModelAndView showIteration2() {
        ModelAndView mav = new ModelAndView("iteration2");
        return mav;
    }

    @RequestMapping("/showAddUserIter2")
    public ModelAndView showAddUserIter2() {
        ModelAndView mav = new ModelAndView("iter2-add-user");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/showIteration3")
    public ModelAndView showIteration3() {
        ModelAndView mav = new ModelAndView("iteration3");
        List<User> list = (List<User>) userRepository.findAll();
        mav.addObject("users", list);
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/showAbout")
    public ModelAndView showAbout() {
        ModelAndView mav = new ModelAndView("about");
        return mav;
    }
}


