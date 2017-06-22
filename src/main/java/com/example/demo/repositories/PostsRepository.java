package com.example.demo.repositories;

import com.example.demo.models.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by daniel on 6/21/17.
 */
public interface PostsRepository extends CrudRepository <Post, Long> {



}
