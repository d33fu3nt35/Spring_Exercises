package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repositories.UsersRepository;
import com.example.demo.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by daniel on 6/19/17.
 */

@Controller
public class PostsController {

    private PostSvc postsDao;
    private UsersRepository usersDao;

    @Autowired
    public PostsController(PostSvc postsDao, UsersRepository usersDao) {

        this.postsDao = postsDao;
        this.usersDao = usersDao;

    }

    @GetMapping("/posts")
    public String viewAll(Model model) {

        Iterable<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        // TODO: Create This View / HTML File
        return "posts/index";

    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model model) {
        Post post = postsDao.findOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String savePost(@RequestParam(name = "title") String title,
                           @RequestParam(name = "body") String body,
                           Model model
    ) {
        Post post = new Post(title, body);
        User user = usersDao.findOne(1L); // just use the first user in the db
        post.setOwner(user);
        postsDao.save(post);
        model.addAttribute("post", post);
        return "posts/create";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id,
                               Model model
    ) {

        Post post = postsDao.findOne(id);
        model.addAttribute("post", post);
        return "posts/edit";

    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body,
                             @PathVariable Long id,
                             Model model
    ) {
        Post post = new Post(id, title, body);
        postsDao.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postsDao.deletePost(id);
        return "redirect:/posts";
    }

}
