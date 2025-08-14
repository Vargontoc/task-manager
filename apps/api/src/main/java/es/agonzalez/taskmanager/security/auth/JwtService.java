package es.agonzalez.taskmanager.security.auth;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService
{
  @Value("${JWT_SECRET:change-me}")
  private String secret;
  private Key key;

  @PostConstruct
  private void init() {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  public String generate(String username, long ttlMilis) {
    long now = System.currentTimeMillis();
    return Jwts.builder().setSubject(username).setIssuedAt(new Date(now)).setExpiration(new Date(now + ttlMilis)).signWith(key, SignatureAlgorithm.HS256).compact();
  }

  public String validate(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
  }


}
