package com.bidly.bidly.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import com.bidly.bidly.user.BidlyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.oidc.authentication.OidcIdTokenValidator;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class CreateUserController {

    private final BidlyUserService service;
    private final JwtValidation validation;

    @Autowired
    public CreateUserController(BidlyUserService service, JwtValidation validation) {
        this.service = service;
        this.validation= validation;
    }

    @GetMapping
    public String createUserAccountIfItDoesNotExist(@AuthenticationPrincipal OidcUser oidcUser){
        if (oidcUser == null){
            return "please Login first";
        }

        validation.validateJwt(oidcUser);

        if(service.getUserByJwtId(oidcUser.getSubject()) == null){
            System.out.println(oidcUser.getSubject());
            service.createUser(oidcUser);
            return "new User Created";
        }
        return "user found";
    }
}
