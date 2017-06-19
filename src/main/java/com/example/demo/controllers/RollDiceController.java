package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by daniel on 6/19/17.
 */

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice/{guess}")
    public String add(@PathVariable int guess, Model model) {
        int diceRoll = (int) (Math.round(Math.random() * 6 + 1));
        System.out.println(diceRoll);
        model.addAttribute("diceRoll", diceRoll);
        model.addAttribute("guess", guess);
        return "roll-dice/index";
    }


}
