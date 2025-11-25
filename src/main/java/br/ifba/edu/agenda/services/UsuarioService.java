package br.ifba.edu.agenda.services;

import br.ifba.edu.agenda.dtos.LoginDTO;
import br.ifba.edu.agenda.dtos.UsuarioDTO;
import br.ifba.edu.agenda.entities.Usuario;
import br.ifba.edu.agenda.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioDTO cadastrarUsuario (LoginDTO loginDTO){
        var novousuario = new Usuario(loginDTO);
        novousuario.setPassword(passwordEncoder.encode(loginDTO.password()));
        usuarioRepository.save(novousuario);
        return new UsuarioDTO(novousuario);
    }

    public UsuarioDTO apagarUsuario (Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.deleteById(id);
        return new UsuarioDTO(usuario);
    }

    public UsuarioDTO atualizarUsuario(Long id, LoginDTO loginDTO){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.setUsername(loginDTO.username());
        usuario.setPassword(loginDTO.password());
        return new UsuarioDTO(usuario);
    }

    public List<UsuarioDTO> listarUsuarios(){
        return usuarioRepository.findAll().stream().map(UsuarioDTO::new).toList();
    }

}
