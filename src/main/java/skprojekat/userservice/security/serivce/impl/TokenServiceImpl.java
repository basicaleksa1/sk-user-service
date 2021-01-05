package skprojekat.userservice.security.serivce.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import skprojekat.userservice.security.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService{
	
	@Value("${oauth.jwt.secret}")
	private String jwtSecret;

	@Override
	public String generate(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
	}

	@Override
	public Claims parseToken(String jwt) {
		Claims claims;
	    try {
	        claims = Jwts.parser()
	                .setSigningKey(jwtSecret)
	                .parseClaimsJws(jwt)
	                .getBody();
	    } catch (Exception e) {
	    	System.out.println("desio se exception");
	        return null;
	    }
	    return claims;
	}

}

