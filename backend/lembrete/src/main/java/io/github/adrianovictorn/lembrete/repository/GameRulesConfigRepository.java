package io.github.adrianovictorn.lembrete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import io.github.adrianovictorn.lembrete.entity.GameRulesConfig;

public interface GameRulesConfigRepository extends JpaRepository<GameRulesConfig, Long> {
    
    @EntityGraph(attributePaths = "inicialLevel")
    Optional<GameRulesConfig> findTopByOrderByIdAsc();
}
