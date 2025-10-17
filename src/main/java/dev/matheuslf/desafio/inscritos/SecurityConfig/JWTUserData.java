package dev.matheuslf.desafio.inscritos.SecurityConfig;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Builder;

@Builder
public record JWTUserData (Long userId, String email, String role) {
     public Collection<? extends GrantedAuthority> getAuthorities() {
       
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role));
    }
}
