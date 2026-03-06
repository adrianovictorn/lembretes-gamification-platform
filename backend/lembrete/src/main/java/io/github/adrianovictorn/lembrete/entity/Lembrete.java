package io.github.adrianovictorn.lembrete.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import io.github.adrianovictorn.lembrete.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lembrete")
@Data
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 100,nullable = false)
    private String titulo;

    @Column(name = "mensagem", length = 255, nullable = true)
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 100)
    private Status status;

    @Column(name = "data_lembrete")
    private LocalDateTime dataLembrete;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "xp_reward", nullable = false) 
    private int xpReward;

    @Column(name = "coins_reward", nullable = true)
    private int coinsReward;

    @Column(name = "user_number")
    private long userNumber;

    @Column(name = "concluido_em", nullable = true)
    private LocalDateTime concluidoEm;
}
