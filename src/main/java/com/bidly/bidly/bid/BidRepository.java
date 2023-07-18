package com.bidly.bidly.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BidRepository {

    private final JpaBidRepository repo;

    @Autowired
    public BidRepository(JpaBidRepository repo) {
        this.repo = repo;
    }

    public Iterable<Bid> getAllBids() {
        return repo.findAll();
    }
}
