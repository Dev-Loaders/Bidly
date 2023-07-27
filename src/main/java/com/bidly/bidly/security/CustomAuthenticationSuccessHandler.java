package com.bidly.bidly.security;

import com.bidly.bidly.user.BidlyUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
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
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        String tokenValue = oidcUser.getIdToken().getTokenValue();

        try {
            validation.validateJwt(oidcUser);
        } catch (JwtValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        String redirectUrl = "https://bidly-git-merged-branches-dev-loaders.vercel.app/workspace?token=" + URLEncoder.encode(tokenValue, StandardCharsets.UTF_8);
        createUserAccountIfItDoesNotExist(oidcUser);
        response.sendRedirect(redirectUrl);
    }

    private void createUserAccountIfItDoesNotExist(@AuthenticationPrincipal OidcUser oidcUser){
        if (userService.getUserByJwtId(oidcUser.getSubject()) == null){
            userService.createUser(oidcUser);
        }
    }
}
