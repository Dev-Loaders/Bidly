package com.bidly.bidly.bid;

import com.bidly.bidly.user.BidlyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public void save(Bid bid) {
        repo.save(bid);
    }

    public Bid getBidByBidId(String bidId){
        long longBidId = Long.parseLong(bidId);
        return repo.findById(longBidId).orElse(null);
    }

    public List<Bid> getBidsByUserSubject(String userSubject) {
        return repo.findByUserId(userSubject);
    }

    public List<Bid> getAllAcceptedBidByUserId(String userSubject) {
        return repo.findByUserIdAndAcceptedTrue(userSubject);
    }
}
