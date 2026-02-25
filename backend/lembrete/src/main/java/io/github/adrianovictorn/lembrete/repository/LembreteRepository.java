package io.github.adrianovictorn.lembrete.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import io.github.adrianovictorn.lembrete.entity.Lembrete;

public interface LembreteRepository extends JpaRepository<Lembrete, Long>{
    
    Page<Lembrete> findByUserUsername(String username, Pageable pageable);

}
