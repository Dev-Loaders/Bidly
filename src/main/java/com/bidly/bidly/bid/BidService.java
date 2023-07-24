package com.bidly.bidly.bid;

import com.bidly.bidly.job.Job;
import com.bidly.bidly.job.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BidService {

    private final BidRepository bidRepo;
    private final JobRepository jobRepo;

    @Autowired
    public BidService(BidRepository bidRepo, JobRepository jobRepo) {
        this.bidRepo = bidRepo;
        this.jobRepo = jobRepo;
    }

    public List<Bid> getAllBids() {
        List<Bid> bids = new ArrayList<>();
        bidRepo.getAllBids().forEach(bids::add);
        return bids;
    }

    public Bid addBidToJob(String userSubject, String jobId, int amount) {
        Job job = jobRepo.getJobById(jobId);
        Bid bid = new Bid(userSubject, amount, job.getTitle());
        bidRepo.save(bid);
        job.addBids(bid);
        return bid;
    }

    public List<Bid> getBidByUserId(String userSubject) {

        return bidRepo.getBidsByUserSubject(userSubject);
    }

    public Bid acceptBid(String jobId, String bidId) {
        Job job = jobRepo.getJobById(jobId);
        Bid bid = bidRepo.getBidByBidId(bidId);
        job.setAcceptedBid(bid);
        return job.getAcceptedBid();
    }
}
