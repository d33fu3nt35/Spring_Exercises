package com.example.demo.controllers;

import com.example.demo.models.Ad;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repositories.UsersRepo;
import com.example.demo.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by daniel on 6/19/17.
 */

@Controller
public class PostsController {

    private PostSvc postsDao;
    private UsersRepo usersDao;

    @Autowired
    public PostsController(PostSvc postsDao, UsersRepo usersDao) {

        this.postsDao = postsDao;
        this.usersDao = usersDao;

    }

    @GetMapping("/posts.json")
    public @ResponseBody Iterable<Post> viewAllPosts() {
        return postsDao.findAll();
    }

    @GetMapping("/posts/ajax")
    public String viewAllPostsUsingAnAJAXCall() {
        return "posts/ajax";
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
    public String savePost(
            @Valid Post post,
            Errors validation,
            Model model
    ) {
        if (post.getTitle().endsWith("?")) {
            validation.rejectValue(
                    "post",
                    "post.title",
                    "You can't be unsure about your title!"
            );
        }

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setOwner(user);
        postsDao.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
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
    public String updatePost(@ModelAttribute Post post,
                             Model model
    ) {
        System.out.println(post.getTitle());
        System.out.println(post.getOwner().getUsername());
        postsDao.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@ModelAttribute Post post, Model model) {
        postsDao.deletePost(post.getId());
        model.addAttribute("msg", "Your post was deleted!");
        return "redirect:/posts";
    }

}
