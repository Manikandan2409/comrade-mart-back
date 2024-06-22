package com.demon.comrade_mart.utils;

//import com.demon.comrade_mart.utils.AccessDeniedException;
//import com.demon.comrade_mart.entity.Users;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.util.Date;
//
//public class JwtUtils {
//    private static String secret = "ComradeSuperMarketSpringWeb";
//    private static long expiryDuration = 60 * 60;
//
//    public static  String   generateJwt(Users user){
//
//        long milliTime = System.currentTimeMillis();
//        long expiryTime = milliTime + expiryDuration * 1000; //1hr duration
//
//        Date issuedAt = new Date(milliTime);
//        Date expiryAt = new Date(expiryTime);
//
//        // claims
//        Claims claims = Jwts.claims()
//                .setIssuer(String.valueOf(user.getId()))
//                .setIssuedAt(issuedAt)
//                .setExpiration(expiryAt);
//
//        // optional claims
//        //claims.put("type", user.getUserType());
//        claims.put("name", user.getUsername());
//        claims.put("emailId", user.getEmail());
//
////        // generate jwt using claims
////        return Jwts.builder()
////                .setClaims(claims)
////                .signWith(SignatureAlgorithm.HS512, secret)
////                .compact();
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(secret, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//    private static Claims verify(String authorization) throws Exception {
//
//        try {
//            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
//            return claims;
//        } catch(Exception e) {
//            throw new AccessDeniedException("Access Denied",e);
//        }
//    }
//
//        public static Long extractUserIdFromToken(String token) {
//            Claims claims = null;
//            try {
//                claims = verify(token);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            return claims.get("userId", Long.class);
//    }
//
//
//}
////package com.demon.comrade_mart.utils;
////
////import com.demon.comrade_mart.entity.Users;
////import io.jsonwebtoken.Claims;
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.SignatureAlgorithm;
////import io.jsonwebtoken.security.Keys;
////
////import javax.crypto.SecretKey;
////import java.security.SecureRandom;
////import java.util.Date;
////
////public class JwtUtils {
////    // Replace this with a securely generated key, not hardcoded in production
////    private static final String secretKeyString = "ComradeSuperMarketSpringWeb"; // Example only, not for production
////
////    // Ensure this is a valid base64-encoded key at least 512 bits long (generated securely)
////    private static final byte[] secretKeyBytes = secretKeyString.getBytes();
////
////    private static final SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
////    private static final long expiryDuration = 60 * 60; // 1 hour
////
////    public static String generateJwt(Users user) {
////        long milliTime = System.currentTimeMillis();
////        long expiryTime = milliTime + expiryDuration * 1000; // 1-hour duration
////
////        Date issuedAt = new Date(milliTime);
////        Date expiryAt = new Date(expiryTime);
////
////        // claims
////        Claims claims = Jwts.claims()
////                .setIssuer(String.valueOf(user.getId()))
////                .setIssuedAt(issuedAt)
////                .setExpiration(expiryAt);
////
////        // optional claims
////        claims.put("name", user.getUsername());
////        claims.put("emailId", user.getEmail());
////
////        // generate jwt using claims
////        return Jwts.builder()
////                .setClaims(claims)
////                .signWith(secretKey, SignatureAlgorithm.HS512)
////                .compact();
////    }
////
////    public Claims verify(String authorization) throws Exception {
////        try {
////            return Jwts.parserBuilder()
////                    .setSigningKey(secretKey)
////                    .build()
////                    .parseClaimsJws(authorization)
////                    .getBody();
////        } catch (Exception e) {
////            throw new AccessDeniedException("Access Denied", e);
////        }
////    }
////}
//package com.demon.comrade_mart.utils;
//
//import com.demon.comrade_mart.entity.Users;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//import java.security.Key;
//import java.util.Date;
//
//public class JwtUtils {
//    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//    private static final long expiryDuration = 60 * 60; // 1 hour in seconds
//
//    public static String generateJwt(Users user) {
//        long nowMillis = System.currentTimeMillis();
//        long expiryMillis = nowMillis + expiryDuration * 1000;
//
//        Date now = new Date(nowMillis);
//        Date expiry = new Date(expiryMillis);
//
//        Claims claims = Jwts.claims()
//                .setIssuer(String.valueOf(user.getId()))
//                .setIssuedAt(now)
//                .setExpiration(expiry);
//        claims.put("userId",user.getId());
//        claims.put("name", user.getUsername());
//     //   claims.put("emailId", user.getEmail());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(secretKey, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//    public static Claims verify(String token) {
//        try {
//            return Jwts.parserBuilder()
//                    .setSigningKey(secretKey)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (JwtException | IllegalArgumentException e) {
//            System.out.println("EException Occured");
//            throw new RuntimeException("Failed to parse JWT token", e);
//        }
//    }
//
//    public static Long extractUserIdFromToken(String token) {
//        Claims claims = verify(token);
//        return claims.get("userId", Long.class);
//    }
//}

import com.demon.comrade_mart.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
//
//public class JwtUtils {
//    // Ensure this is a securely generated key, not hardcoded in production
////    private static final String secretKeyString = "ComradeSuperMarketSpringApplicaiton";
////    private static final byte[] secretKeyBytes = secretKeyString.getBytes();
////    private static final SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
//
//    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//
//    private static final long expiryDuration = 60 * 60; // 1 hour in seconds
//
//    public static String generateJwt(Users user) {
//        long nowMillis = System.currentTimeMillis();
//        long expiryMillis = nowMillis + expiryDuration * 1000;
//
//        Date now = new Date(nowMillis);
//        Date expiry = new Date(expiryMillis);
//
//        Claims claims = Jwts.claims()
//                .setIssuer(String.valueOf(user.getId()))
//                .setIssuedAt(now)
//                .setExpiration(expiry);
//        claims.put("userId", user.getId());
//        claims.put("name", user.getUsername());
//        // claims.put("emailId", user.getEmail());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(secretKey, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
////    public static Claims verify(String token) {
////        try {
////            return Jwts.parserBuilder()
////                    .setSigningKey(secretKey)
////                    .build()
////                    .parseClaimsJws(token)
////                    .getBody();
////        } catch (JwtException | IllegalArgumentException e) {
////            throw new RuntimeException("Failed to parse JWT token", e);
////        }
////    }
//private static Claims verify(String authorization) throws Exception {
//
//    try {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(secretKey)
//                .build()
//                .parseClaimsJws(authorization)
//                .getBody();
//        return  claims;
//
//    } catch(Exception e) {
//        throw new AccessDeniedException("Access Denied",e);
//    }
//
//}
//
//    public static Long extractUserIdFromToken(String token) {
//        Claims claims = null;
//        try {
//            claims = verify(token);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return claims.get("userId", Long.class);
//    }
//}

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.catalina.User;

import javax.crypto.SecretKey;

import java.util.Date;

public class JwtUtils {

    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long expiryDuration = 60 * 60 * 1000; // 1 hour in milliseconds

    // Method to generate a JWT token
    public static String generateJwt(Users users) {
        long nowMillis = System.currentTimeMillis();
        long expiryMillis = nowMillis + expiryDuration;

        Date now = new Date(nowMillis);
        Date expiry = new Date(expiryMillis);

        Claims claims = Jwts.claims()
                .setIssuer(String.valueOf(1))
                .setIssuedAt(now)
                .setExpiration(expiry);
        claims.put("userId", users.getId());
        claims.put("name", users.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // Method to verify and parse the JWT token
    private static Claims verify(String authorization) throws Exception {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(authorization)
                    .getBody();
        } catch (Exception e) {
            // Handle exception properly or rethrow if necessary
            throw new AccessDeniedException("Failed to verify JWT token", e);
        }
    }


    public static Long extractUserIdFromToken(String token) {
        Claims claims = null;
        try {
            claims = verify(token);
        } catch (Exception e) {
            throw new AccessDeniedException("Error in getting claims",e);

        }
        return  claims.get("userId",Long.class);
    }
}
