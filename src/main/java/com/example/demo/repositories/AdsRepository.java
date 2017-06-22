package com.example.demo.repositories;

import com.example.demo.models.Ad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by daniel on 6/21/17.
 */

public interface AdsRepository extends CrudRepository <Ad, Long> {

    Ad findByTitle(String title);
    List<Ad> findByTitleIsLike(String title);

}
