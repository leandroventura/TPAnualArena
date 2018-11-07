package Server;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import Server.InvalidTokenException;

public class Security {
	private String secret;

	public Security(String secret) {
		this.secret = secret;
	}
	
	public Long user(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			return jwt.getClaim("user").asLong();
		} catch (JWTVerificationException e) {
			throw new InvalidTokenException(e);
		}
	}
}
