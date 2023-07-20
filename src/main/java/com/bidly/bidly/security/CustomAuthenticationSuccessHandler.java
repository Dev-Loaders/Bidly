package com.bidly.bidly.security;

import com.bidly.bidly.user.BidlyUserService;
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
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        System.out.println("this is it " + session.getAttribute("token"));
        createUserAccountIfItDoesNotExist((OidcUser) session.getAttribute("token"));
        response.sendRedirect("http://localhost:3000/workspace");
    }

    private void createUserAccountIfItDoesNotExist(@AuthenticationPrincipal OidcUser oidcUser){
        if (userService.getUserByJwtId(oidcUser.getSubject()) == null){
            System.out.println(oidcUser.getSubject());
            userService.createUser(oidcUser);
        }
    }
}
