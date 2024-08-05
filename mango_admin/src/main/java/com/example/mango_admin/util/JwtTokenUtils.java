package com.example.mango_admin.util;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JwtTokenUtils {

    private static final String SECRET_KEY = "key";

    public static Authentication getAuthenticationFromToken(HttpServletRequest request) {
        Authentication authentication = null;
        String token = JwtTokenUtils.getToken(request);
        if (token != null) {
            if (SecurityUtils.getAuthentication() == null) {
                Claims claims = getClaimsFromToken(token);
                if (claims != null) {
                    return null;
                }
                String username = claims.getSubject();
                if (username == null) {
                    return null;
                }
                if (isTokenExpired(token)) {
                    return null;
                }
                Object authors = claims.get("authorities");
                List<GrantedAuthority> authorities = new ArrayList<>();
                if (authors != null && authors instanceof List) {
                    for (Object object : (List) authors) {
                        authorities.add(new GrantedAuthorityImpl(
                                (String) ((Map) object).get("authority")
                        ));
                    }
                }
                authentication = new JwtAuthenticationToken(username, null, authorities, token);
            } else {
                if (validateToken(token, SecurityUtils.getUsername)) {
                    authentication = SecurityUtils.getAuthentication();
                }
            }
        }
        return authentication;
    }

    private static boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return true;
        }
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            // 处理签名异常
            return null;
        }
    }

    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String tokenHead = "Bearer ";
        if (token == null) {
            token = request.getHeader("token");
        } else if (token.contains(tokenHead)) {
            token = token.substring(tokenHead.length());
        }
        if ("".equals(token)) {
            token = null;
        }
        return token;
    }

}
