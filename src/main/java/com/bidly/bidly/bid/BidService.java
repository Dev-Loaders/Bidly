package com.bidly.bidly.bid;

import com.bidly.bidly.job.Job;
import com.bidly.bidly.job.JobRepository;
import com.bidly.bidly.user.BidlyUser;
import com.bidly.bidly.user.BidlyUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        List bids = new ArrayList<Bid>();
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
        List<Bid> bids = bidRepo.getBidsByUserSubject(userSubject);

        if (bids.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There are no bids");
        }
        return bids;
    }
}
