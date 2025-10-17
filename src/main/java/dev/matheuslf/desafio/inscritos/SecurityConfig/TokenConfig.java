package dev.matheuslf.desafio.inscritos.SecurityConfig;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import dev.matheuslf.desafio.inscritos.model.User;

@Component
public class TokenConfig {
    @Value("${api.security.token.secret}")
    private String secret;
    
    //@Value("${api.security.token.expiration}") 
    //private Long expiration = 86400l;

    public String generateToken(User newuser){
    Algorithm algorithm = Algorithm.HMAC256(secret);

     System.out.println("Secret usado para gerar token: " + secret.substring(0, Math.min(5, secret.length())) + "...");
     System.out.println("Gerando token para: " + newuser.getEmail());
     //System.out.println("Data de expiração do token " + Instant.now().plusSeconds(286400));

    return JWT.create()
            .withClaim("userId", newuser.getId())
            .withClaim("role", newuser.getRole().name()) 
            .withSubject(newuser.getEmail())
            //.withExpiresAt(Instant.now().plusSeconds(expiration))
            //.withIssuedAt(Instant.now())
            .withIssuedAt(creationDate())
            .withExpiresAt(expirationDate())
            .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {
        try{

            System.out.println("Secret usado para validar: " + secret.substring(0, Math.min(35, secret.length())) + "...");
            
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);

            //Extrair a role do token
            String role = decode.getClaim("role").asString();
            
            System.out.println("Token válido para: " + decode.getSubject());
            System.out.println("User ID: " + decode.getClaim("userId").asLong());
            System.out.println("Role: " + role); // Log para debug

            return Optional.of(JWTUserData.builder()
                .userId(decode.getClaim("userId").asLong())
                .email(decode.getSubject())
                .role(role) // AGORA incluindo a role!
                .build());
        }
        catch(JWTVerificationException exception){
            System.out.println("Erro na validação do token: " + exception.getMessage());
            System.out.println("Token : " + token.substring(0, Math.min(80, token.length())) + "...");
            return Optional.empty();
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(24).toInstant();
    }

}
