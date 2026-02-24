package io.github.adrianovictorn.lembrete.service;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.adrianovictorn.lembrete.dto.integration.N8nParseRequest;
import io.github.adrianovictorn.lembrete.dto.integration.N8nParseResponse;


@Service
public class N8nClientService {

    private final WebClient webClient;

    public N8nClientService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://191.101.78.51:32768")
                .build();
    }

    public N8nParseResponse parse(N8nParseRequest req) {
        return webClient.post()
                .uri("/webhook/lembrete-parse")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .retrieve()
                .bodyToMono(N8nParseResponse.class)
                .block(java.time.Duration.ofSeconds(20)); 
    }
}

