package br.ifba.edu.agenda.services;

import br.ifba.edu.agenda.dtos.ContatoDTO;
import br.ifba.edu.agenda.dtos.ListTelefoneDTO;
import br.ifba.edu.agenda.dtos.TelefoneDTO;
import br.ifba.edu.agenda.entities.Telefone;
import br.ifba.edu.agenda.repositories.ContatoRepository;
import br.ifba.edu.agenda.repositories.TelefoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefoneService {

    private TelefoneRepository telefoneRepository;
    private ContatoRepository contatoRepository;

    public TelefoneService(TelefoneRepository telefoneRepository, ContatoRepository contatoRepository){
        this.telefoneRepository = telefoneRepository;
        this.contatoRepository = contatoRepository;
    }

    public ContatoDTO cadastrarTelefones(Long contatoId, ListTelefoneDTO listaTelefonesDTO){
        var contato = contatoRepository.getReferenceById(contatoId);
        if(!listaTelefonesDTO.numeros().isEmpty()){
            this.telefoneRepository.saveAll(listaTelefonesDTO.numeros().stream()
                    .map(telefoneDTO -> new Telefone(telefoneDTO, contato)).toList());
        }
        return new ContatoDTO(contato);
    }

    public ContatoDTO deletarTelefones(Long contatoId, Long telefoneId){
        var telefone = this.telefoneRepository.getReferenceById(telefoneId);
        if(telefone.getContato().getId().equals(contatoId)){
            this.telefoneRepository.delete(telefone);
        } else {
            throw new RuntimeException("O telefone não pertence ao contato.");
        }
        var contato = this.contatoRepository.getReferenceById(contatoId);
        return new ContatoDTO(contato);
    }

    public ContatoDTO atualizarTelefones(Long contatoId, Long telefoneId, TelefoneDTO novoNumero){
        var telefone = this.telefoneRepository.getReferenceById(telefoneId);
        if(telefone.getContato().getId().equals(contatoId)){
            telefone.setNumero(novoNumero.numero());
            telefone.setCategoria(novoNumero.categoria());
            telefone.setPrincipal(novoNumero.principal());
            this.telefoneRepository.save(telefone);
        } else {
            throw new RuntimeException("O telefone não pertence ao contato.");
        }
        var contato = this.contatoRepository.getReferenceById(contatoId);
        return new ContatoDTO(contato);
    }
}
