package es.agonzalez.taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.agonzalez.taskmanager.dtos.LoginRequest;
import es.agonzalez.taskmanager.dtos.LoginResponse;
import es.agonzalez.taskmanager.security.auth.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private JwtService service;

  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
    if("user".equals(req.username()) && "pass".equals(req.password())) {
      String token = service.generate(req.username(), 1000L * 60 * 60);
      return ResponseEntity.ok(new LoginResponse(token));
    }
    return ResponseEntity.status(401).build();
  }
}
