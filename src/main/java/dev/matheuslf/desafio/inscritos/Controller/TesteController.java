package dev.matheuslf.desafio.inscritos.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/teste")
public class TesteController {
    
    @Value("${api.security.token.secret}")
    private String secret;
    
    @GetMapping("/secret")
    public String testSecret() {
        return "Secret length: " + secret.length() + 
               ", First 5 chars: " + secret.substring(0, Math.min(5, secret.length()));
    }
}