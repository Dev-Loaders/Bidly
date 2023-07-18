package com.bidly.bidly.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class BidlyUserController {

    private final BidlyUserService service;

    @Autowired
    public BidlyUserController(BidlyUserService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping(path = "{userId}")
    public BidlyUser getUserByJwtId(@PathVariable String userId) {
        return service.getUserByJwtId(Long.valueOf(userId));
    }


}
