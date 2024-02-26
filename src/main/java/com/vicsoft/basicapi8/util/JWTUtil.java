package com.vicsoft.basicapi8.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class JWTUtil {

    private static final String TOKEN_SUBJECT = "BASIC_API_JWT";

    private static final String TOKEN_ISSUER = "victory";

    private static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 10; // ms (10 minutes)

    public static Map<String, Object> newToken(HttpServletRequest httpServletRequest, String userId) {
        String token = Jwts.builder()
                .setIssuer(TOKEN_ISSUER)
                .setSubject(TOKEN_SUBJECT)
                .setAudience(httpServletRequest.getRemoteAddr())
                .setExpiration(new Date(Instant.now().toEpochMilli() + TOKEN_EXPIRATION_TIME))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, userId)
                .compact();

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);

        return body;
    }

    public static boolean validation(String token, String userId) {
        try {
            Jwts.parser().setSigningKey(userId).parseClaimsJws(token).getBody();
            return true;
        } catch (SignatureException | MalformedJwtException | IllegalArgumentException e) {
            log.warn("Invalid JWT Format");
        } catch (ExpiredJwtException eje) {
            log.warn("JWT is expired");
        } catch (UnsupportedJwtException uje) {
            log.warn("Unsupported JWT");
        }
        return false;
    }

}
