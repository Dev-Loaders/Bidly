package com.bidly.bidly.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class Controller {

    private final BidlyService service;

    @Autowired
    public Controller(BidlyService service) {
        this.service = service;
    }

    @GetMapping()
    public String returnHome() {
        return "home";
    }

    @GetMapping("/api/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping("/api/user")
    public BidlyUser getUser() {
        return service.getUser(1L);
    }
}
