package com.bidly.bidly.main;

import com.bidly.bidly.user.BidlyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CreateUserController {

    private final BidlyUserService service;

    @Autowired
    public CreateUserController(BidlyUserService service) {
        this.service = service;
    }

    @GetMapping
    public String createUserAccountIfItDoesNotExist(@AuthenticationPrincipal OidcUser oidcUser){
        if(service.getUserByJwtId(oidcUser.getSubject()) == null){
            System.out.println(oidcUser.getSubject());
            service.createUser(oidcUser);
            return "new User Created";
        }
        return "user found";
    }
}
