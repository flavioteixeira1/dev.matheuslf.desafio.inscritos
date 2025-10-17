package dev.matheuslf.desafio.inscritos.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.matheuslf.desafio.inscritos.Repository.UserRepository;
import dev.matheuslf.desafio.inscritos.SecurityConfig.TokenConfig;
import dev.matheuslf.desafio.inscritos.dto.Request.LoginRequest;
import dev.matheuslf.desafio.inscritos.dto.Request.RegisterUserRequest;
import dev.matheuslf.desafio.inscritos.dto.Response.LoginResponse;
import dev.matheuslf.desafio.inscritos.dto.Response.RegisterUserResponse;
import dev.matheuslf.desafio.inscritos.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autenticacao")
public class AuthenticationController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthenticationController(UserRepository repository, PasswordEncoder passwordEncoder, 
    AuthenticationManager authenticationManager, TokenConfig tokenConfig){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/logar")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("Tentando autenticar: " + loginRequest.email());
        UsernamePasswordAuthenticationToken userAndPass = 
        new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        User newUser = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(newUser); 
    
        System.out.println("Token gerado para: " + newUser.getEmail());
        return ResponseEntity.ok(new LoginResponse(token));
    }
    /* endpoint para cadastrar novo usuário no sistema - */
    @PostMapping("/cadastrar")
    public ResponseEntity<RegisterUserResponse> cadastrar (@Valid @RequestBody RegisterUserRequest request){
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setEmail(request.email());
        newUser.setName(request.name());
        newUser.setRole(request.role());
        repository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(newUser.getName(), newUser.getEmail()));
        
    }



}
