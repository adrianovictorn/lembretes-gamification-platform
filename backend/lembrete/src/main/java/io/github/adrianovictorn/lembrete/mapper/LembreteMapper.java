package io.github.adrianovictorn.lembrete.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteCreateDTO;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteListDTO;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteUpdateDTO;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteViewDTO;
import io.github.adrianovictorn.lembrete.entity.Lembrete;

@Mapper(componentModel = "spring")
public interface LembreteMapper {

    @Mapping(target = "id", ignore = true)    
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "status", ignore =  true)
    @Mapping(target = "user", ignore =  true)
    @Mapping(target = "concluidoEm", ignore = true)
    Lembrete toEntity(LembreteCreateDTO lembrete);
    LembreteViewDTO toViewDTO (Lembrete lembrete);
    List<LembreteListDTO> toListDTO(List<Lembrete> lista);

    @Mapping(target = "id", ignore = true)    
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "user", ignore =  true)
    @Mapping(target = "xpReward", ignore = true)
    @Mapping(target = "coinsReward", ignore = true)
    @Mapping(target = "concluidoEm", ignore = true)
    void updateDTO(@MappingTarget Lembrete lembrete, LembreteUpdateDTO dto);


}