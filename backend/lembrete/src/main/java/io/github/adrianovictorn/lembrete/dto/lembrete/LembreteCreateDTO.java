package io.github.adrianovictorn.lembrete.dto.lembrete;

import java.time.LocalDate;


public record LembreteCreateDTO(
    String titulo,
    String mensagem,
    LocalDate dataLembrete,
    Long userId
) {
    
}
