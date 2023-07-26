package com.bidly.bidly.security;

import com.bidly.bidly.user.BidlyUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtValidation validation;

    private final BidlyUserService userService;

    @Autowired
    public CustomAuthenticationSuccessHandler(JwtValidation validation, BidlyUserService userService){
        this.validation = validation;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String token = authentication.getPrincipal().toString();
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        String tokenValue = oidcUser.getIdToken().getTokenValue();
//        validation.validateJwt(oidcUser);
        String redirectUrl = "http://localhost:3000/workspace?token=" + URLEncoder.encode(tokenValue, StandardCharsets.UTF_8);
        createUserAccountIfItDoesNotExist(oidcUser);
        response.sendRedirect(redirectUrl);
    }

    private void createUserAccountIfItDoesNotExist(@AuthenticationPrincipal OidcUser oidcUser){
        if (userService.getUserByJwtId(oidcUser.getSubject()) == null){
            userService.createUser(oidcUser);
        }
    }
}
