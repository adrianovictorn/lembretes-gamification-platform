package io.github.adrianovictorn.lembrete.dto.user;

public record UserSimpleViewDTO(
    Long id,
    String nome,
    String username
) {
}