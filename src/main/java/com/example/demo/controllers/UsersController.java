package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by daniel on 6/23/17.
 */

@Controller
public class UsersController {

    @Autowired
    UsersRepo usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/users/register")
    public String saveUser(@ModelAttribute User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        usersDao.save(user);

        return "redirect:/login";

    }
}
