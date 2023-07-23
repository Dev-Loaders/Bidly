package com.bidly.bidly.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<List<Bid>> getBids() {
        List<Bid> bids = service.getAllBids();
        if (bids.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(bids);
    }

    @PostMapping("users/{userSubject}/jobs/{jobId}/bids")
    public ResponseEntity<Bid> addBidToJob(@PathVariable String userSubject,
                                           @PathVariable String jobId,
                                           @RequestParam("amount") int amount) {

        Bid bid = service.addBidToJob(userSubject, jobId, amount);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bid.getBidId())
                .toUri();

        return ResponseEntity.created(location).body(bid);
    }

    @GetMapping("users/{userSubject}/bids")
    public ResponseEntity<List<Bid>> getBidByUserId(@PathVariable String userSubject) {
        return ResponseEntity.ok(service.getBidByUserId(userSubject));
    }

}
