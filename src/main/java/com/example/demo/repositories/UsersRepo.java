package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 6/22/17.
 */
@Repository
public interface UsersRepo extends CrudRepository <User, Long> {
    public User findByUsername (String username);
}
