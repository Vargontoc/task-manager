package es.agonzalez.taskmanager.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
  @Autowired
  private JwtAuthFilter filter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(c -> c.disable()).authorizeHttpRequests(a -> a.requestMatchers("/api/auth/**", "/actuator/health").permitAll().anyRequest().authenticated())

      .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
      return http.build();
  }
}
