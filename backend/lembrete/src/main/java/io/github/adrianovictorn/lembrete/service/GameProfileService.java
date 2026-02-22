package io.github.adrianovictorn.lembrete.service;

import org.springframework.stereotype.Service;

import io.github.adrianovictorn.lembrete.mapper.GameProfileMapper;
import io.github.adrianovictorn.lembrete.repository.GameProfileRepository;
import io.github.adrianovictorn.lembrete.repository.LevelRepository;
import io.github.adrianovictorn.lembrete.repository.UserRepository;

@Service
public class GameProfileService {
    
    private final GameProfileRepository gameProfileRepository;
    private final UserRepository userRepository;
    private final LevelRepository levelRepository;
    private final GameProfileMapper gameProfileMapper;
    
    public GameProfileService(GameProfileRepository gameProfileRepository, UserRepository userRepository,
            LevelRepository levelRepository, GameProfileMapper gameProfileMapper) {
        this.gameProfileRepository = gameProfileRepository;
        this.userRepository = userRepository;
        this.levelRepository = levelRepository;
        this.gameProfileMapper = gameProfileMapper;
    }
}
