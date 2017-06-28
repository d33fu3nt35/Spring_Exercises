package com.example.demo.repositories;

import com.example.demo.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 6/21/17.
 */
@Repository
public interface PostsRepository extends CrudRepository <Post, Long> {



}
