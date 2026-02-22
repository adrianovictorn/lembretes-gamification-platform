package io.github.adrianovictorn.lembrete.dto.user;


import io.github.adrianovictorn.lembrete.enums.Role;

public record UserCreateDTO(
    String nome,
    String senha,
    String username,
    String telefone,
    Role role
    
) {
    
}
