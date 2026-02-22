package io.github.adrianovictorn.lembrete.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.adrianovictorn.lembrete.dto.level.LevelCreateDTO;
import io.github.adrianovictorn.lembrete.dto.level.LevelListDTO;
import io.github.adrianovictorn.lembrete.dto.level.LevelUpdateDTO;
import io.github.adrianovictorn.lembrete.dto.level.LevelViewDTO;
import io.github.adrianovictorn.lembrete.entity.Level;
import io.github.adrianovictorn.lembrete.mapper.LevelMapper;
import io.github.adrianovictorn.lembrete.repository.LevelRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LevelService {
    
    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;

    public LevelService(LevelRepository levelRepository, LevelMapper levelMapper) {
        this.levelRepository = levelRepository;
        this.levelMapper = levelMapper;
    }

    public LevelViewDTO createLevel(LevelCreateDTO dto){
        Level newLevel = levelMapper.toEntity(dto);
        Level save = levelRepository.save(newLevel);
        return levelMapper.toViewDTO(save);
    }

    public List<LevelListDTO> listLevel(){
        List<Level> listEntity = levelRepository.findAll();
        return levelMapper.toListDTO(listEntity);
    }

    public LevelViewDTO updateLevel(Long id, LevelUpdateDTO dto){
        Level exitsLevel = levelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Level not found !"));
        levelMapper.update(exitsLevel, dto);
        Level uptaded = levelRepository.save(exitsLevel);
        return levelMapper.toViewDTO(uptaded);
    }
    
    public void deleteLevel(Long id){
        Level existsLevel = buscarPorId(id);
        levelRepository.delete(existsLevel);
    }

    private Level buscarPorId(Long id){
        Level exitsLevel = levelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Level not found !"));
        return exitsLevel;
    } 
}
