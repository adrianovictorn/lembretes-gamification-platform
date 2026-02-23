package io.github.adrianovictorn.lembrete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.adrianovictorn.lembrete.entity.Level;

public interface LevelRepository extends JpaRepository<Level, Long>{
    

        Optional<Level> findTopByAtivoTrueAndXpMinimoLessThanEqualOrderByXpMinimoDesc(long xp);

}
