package dev.matheuslf.desafio.inscritos.SecurityConfig;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class SecurityFilter extends OncePerRequestFilter{
    
    private final TokenConfig tokenConfig;
    
    public SecurityFilter(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException { 
        
   
    String authorizedHeader = request.getHeader("Authorization");
    System.out.println("Header Authorization recebido: " + authorizedHeader); //para ver o header na saída do terminal
    
    if (!Strings.isEmpty(authorizedHeader) && authorizedHeader.startsWith("Bearer ")){
        String token = authorizedHeader.substring("Bearer ".length());
        System.out.println("🔍 Token extraído: " + token.substring(0, Math.min(20, token.length())) + "...");

        Optional<JWTUserData> optUser = tokenConfig.validateToken(token);
        
        if(optUser.isPresent()){
            JWTUserData userData = optUser.get();
            
            // Usando o método getAuthorities() que foi definido na classe User.java
            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(
                    userData, 
                    null, 
                    userData.getAuthorities() // Isso ja está ok!
                );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Usuário autenticado: " + userData.email() + " com role: " + userData.role());
        } else {
            System.out.println("Token inválido ou expirado");
        }
    } else {
        System.out.println("Header Authorization ausente ou mal formatado: " + authorizedHeader);
    }
    
    filterChain.doFilter(request, response);
}




}
