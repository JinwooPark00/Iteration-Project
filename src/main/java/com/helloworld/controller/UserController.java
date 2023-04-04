package com.helloworld.controller;

import com.helloworld.model.User;
import org.apache.commons.lang3.StringUtils;
import com.helloworld.DAO.UserRepository;
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
public class UserController {

    Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    WelcomeController welcomeController;

    @Deprecated
    @GetMapping("/list")
    public ModelAndView showUsersList() {
        ModelAndView mav = new ModelAndView("list-users");
        List<User> list = (List<User>) userRepository.findAll();
        mav.addObject("users", list);
        return mav;
    }

    @Deprecated
    @PostMapping("/add")
    public ModelAndView addUser(@ModelAttribute User user) {
        logger.log(Level.INFO, "adding");
        userRepository.save(user);
        return new ModelAndView("redirect:/list");
    }

    @PostMapping("/Iter2Add")
    public String iter2AddUser(@RequestParam Long id) {

        String first = userRepository.findUserById(id).getFirstName();
        String last = userRepository.findUserById(id).getLastName();
        logger.log(Level.INFO, "showing added user "+ first + " "+ last);

        return String.format("hello %s %s", first, last);
    }

    @RequestMapping("/update")
    public ModelAndView updateUser(@RequestParam String newFirst, @RequestParam String newLast, @RequestParam long id){
        logger.log(Level.INFO, "updating id " + id + "new first = " +newFirst + " new last" + newLast);

        User user = userRepository.findById(id)
                .map(c -> {
                    if (StringUtils.isNotBlank(newFirst)){
                        c.setFirstName(newFirst);
                    }
                    if (StringUtils.isNotBlank(newLast)){
                        c.setLastName(newLast);
                    }
                    return userRepository.save(c);
                })
                .orElseGet(() -> {
                    User c = new User();
                    c.setId(id);
                    c.setFirstName(newFirst);
                    c.setLastName(newLast);
                    return userRepository.save(c);
                });

        return welcomeController.showIteration3();
    }

    @PostMapping("/iter2Add")
    public ModelAndView iter2Add(@ModelAttribute User user) {
        logger.log(Level.INFO, "Adding user for iter 2");
        logger.log(Level.INFO, "Name is " + user.getFirstName() + user.getLastName());
        userRepository.save(user);

        ModelAndView mav = new ModelAndView("iteration2Greet");
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/iter3Add")
    public ModelAndView iter3Add(@ModelAttribute User user) {
        logger.log(Level.INFO, "Adding user for iter 3, id = " + user.getId());
        logger.log(Level.INFO, "Name is " + user.getFirstName() + " " + user.getLastName());
        userRepository.save(user);

        ModelAndView mav = new ModelAndView("iteration3");
        List<User> list = (List<User>) userRepository.findAll();
        logger.log(Level.INFO, "List conains id = " + list.get(0));
        mav.addObject("users", list);
        user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/removeAll")
    public ModelAndView removeAll(){
        logger.log(Level.INFO, "Removing All Users");
        userRepository.deleteAll();
        ModelAndView mav = new ModelAndView("iteration3");
        List<User> list = (List<User>) userRepository.findAll();
        mav.addObject("users", list);
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/find/{id}")
    public User findUserById(@PathVariable Long id) {
        return userRepository.findUserById(id);
    }
}
