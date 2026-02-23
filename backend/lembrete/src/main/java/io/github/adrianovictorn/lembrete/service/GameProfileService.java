package io.github.adrianovictorn.lembrete.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.adrianovictorn.lembrete.dto.game_profile.GameProfileViewDTO;
import io.github.adrianovictorn.lembrete.entity.GameProfile;
import io.github.adrianovictorn.lembrete.entity.Level;
import io.github.adrianovictorn.lembrete.mapper.GameProfileMapper;
import io.github.adrianovictorn.lembrete.repository.GameProfileRepository;
import io.github.adrianovictorn.lembrete.repository.LevelRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GameProfileService {
    
    private final GameProfileRepository gameProfileRepository;
    private final LevelRepository levelRepository;
    private final GameRulesConfigService gameRulesConfigService;
    private final GameProfileMapper mapper;
    
    public GameProfileService(GameProfileRepository gameProfileRepository, LevelRepository levelRepository,
            GameRulesConfigService gameRulesConfigService, GameProfileMapper mapper) {
        this.gameProfileRepository = gameProfileRepository;
        this.levelRepository = levelRepository;
        this.gameRulesConfigService = gameRulesConfigService;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public GameProfileViewDTO buscarPorUser(Long id){
        GameProfile gameExistente = gameProfileRepository.findByUserId(id).orElseThrow(() -> new EntityNotFoundException("Usuário não possui um Profile"));
        return mapper.toViewDTO(gameExistente);
    }

    @Transactional
    public void verificarAtualizarLevel(Long id){
        GameProfile profile = gameProfileRepository.findByUserId(id).orElseThrow(() -> new EntityNotFoundException("Usuário não possui um Profile"));

        long xpAtual = profile.getXp();

        Level calculado = levelRepository
        .findTopByAtivoTrueAndXpMinimoLessThanEqualOrderByXpMinimoDesc(xpAtual)
        .orElseGet(gameRulesConfigService::getInitialLevel);


        Level atual = profile.getLevel();
        boolean mudou = (atual == null) || !atual.getId().equals(calculado.getId());

        if (mudou) {
            profile.setLevel(calculado);
            gameProfileRepository.save(profile);
        }
    }

    

    
}
