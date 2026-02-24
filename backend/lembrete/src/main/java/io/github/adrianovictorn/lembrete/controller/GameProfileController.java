package io.github.adrianovictorn.lembrete.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.adrianovictorn.lembrete.dto.game_profile.GameProfileViewDTO;
import io.github.adrianovictorn.lembrete.service.GameProfileService;

@RestController
@RequestMapping("/api/game-profile")
public class GameProfileController {
    
    private final GameProfileService gameProfileService;

    public GameProfileController(GameProfileService gameProfileService) {
        this.gameProfileService = gameProfileService;
    }

    @GetMapping("/buscar/autenticado")
    public ResponseEntity<GameProfileViewDTO> buscarGameProfile(@AuthenticationPrincipal Jwt jwt){
        String username = jwt.getClaimAsString("sub");
        return ResponseEntity.ok(gameProfileService.buscarPorUser(username));
    }
}
