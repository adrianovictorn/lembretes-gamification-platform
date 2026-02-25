package io.github.adrianovictorn.lembrete.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.adrianovictorn.lembrete.dto.integration.IaLembreteRequest;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteCreateDTO;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteListDTO;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteUpdateDTO;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteViewDTO;
import io.github.adrianovictorn.lembrete.service.LembreteService;

@RestController
@RequestMapping("/api/lembrete")
public class LembreteController {
    

    private final LembreteService lembreteService;

    public LembreteController(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<LembreteViewDTO> cadastrarLembrete (@RequestBody LembreteCreateDTO dto, @AuthenticationPrincipal Jwt jwt){
        String username = jwt.getClaimAsString("sub");
        return ResponseEntity.ok(lembreteService.cadastrarLembrete(dto, username));
    }

    @PostMapping("/ai")
    public ResponseEntity<LembreteViewDTO> cadastrarComIa (@RequestBody IaLembreteRequest dto, @AuthenticationPrincipal Jwt jwt){
        String username = jwt.getClaimAsString("sub");
        return ResponseEntity.ok(lembreteService.cadastrarViaLLM(username, dto.text()));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LembreteListDTO>> listarLembretes(){
        return ResponseEntity.ok(lembreteService.listarLembretes());
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<LembreteViewDTO>> buscarPorUsuario(
        @RequestParam(defaultValue = "0", name = "page", required = true) int page,
        @RequestParam(defaultValue = "10", name = "size", required = false) int size,
        @AuthenticationPrincipal Jwt jwt
        ){
            String username = jwt.getClaimAsString("sub");
            return ResponseEntity.ok(lembreteService.buscarPorUsuario(username, page, size));
    }

    @PatchMapping("/concluido/{id}")
    public ResponseEntity<HttpStatus> concluirTarefa(@PathVariable Long id){
       lembreteService.concluirLembrete(id);
       return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<LembreteViewDTO> atualizarLembrete(@PathVariable Long id, @RequestBody LembreteUpdateDTO dto){
        return ResponseEntity.ok(lembreteService.atualizarLembrete(id, dto));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Void> deletarLembrete(@PathVariable Long id){
        lembreteService.deletarLembrete(id);
        return ResponseEntity.noContent().build();
    }
}
