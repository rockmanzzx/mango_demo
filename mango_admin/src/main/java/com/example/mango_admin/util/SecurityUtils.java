package com.example.mango_admin.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtils {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static void checkAuthentication(HttpServletRequest request) {
        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
