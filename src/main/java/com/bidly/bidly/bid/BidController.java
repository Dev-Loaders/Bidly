package com.bidly.bidly.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
        if (bids == null || bids.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(bids);
    }

    @GetMapping("users/{userSubject}/bids")
    public ResponseEntity<List<Bid>> getBidByUserId(@PathVariable String userSubject) {
        List<Bid> bids = service.getBidByUserId(userSubject);
        if (bids == null || bids.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(bids);
    }

    @GetMapping("users/{userSubject}/bids/accepted")
    public ResponseEntity<List<Bid>> getAcceptedBid(@PathVariable String userSubject) {
        List<Bid> bids = service.getAcceptedBidByUserId(userSubject);
        if (bids == null || bids.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(bids);
    }

    @PostMapping("users/{userSubject}/jobs/{jobId}/bids")
    public ResponseEntity<Bid> addBidToJob(@PathVariable String userSubject,
                                           @PathVariable String jobId,
                                           @RequestParam("amount") int amount) {
        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid bid amount");
        }

        Bid bid = service.addBidToJob(userSubject, jobId, amount);
        if (bid == null) {
            return ResponseEntity.internalServerError().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bid.getBidId())
                .toUri();

        return ResponseEntity.created(location).body(bid);
    }

    @PutMapping("users/{userSubject}/jobs/{jobId}/bids/{bidId}")
    public ResponseEntity<Bid> acceptBid(@PathVariable String userSubject,
                                         @PathVariable String jobId,
                                         @PathVariable String bidId) {

        return ResponseEntity.accepted().body(service.acceptBid(jobId, bidId));
    }
}
