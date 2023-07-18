package com.bidly.bidly.bid;

import com.bidly.bidly.job.Job;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BidService {

    private final BidRepository bidRepo;

    @Autowired
    public BidService(BidRepository bidRepo) {
        this.bidRepo = bidRepo;
    }

    public List<Bid> getAllBids() {
        List bids = new ArrayList<>();
        bidRepo.getAllBids().forEach(bids::add);
        return bids;
    }
}
