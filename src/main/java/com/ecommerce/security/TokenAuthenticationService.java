package com.ecommerce.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {
	
	@Value("${jwt.application}")
	private String application;
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private String expiration;
	
	public String generateToken(String user) {
		Date now = new Date();
		Date exp = new Date(now.getTime() + Long.parseLong(this.expiration));
		
		return Jwts.builder()
				.setIssuer(this.application)
				.setSubject(user)
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}
}
