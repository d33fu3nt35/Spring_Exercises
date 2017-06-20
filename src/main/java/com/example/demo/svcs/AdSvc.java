package com.example.demo.svcs;

import com.example.demo.models.Ad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 6/20/17.
 */

@Service("adSvc")
public class AdSvc {
    private List<Ad> ads;

    public AdSvc() {
        createAds();
    }

    public List<Ad> findAll() {
        return ads;
    }

    public Ad save(Ad ad) {
        ad.setId(ads.size() + 1);
        ads.add(ad);
        return ad;
    }

    public Ad findOne(long id) {
        return ads.get((int) (id - 1));
    }

    private void createAds() {
       ads = new ArrayList<>();
       save (new Ad("Playstation for sale", "$1000 OBO"));
       save (new Ad("Xbox for sale", "$1000 OBO"));
    }
}
