package com.bidly.bidly.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomBearerTokenFilter customBearerTokenFilter;
    @Autowired
    public SecurityConfiguration(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
                                 CustomBearerTokenFilter customBearerTokenFilter){
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customBearerTokenFilter = customBearerTokenFilter;
    }

    @Bean
    DefaultSecurityFilterChain defaultChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()

                )
                .cors(withDefaults())
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(customAuthenticationSuccessHandler)
                )
                .addFilterBefore(customBearerTokenFilter, AuthorizationFilter.class)
                .csrf().disable()
                .build();
    }
}
