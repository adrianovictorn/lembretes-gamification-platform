package io.github.adrianovictorn.lembrete.dto.integration;

public record N8nParseRequest(
    String requestId,
    String username,
    String text,
    String timezone
) {
    
}
