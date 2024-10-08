package com.aryansingh.instaforms.config.security.utils;

import com.aryansingh.instaforms.config.security.filters.CustomJWTAuthFilter;
import com.aryansingh.instaforms.utils.exceptions.InsufficientRolesException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        throw new InsufficientRolesException("You do not have permission to access this resource.");
    }
}
