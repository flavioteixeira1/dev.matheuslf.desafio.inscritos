package dev.matheuslf.desafio.inscritos.dto.Request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty (message = "email/username não pode ser vazio") String email, 
                           @NotEmpty (message= "password não pode ser vazio")String password) {

}
