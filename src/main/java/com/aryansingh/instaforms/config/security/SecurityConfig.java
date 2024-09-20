package com.aryansingh.instaforms.config.security;

import com.aryansingh.instaforms.config.security.authprovider.OrgUserAuthProvider;
import com.aryansingh.instaforms.config.security.authprovider.OrganisationAuthProvider;
import com.aryansingh.instaforms.config.security.authprovider.UserAuthProvider;
import com.aryansingh.instaforms.config.security.filters.CustomJWTAuthFilter;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static com.aryansingh.instaforms.utils.AppConstants.PUBLIC_URLS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {




    private final AuthenticationEntryPoint authEntryPoint;
    private final UserAuthProvider userAuthProvider;
    private final OrganisationAuthProvider organisationAuthProvider;
    private final OrgUserAuthProvider orgUserAuthProvider;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;


    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(userAuthProvider).authenticationProvider(
                orgUserAuthProvider).authenticationProvider(organisationAuthProvider);

        return authenticationManagerBuilder.build();
    }


    @Bean
    public CustomJWTAuthFilter customAuthenticationFilter(HttpSecurity http) throws Exception {
        return new CustomJWTAuthFilter(authManager(http));
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(customizer->customizer.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("http://localhost:5173"));
                    configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
                    configuration.setAllowCredentials(true);
                    configuration.addExposedHeader("Message");
                    configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
                    return configuration;
                }))

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(customizer->customizer.requestMatchers(PUBLIC_URLS)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(customizer->customizer
                        .accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(authEntryPoint))
                .sessionManagement(customizaer->customizaer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        http.addFilterBefore(customAuthenticationFilter(http), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
