package io.github.adrianovictorn.lembrete.entity;


import java.util.List;

import io.github.adrianovictorn.lembrete.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;
    
    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    @Column(name = "username", length = 150, nullable = false, unique = true)
    private String username;

    @Column(name = "telefone", length = 20, nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "user")
    private List<Lembrete> lembretes;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false, length = 100)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private GameProfile gameProfile;

    @Column(name = "user_sequence")
    private long userSequence;

}