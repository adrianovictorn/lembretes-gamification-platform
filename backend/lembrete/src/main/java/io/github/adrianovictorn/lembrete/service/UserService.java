package io.github.adrianovictorn.lembrete.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.adrianovictorn.lembrete.dto.user.UserCreateDTO;
import io.github.adrianovictorn.lembrete.dto.user.UserListDTO;
import io.github.adrianovictorn.lembrete.dto.user.UserViewDTO;
import io.github.adrianovictorn.lembrete.entity.GameProfile;
import io.github.adrianovictorn.lembrete.entity.User;
import io.github.adrianovictorn.lembrete.mapper.UserMapper;
import io.github.adrianovictorn.lembrete.repository.GameProfileRepository;
import io.github.adrianovictorn.lembrete.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final GameProfileRepository gameProfileRepository;
    private final GameRulesConfigService gameRulesConfigService;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, GameProfileRepository gameProfileRepository, GameRulesConfigService gameRulesConfigService) {
        this.userRepository = userRepository;
        this.gameProfileRepository = gameProfileRepository;
        this.userMapper = userMapper;
        this.encoder = passwordEncoder;
        this.gameRulesConfigService = gameRulesConfigService;
    }

    @jakarta.transaction.Transactional
    public UserViewDTO cadastrarUsuario (UserCreateDTO dto){
        User novoUsuario = userMapper.toEntity(dto);
        novoUsuario.setSenha(encoder.encode(dto.senha()));
        User salvo = userRepository.save(novoUsuario);
        
        GameProfile novoGameProfile = new GameProfile();
        novoGameProfile.setUser(novoUsuario);
        novoGameProfile.setLevel(gameRulesConfigService.getInitialLevel());
        novoGameProfile.setMelhorStreak(0);
        novoGameProfile.setMoedas(0);
        novoGameProfile.setUltimoDiaAtivo(LocalDate.now());
        novoGameProfile.setTotalConcluido(0);
        novoGameProfile.setXp(0);
        novoGameProfile.setAtualizadoEm(null);
        
        gameProfileRepository.save(novoGameProfile);
        return userMapper.toViewDTO(salvo);
    }

    

    public List<UserListDTO> listarUsuarios(){
        List<User> usuarios = userRepository.findAll();        
        return userMapper.toList(usuarios);
    }

    
}
