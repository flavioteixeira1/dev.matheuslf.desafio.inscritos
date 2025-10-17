package dev.matheuslf.desafio.inscritos.SecurityConfig;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.matheuslf.desafio.inscritos.Repository.UserRepository;

@Service
public class AutenticaConfig implements UserDetailsService{

   private final UserRepository usuarioRepository;

    public AutenticaConfig(UserRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return usuarioRepository.findUserByEmail(username).orElseThrow(()-> new  UsernameNotFoundException(username));
    }
    

}
