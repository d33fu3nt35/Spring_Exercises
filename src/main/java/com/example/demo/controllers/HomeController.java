package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by daniel on 6/19/17.
 */

@Controller
public class HomeController {

    @GetMapping("/home")

    // With a ResponseBody annotation it will return the value produced by this method as response.
    public String welcome() {
        return "home"; // it'll look for the view within templates if no ResponseBody annotation is found
    }
}