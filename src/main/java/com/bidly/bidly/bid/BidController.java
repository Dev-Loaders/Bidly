package com.bidly.bidly.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
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
}
