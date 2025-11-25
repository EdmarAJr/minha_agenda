package br.ifba.edu.agenda.controllers;

import br.ifba.edu.agenda.dtos.ContatoDTO;
import br.ifba.edu.agenda.dtos.ListTelefoneDTO;
import br.ifba.edu.agenda.dtos.TelefoneDTO;
import br.ifba.edu.agenda.services.ContatoService;
import br.ifba.edu.agenda.services.TelefoneService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private ContatoService contatoService;
    private TelefoneService telefoneService;

    public ContatoController(ContatoService contatoService, TelefoneService telefoneService){
        this.contatoService = contatoService;
        this.telefoneService = telefoneService;
    }

    @PostMapping
    public ResponseEntity<ContatoDTO> cadastrarTelefones(@RequestBody @Valid ContatoDTO contatoDTO){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var novoContato = contatoService.cadastrarContato(contatoDTO, username);
        return ResponseEntity.status(201).body(novoContato);
    }

    @GetMapping
    public ResponseEntity<List<ContatoDTO>> listarContatos (){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var contatos = contatoService.listarContatoPorUsuario(username);
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> listarContatoPorId(@PathVariable @Valid Long id){
        var contato = contatoService.buscarContatoPorId(id);
        return ResponseEntity.ok(contato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContatoDTO> apagarContato(@PathVariable("id") @Valid Long contatoId){
        var contatoapagado = contatoService.apagarContatoPorId(contatoId);
        return ResponseEntity.ok(contatoapagado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoDTO> atualizarContato(@PathVariable("id") @Valid Long contatoId,  @RequestBody @Valid ContatoDTO contatoDTO){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var contatoatualizado = contatoService.atualizarContato(contatoId, contatoDTO, username);
        return ResponseEntity.ok(contatoatualizado);
    }

    @PostMapping("/{contatoId}/telefones/{telefoneId}")
    public ResponseEntity<ContatoDTO> atualizarTelefonesDeContato(@PathVariable @Valid Long contatoId,
                                                                  @PathVariable @Valid Long telefoneId
                                                                  ,@RequestBody @Valid TelefoneDTO novoNumero){
        var contatoNumeroAtualizado = telefoneService.atualizarTelefones(contatoId, telefoneId, novoNumero);
        return ResponseEntity.ok(contatoNumeroAtualizado);
    }


    @DeleteMapping("/{contatoId}/telefones/{telefoneId}")
    public ResponseEntity<ContatoDTO> apagarTelefonesDeContato(@PathVariable @Valid Long contatoId,
                                                                  @PathVariable @Valid Long telefoneId){
        var contatoNumeroApagado = telefoneService.deletarTelefones(contatoId, telefoneId);
        return ResponseEntity.ok(contatoNumeroApagado);
    }
}
