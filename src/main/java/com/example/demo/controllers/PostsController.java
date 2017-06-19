package com.example.demo.controllers;

import com.example.demo.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 6/19/17.
 */

@Controller
public class PostsController {

    @GetMapping("/posts")
    public String viewAll(Model model) {
        List<Post> list = new ArrayList<Post>();
        list.add(new Post("title 1", "body 1"));
        list.add(new Post("title 2", "body 2"));
        model.addAttribute("list", list);
        return "posts/index";
    }

    @GetMapping("/posts/show")
    public String viewIndividualPost(Model model) {
        Post post = new Post("FirstTitle", "FirstBody");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String showPostForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String savePost() {
        return "create a new post";
    }


}
