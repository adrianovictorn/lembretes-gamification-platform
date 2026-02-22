package io.github.adrianovictorn.lembrete.service;

import org.springframework.stereotype.Service;

import io.github.adrianovictorn.lembrete.entity.GameRulesConfig;
import io.github.adrianovictorn.lembrete.entity.Level;
import io.github.adrianovictorn.lembrete.repository.GameRulesConfigRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GameRulesConfigService {
    

    private final GameRulesConfigRepository repository;

    public GameRulesConfigService(GameRulesConfigRepository repository) {
        this.repository = repository;
    }

   public GameRulesConfig getConfig(){
    GameRulesConfig config = repository.findTopByOrderByIdAsc().orElseThrow(() -> new EntityNotFoundException("Não foi encontrado ! Precisa verificar se a Seed foi criada !"));
    return config;
   }

   public Level getInitialLevel(){
    return getConfig().getInicialLevel();
   }

   public int getXpMax(){
    return getConfig().getXpMax();
   }

   public boolean isStreakAtual(){
    return getConfig().isStreakAtiva();
   }
}
