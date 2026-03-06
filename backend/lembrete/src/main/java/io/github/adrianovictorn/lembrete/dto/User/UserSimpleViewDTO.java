package io.github.adrianovictorn.lembrete.dto.user;

import io.github.adrianovictorn.lembrete.enums.Role;

public record UserSimpleViewDTO(
    Long id,
    String nome,
    String username,
    Role role
) {
}