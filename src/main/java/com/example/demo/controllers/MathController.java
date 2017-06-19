package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by daniel on 6/19/17.
 */

@Controller
public class MathController {

    @RequestMapping(path = "/add/3/and/4", method = RequestMethod.GET)
    @ResponseBody
    public int threePlusFour() {
        return 3 + 4;
    }

}
