package com.bidly.bidly.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BidlyUserRepository {
    private final JpaBidlyUserRepository repo;

    @Autowired
    public BidlyUserRepository(JpaBidlyUserRepository repo) {
        this.repo = repo;
    }

    public BidlyUser getUser(Long jwtId) {
        System.out.println(repo.findById(jwtId).orElse(null));
        return repo.findById(jwtId).orElse(null);
    }
}
