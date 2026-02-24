package io.github.adrianovictorn.lembrete.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.adrianovictorn.lembrete.dto.integration.N8nParseRequest;
import io.github.adrianovictorn.lembrete.dto.integration.N8nParseResponse;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteCreateDTO;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteListDTO;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteUpdateDTO;
import io.github.adrianovictorn.lembrete.dto.lembrete.LembreteViewDTO;
import io.github.adrianovictorn.lembrete.entity.GameProfile;
import io.github.adrianovictorn.lembrete.entity.Lembrete;
import io.github.adrianovictorn.lembrete.entity.User;
import io.github.adrianovictorn.lembrete.enums.Status;
import io.github.adrianovictorn.lembrete.mapper.LembreteMapper;
import io.github.adrianovictorn.lembrete.repository.GameProfileRepository;
import io.github.adrianovictorn.lembrete.repository.LembreteRepository;
import io.github.adrianovictorn.lembrete.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LembreteService {
    
    private final LembreteRepository lembreteRepository;
    private final UserRepository userRepository;
    private final GameProfileRepository gameProfileRepository;
    private final LembreteMapper lembreteMapper;
    private final GameProfileService gameProfileService;
    private final N8nClientService n8nClientService;

    public LembreteService(LembreteRepository repository, LembreteMapper mapper, UserRepository userRepository, GameProfileRepository gameProfileRepository, GameProfileService gameProfileService, N8nClientService n8nClientService) {
        this.lembreteRepository = repository;
        this.lembreteMapper = mapper;
        this.userRepository = userRepository;
        this.gameProfileRepository = gameProfileRepository;
        this.gameProfileService = gameProfileService;
        this.n8nClientService = n8nClientService;
    }

    @Transactional
    public LembreteViewDTO cadastrarViaLLM(String username, String text){


        String requestId = java.util.UUID.randomUUID().toString();
        System.out.println("Vai chamar n8n...");
        N8nParseResponse parsed = n8nClientService.parse(
        
            new N8nParseRequest(requestId, username, text, "America/Bahia")
        );
        System.out.println("Voltou do n8n!");

        System.out.println(parsed.lembretes());
        if (parsed == null || parsed.lembretes() == null || parsed.lembretes().titulo() == null) {
                throw new IllegalArgumentException("IA não conseguiu estruturar o lembrete");
            }

        LembreteViewDTO lembrete = cadastrarLembrete(parsed.lembretes(), username);
        return lembrete;
    }

    public LembreteViewDTO cadastrarLembrete(LembreteCreateDTO dto, String username){
        User usuarioExistente = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Usuário não existe ou não encontrado !"));
        Lembrete novoLembrete = lembreteMapper.toEntity(dto);
        novoLembrete.setUser(usuarioExistente);
        novoLembrete.setStatus(Status.PENDENTE);
        Lembrete salvo = lembreteRepository.save(novoLembrete);
        return lembreteMapper.toViewDTO(salvo);
    }

    public List<LembreteListDTO> listarLembretes(){
        List<Lembrete> lembreteList = lembreteRepository.findAll();
        return lembreteMapper.toListDTO(lembreteList);
    }

    public LembreteViewDTO atualizarLembrete(Long id, LembreteUpdateDTO dto){
        Lembrete lembreteExistente = lembreteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lembrete não encontrado !"));
        lembreteMapper.updateDTO(lembreteExistente, dto);
        Lembrete atualizado = lembreteRepository.save(lembreteExistente);
        return lembreteMapper.toViewDTO(atualizado);
    }

    public Page<LembreteViewDTO> buscarPorUsuario(Long id, int page, int size){
        Pageable pagina = PageRequest.of(page, size);
        return lembreteRepository.findByUserId(id, pagina).map(lembreteMapper::toViewDTO);
    }

    public void deletarLembrete(Long id){
        Lembrete lembreExistente = buscarPorId(id);
        lembreteRepository.delete(lembreExistente);
    }

    @Transactional
    public LembreteViewDTO concluirLembrete(Long id){
        Lembrete existente = lembreteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lembrete não encontrado !"));
        
        if (existente.getStatus() == Status.CONCLUIDO) {
            return lembreteMapper.toViewDTO(existente);
        }

        existente.setConcluidoEm(LocalDateTime.now());
        existente.setStatus(Status.CONCLUIDO);
        
        GameProfile profile = gameProfileRepository.findByUserId(existente.getUser().getId()).orElseThrow(() -> new EntityNotFoundException("Usuário não possui um Profile ativo !"));
        long recompensa = existente.getXpReward();
        long moedasRecompensa = existente.getCoinsReward();
        
        profile.setMoedas(profile.getMoedas() + moedasRecompensa);
        profile.setXp(profile.getXp() + recompensa);


        lembreteRepository.save(existente);
        gameProfileRepository.save(profile);

        gameProfileService.verificarAtualizarLevel(existente.getUser().getId());

        return lembreteMapper.toViewDTO(existente);

    }

    private Lembrete buscarPorId(Long id){
        Lembrete lembreteExistente = lembreteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lembrete não encontrado !"));
        return lembreteExistente;
    }

    
    
}
