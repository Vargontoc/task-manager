package es.agonzalez.taskmanager.security.auth;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter{


  @Autowired
  private JwtService service;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header != null && header.startsWith("Bearer ")) {
          String token = header.substring(7);
          try {
              String username = service.validate(token);
              var auth = new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
              SecurityContextHolder.getContext().setAuthentication(auth);
          } catch (Exception e) { }
        }
        filterChain.doFilter(request, response);
  }
}
