package br.com.victor.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTProviders {

  @Value("${security.token.secret}")
  private String secretKey;

  public String validadeToken(String token){
    token = token.replace("Bearer","").trim();
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    try {
      var subject = JWT.require(algorithm)
      .build()
      .verify(token)
      .getSubject();
    return subject;
    } catch (JWTVerificationException ex) {
      ex.printStackTrace();
      return "";

    }

  }
  
}