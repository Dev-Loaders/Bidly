package com.bidly.bidly.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtValidation validation;

    @Autowired
    public CustomAuthenticationSuccessHandler(JwtValidation validation){
        this.validation = validation;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OidcUser token = (OidcUser) authentication.getPrincipal();
        validation.validateJwt(token);
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        response.sendRedirect("https://bidly.vercel.app/workspace");
    }
}
