package com.example.demo.controllers;

import com.example.demo.models.Ad;
import com.example.demo.models.User;
import com.example.demo.repositories.AdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by daniel on 6/20/17.
 */

@Controller
public class AdsController {

    private AdsRepository adsDao;

    @Value("${file-upload-path}")
    private String uploadPath;

    @Autowired
    public AdsController(AdsRepository adsDao) {

        this.adsDao = adsDao;

    }

    @GetMapping("/ads.json")
    public @ResponseBody Iterable<Ad> viewAllAds() {
        return adsDao.findAll();
    }

    @GetMapping("/ads/ajax")
    public String viewAllAdsUsingAnAJAXCall() {
        return "ads/ajax";
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

//    @PostMapping("/ads/create")
//    public String saveAd(@RequestParam(name = "title") String title,
//                         @RequestParam(name = "description") String description,
//                         Model model
//    ) {
//        Ad ad = new Ad(title, description);
//        model.addAttribute("ad", ad);
//        return "ads/create";
//    }

    @PostMapping("/ads/create")
    public String saveAd(
            @Valid Ad ad,
            Errors validation,
            @RequestParam(name = "file") MultipartFile uploadedFile,
            Model model
    ) {
        if (ad.getTitle().endsWith("?")) {
            validation.rejectValue(
                    "title",
                    "ad.title",
                    "You can't be unsure about your title!"
            );
        }

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("ad", ad);
            return "ads/create";
        }

        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ad.setAuthor(user);
        ad.setImageUrl(filename);
        adsDao.save(ad);
        model.addAttribute("ad", ad);
        return "redirect:/ads";
    }
}
