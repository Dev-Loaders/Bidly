package com.bidly.bidly.main;

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

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Autowired
    public CreateUserController(BidlyUserService service) {
        this.service = service;
    }

    @GetMapping
    public String createUserAccountIfItDoesNotExist(@AuthenticationPrincipal OidcUser oidcUser){
        if (oidcUser == null){
            return "please Login first";
        }


        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("google")
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("openid", "profile", "email")
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .clientName("Google")
                .build();

        OidcIdTokenValidator validator = new OidcIdTokenValidator(clientRegistration);
        JwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .build();
        String tokenValue = oidcUser.getIdToken().getTokenValue();
        Jwt jwtToken = jwtDecoder.decode(tokenValue);

        try {
            Jwt jwt = jwtToken;
            validator.validate(jwt);
            System.out.println("success");
            // Token validation succeeded
        } catch (JwtValidationException e) {
            // Token validation failed
        }


        if(service.getUserByJwtId(oidcUser.getSubject()) == null){
            System.out.println(oidcUser.getSubject());
            service.createUser(oidcUser);
            return "new User Created";
        }
        return "user found";
    }
}
