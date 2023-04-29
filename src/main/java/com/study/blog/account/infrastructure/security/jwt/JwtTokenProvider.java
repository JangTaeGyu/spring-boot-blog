package com.study.blog.account.infrastructure.security.jwt;

import com.study.blog.springboot.exception.HttpException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final String PROJECT_ISSUER = "BLOG_API";
    private final String secret;
    private final Long expirationDate;

    public JwtTokenProvider(@Value("${app.jwt-secret}") String secret,
                            @Value("${app.jwt-expiration-date}") Long expirationDate) {
        this.secret = secret;
        this.expirationDate = expirationDate;
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .requireIssuer(PROJECT_ISSUER)
                    .build()
                    .parse(token);
            return true;
        } catch (SignatureException e) {
            throw new HttpException("Invalid Jwt signature", HttpStatus.UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            throw new HttpException("Invalid Jwt token", HttpStatus.UNAUTHORIZED);
        } catch (ExpiredJwtException e) {
            throw new HttpException("Expired Jwt token", HttpStatus.UNAUTHORIZED);
        } catch (UnsupportedJwtException e) {
            throw new HttpException("Unsupported Jwt token", HttpStatus.UNAUTHORIZED);
        } catch (IllegalArgumentException e) {
            throw new HttpException("Jwt claims string is empty", HttpStatus.UNAUTHORIZED);
        }
    }

    public String generateToken(String email) {
        Date currentDate = new Date();
        Date expireDate = new Date(expireTime(currentDate));

        return Jwts.builder()
                .claim("email", email)
                .setIssuer(PROJECT_ISSUER)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("email")
                .toString();
    }

    public Long expireTime(Date currentDate) {
        if (currentDate == null) currentDate = new Date();
        return currentDate.getTime() + expirationDate;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
