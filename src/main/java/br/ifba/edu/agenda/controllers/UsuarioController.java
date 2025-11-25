package br.ifba.edu.agenda.controllers;

import br.ifba.edu.agenda.dtos.LoginDTO;
import br.ifba.edu.agenda.dtos.TokenDTO;
import br.ifba.edu.agenda.dtos.UsuarioDTO;
import br.ifba.edu.agenda.entities.Usuario;
import br.ifba.edu.agenda.services.JWTokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.ifba.edu.agenda.services.UsuarioService;
import java.util.List;

@RestController()
@RequestMapping("/auth")
public class UsuarioController {

    private AuthenticationManager manager;
    private UsuarioService usuarioService;
    private JWTokenService tokenService;

    public UsuarioController(AuthenticationManager manager, UsuarioService usuarioService, JWTokenService tokenService) {
        this.manager = manager;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO login) {
        var usuariovalido = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        var authentication = manager.authenticate(usuariovalido);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar (@RequestBody @Valid LoginDTO dados, UriComponentsBuilder uriBuilder){
        var novousuario = usuarioService.cadastrarUsuario(dados);
        var uri = uriBuilder.path("/auth/{id}").buildAndExpand(novousuario.id()).toUri();
        return ResponseEntity.created(uri).body(novousuario);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<UsuarioDTO> apagar (@PathVariable Long id){
        var usuarioapagado = usuarioService.apagarUsuario(id);
        return ResponseEntity.ok(usuarioapagado);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDTO> atualizar (@PathVariable Long id, @RequestBody @Valid LoginDTO dados){
        var usuarioatualizado = usuarioService.atualizarUsuario(id, dados);
        return ResponseEntity.ok(usuarioatualizado);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }
}