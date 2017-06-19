package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by daniel on 6/19/17.
 */

@Controller
public class MathController {
    @GetMapping("/add/{number1}/and/{number2}")
    public String add(@PathVariable int number1, @PathVariable int number2, Model model) {
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);
        model.addAttribute("result", number1 + number2);
        return "arithmetic/addition/result";
    }

    @GetMapping("/subtract/{number1}/from/{number2}")
    @ResponseBody
    public int subtract(@PathVariable int number1, @PathVariable int number2) {
        return  number1 - number2;
    }

    @GetMapping("/multiply/{number1}/and/{number2}")
    @ResponseBody
    public int multiply(@PathVariable int number1, @PathVariable int number2) {
        return  number1 * number2;
    }

    @GetMapping("/divide/{number1}/by/{number2}")
    @ResponseBody
    public double divide(@PathVariable double number1, @PathVariable double number2) {
        return  number1 / number2;
    }

}
