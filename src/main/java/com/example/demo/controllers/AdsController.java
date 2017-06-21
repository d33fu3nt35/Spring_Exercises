package com.example.demo.controllers;

import com.example.demo.models.Ad;
import com.example.demo.svcs.AdSvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by daniel on 6/20/17.
 */

@Controller
public class AdsController {

    private AdSvc adsDao;

    public AdsController(AdSvc adsDao) {

        this.adsDao = adsDao;

    }

    @GetMapping("/ads")
    @ResponseBody
    public String index(Model model) {

        List<Ad> ads = adsDao.findAll();
        model.addAttribute("ads", ads);
        // TODO: Create This View / HTML File
        return "ads/index";

    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String show(@PathVariable long id) {

        return "view ad #" + id;

    }

    @GetMapping("/ads/create")
    public String showAdForm(Model model) {
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String saveAd(@RequestParam(name = "title") String title,
                         @RequestParam(name = "description") String description,
                         Model model
    ) {
        Ad ad = new Ad(title, description);
        model.addAttribute("ad", ad);
        return "ads/create";
    }

}
