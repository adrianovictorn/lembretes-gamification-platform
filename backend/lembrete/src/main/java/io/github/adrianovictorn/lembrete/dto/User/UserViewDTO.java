package io.github.adrianovictorn.lembrete.dto.user;

import io.github.adrianovictorn.lembrete.enums.Role;

public record UserViewDTO(
    Long id,
    String nome,
    String senha,
    String telefone,
    String username,
    Role role,
    long userSequence

) {
    
}
