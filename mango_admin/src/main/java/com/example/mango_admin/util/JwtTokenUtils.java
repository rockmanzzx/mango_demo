package com.example.mango_admin.util;

import com.example.mango_admin.security.GrantedAuthorityImpl;
import com.example.mango_admin.security.JwtAuthenticationToken;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class JwtTokenUtils {

    private static final String SECRET_KEY = "abcdefgh";

    /**
     * 用户名称
     */
    private static final String USERNAME = Claims.SUBJECT;
    /**
     * 创建时间
     */
    private static final String CREATED = "created";
    /**
     * 权限列表
     */
    private static final String AUTHORITIES = "authorities";

    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;

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
                if (validateToken(token, SecurityUtils.getUsername())) {
                    authentication = SecurityUtils.getAuthentication();
                }
            }
        }
        return authentication;
    }

    private static boolean validateToken(String token, String username) {
        String name = getUsernameFromToken(token);
        return username.equals(name) && !isTokenExpired(token);
    }

    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
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

    public static String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USERNAME, SecurityUtils.getUsername(authentication));
        claims.put(CREATED, new Date());
        claims.put(AUTHORITIES, authentication.getAuthorities());
        return generateToken(claims);
    }

    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

}
