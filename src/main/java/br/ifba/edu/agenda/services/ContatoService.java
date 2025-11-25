package br.ifba.edu.agenda.services;

import br.ifba.edu.agenda.dtos.ContatoDTO;
import br.ifba.edu.agenda.entities.Contato;
import br.ifba.edu.agenda.entities.Telefone;
import br.ifba.edu.agenda.entities.Usuario;
import br.ifba.edu.agenda.repositories.ContatoRepository;
import br.ifba.edu.agenda.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContatoService {

    private ContatoRepository contatoRepository;
    private UsuarioRepository usuarioRepository;

    public ContatoService(ContatoRepository contatoRepository, UsuarioRepository usuarioRepository){
        this.contatoRepository = contatoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ContatoDTO cadastrarContato(ContatoDTO contatoDTO, String username){
        var usuario = (Usuario) usuarioRepository.findByUsername(username);
        var novocontato = new Contato(contatoDTO, usuario);
        if(contatoDTO.telefones() != null){
            contatoDTO.telefones().forEach(t -> {
                novocontato.getTelefones().add(new Telefone(t,novocontato));
            });
        }
        contatoRepository.save(novocontato);
        return new ContatoDTO(novocontato);
    }

    public List<ContatoDTO> listarContatoPorUsuario(String username){
        var usuario = (Usuario)usuarioRepository.findByUsername(username);
        var contatos = usuario.getContatos();
        return contatos.stream().map(ContatoDTO::new).toList();
    }

    public ContatoDTO atualizarContato(Long id, ContatoDTO contatoDTO, String username) {
        var usuario = (Usuario) contatoRepository.findByUsuarioUsername(username);
        var contato = contatoRepository.getReferenceById(id);
        if (!contato.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("O contato não pertence ao usuário autenticado");
        }
        contato.setNome(contatoDTO.nome());
        contato.setSobrenome(contatoDTO.sobrenome());
        contato.setEmail(contatoDTO.email());
        contatoRepository.save(contato);
        return new ContatoDTO(contato);
    }

    public ContatoDTO buscarContatoPorId(Long id){
        var contato = contatoRepository.getReferenceById(id);
        return new ContatoDTO(contato);
    }

    public ContatoDTO apagarContatoPorId(Long id){
        var contato = contatoRepository.getReferenceById(id);
        contatoRepository.delete(contato);
        return new ContatoDTO(contato);
    }
}
