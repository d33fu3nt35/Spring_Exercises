package com.example.demo.controllers;

import com.example.demo.models.Ad;
import com.example.demo.repositories.AdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by daniel on 6/20/17.
 */

@Controller
public class AdsController {

    private AdsRepository adsDao;

    @Autowired
    public AdsController(AdsRepository adsDao) {

        this.adsDao = adsDao;

    }

    @GetMapping("/ads")
    public String index(Model model) {
        Iterable<Ad> ads = adsDao.findAll();
        model.addAttribute("ads", ads);

//        Ad ad = adsDao.findByTitle("test");
//        System.out.println(ad.getDescription());

//        List<Ad> ad2 = adsDao.findByTitleIsLike("%test%");

//        model.addAttribute("ads", ad);

        return "ads/index";

    }

    @GetMapping("/ads/{id}")
    public String show(@PathVariable long id, Model model) {

        Ad ad = adsDao.findOne(id);
        model.addAttribute("ad", ad);
        return "ads/show";

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
