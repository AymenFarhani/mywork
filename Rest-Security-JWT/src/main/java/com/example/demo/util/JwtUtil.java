package com.example.demo.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;

	public boolean isValideToken(String token, String username) {
		return (getUsername(token).equals(username)
				&& !getExpirationDate(token).before(new Date(System.currentTimeMillis())));
	}

	public Date getExpirationDate(String token) {
		return getClaims(token).getExpiration();
	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();

	}

	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	public String generateToken(String subject) {
		return Jwts.builder().setId("jwtId").setSubject(subject).setIssuer("IT")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30)))
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secret.getBytes())).compact();
	}

}
