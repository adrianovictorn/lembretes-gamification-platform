package io.github.adrianovictorn.lembrete.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "game_rules_config")
@Data
public class GameRulesConfig {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "xp_max", nullable = false)
    private int xpMax;

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level inicialLevel;

    @Column(name = "streak_ativa", nullable = false)
    private boolean streakAtiva;   
}
