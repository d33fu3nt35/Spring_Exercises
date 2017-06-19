package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by daniel on 6/19/17.
 */

@Controller
public class ResumeController {
        @GetMapping("/resume")
        public String add() {
            return "resume/index";
        }
}
