package com.bidly.bidly.security;

import com.bidly.bidly.user.BidlyUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

        OidcUser token = (OidcUser) authentication.getPrincipal();
//        validation.validateJwt(token);

        Cookie tokenCookie = addTokenToCookie(request, response, token);
        response.addCookie(tokenCookie);

        createUserAccountIfItDoesNotExist(token);
        response.sendRedirect("http://localhost:3000/workspace");
    }

    private Cookie addTokenToCookie(HttpServletRequest request, HttpServletResponse response, OidcUser token) {
        Cookie tokenCookie = new Cookie("tokenCookie", token.getIdToken().getTokenValue());
        tokenCookie.setMaxAge(600);
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(false);
        tokenCookie.setSecure(true);
        System.out.println(tokenCookie.getName());

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " = " + cookie.getValue());
            }
        }
        return tokenCookie;
    }

    private void createUserAccountIfItDoesNotExist(@AuthenticationPrincipal OidcUser oidcUser){
        if (userService.getUserByJwtId(oidcUser.getSubject()) == null){
            System.out.println(oidcUser.getSubject());
            userService.createUser(oidcUser);
        }
    }
}
