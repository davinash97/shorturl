package com.shorturl.auth.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	private Key getSignKey() {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] keyBytes = md.digest(secret.getBytes(StandardCharsets.UTF_8));
			return Keys.hmacShaKeyFor(keyBytes);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("SHA-256 not available", e);
		}
	}

	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractUsername(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(getSignKey())
					.build()
					.parseClaimsJws(token)
					.getBody();

			return claims.getSubject();
		} catch (JwtException | IllegalArgumentException e) {
			return null;
		}
	}
}
