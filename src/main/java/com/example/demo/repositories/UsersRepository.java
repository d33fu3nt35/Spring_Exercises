package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by daniel on 6/22/17.
 */
public interface UsersRepository extends CrudRepository <User, Long> {
}
