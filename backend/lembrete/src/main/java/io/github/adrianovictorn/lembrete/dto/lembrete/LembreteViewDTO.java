package io.github.adrianovictorn.lembrete.dto.lembrete;

import java.time.LocalDateTime;

import io.github.adrianovictorn.lembrete.enums.Status;

public record LembreteViewDTO(
    Long id,
    String titulo,
    String mensagem,
    Status status,
    LocalDateTime criadoEm,
    int coinsReward,
    int xpReward,
    LocalDateTime dataLembrete
) {

}
