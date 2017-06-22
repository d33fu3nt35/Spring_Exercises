package com.example.demo.svcs;

import com.example.demo.models.Post;
import com.example.demo.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by daniel on 6/20/17.
 */
@Service("postSvc")
public class PostSvc {

    private PostsRepository postsDao;
//    private List<Post> posts;

    @Autowired
    public PostSvc(PostsRepository postsDao) {

        this.postsDao = postsDao;

    }

    public Iterable<Post> findAll() {
        Iterable<Post> posts = postsDao.findAll();
        return posts;
    }

    public Post save(Post post) {
        postsDao.save(post);
        return post;
    }

    public Post findOne(long id) {
        Post post = postsDao.findOne(id);
        return post;
    }

    public Post deletePost(long id) {
        Post post = postsDao.findOne(id);
        postsDao.delete(post);
        return post;
    }
}