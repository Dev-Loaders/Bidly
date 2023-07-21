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

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BidService {

    private final BidRepository bidRepo;
    private final JobRepository jobRepo;
    private final BidlyUserRepository bidlyUserRepo;

    @Autowired
    public BidService(BidRepository bidRepo, JobRepository jobRepo, BidlyUserRepository bidlyUserRepo) {
        this.bidRepo = bidRepo;
        this.jobRepo = jobRepo;
        this.bidlyUserRepo = bidlyUserRepo;
    }

    public List<Bid> getAllBids() {
        List bids = new ArrayList<Bid>();
        bidRepo.getAllBids().forEach(bids::add);
        return bids;
    }

    public ResponseEntity<Bid> addBidToJob(String userSubject, String jobId, int amount) {
        Job job = jobRepo.getJobById(jobId);
        BidlyUser bidlyUser = bidlyUserRepo.getUserByJwtId(userSubject);
        Bid bid = new Bid(bidlyUser, amount);
        bidRepo.save(bid);
        job.addBids(bid);
        return ResponseEntity.accepted().body(bid);
    }

    public ResponseEntity<List<Bid>> getBidByUserId(String userSubject) {
        BidlyUser bidlyUser = bidlyUserRepo.getUserByJwtId(userSubject);
        List<Bid> bids = bidRepo.getBidsByUser(bidlyUser);

        if (bids.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(bids, HttpStatus.OK);
        }
    }
}
