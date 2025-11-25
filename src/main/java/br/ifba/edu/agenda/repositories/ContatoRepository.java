package br.ifba.edu.agenda.repositories;

import br.ifba.edu.agenda.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByUsuarioUsername(String username);
}
