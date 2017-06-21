package com.example.demo.svcs;

import com.example.demo.models.Ad;
import com.example.demo.models.Post;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 6/20/17.
 */
@Service("postSvc")
public class PostSvc {
    private List<Post> posts;

    public PostSvc() {
        createPosts();
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post save(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }

    public Post findOne(long id) {
        return posts.get((int) (id - 1));
    }

    private void createPosts() {
        posts = new ArrayList<>();
        save(new Post("Playstation for sale", "$1000 OBO"));
        save(new Post("Xbox for sale", "$1000 OBO"));
        save(new Post("Nintendo for sale", "$1000 OBO"));
        save(new Post("PC for sale", "$1000 OBO"));
    }
}