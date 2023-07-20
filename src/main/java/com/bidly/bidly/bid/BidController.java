package com.bidly.bidly.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class BidController {

    private final BidService service;

    @Autowired
    public BidController(BidService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Bid> getBids() {
        return service.getAllBids();
    }

    @PostMapping("users/{userSubject}/jobs/{jobId}/bids")
    public ResponseEntity<Bid> addBidToJob(@PathVariable String userSubject,
                                           @PathVariable String jobId,
                                           @RequestParam("amount") int amount) {
        return service.addBidToJob(userSubject, jobId, amount);
    }


}
