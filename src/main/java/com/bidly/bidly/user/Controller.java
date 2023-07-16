package com.bidly.bidly.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "bodly.vercel.app")
public class Controller {

    private final BidlyService service;

    @Autowired
    public Controller(BidlyService service) {
        this.service = service;
    }

    @GetMapping("hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping("user")
    public BidlyUser getUser() {
        return service.getUser(1L);
    }
}
