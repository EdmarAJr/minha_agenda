package br.ifba.edu.agenda.repositories;

import br.ifba.edu.agenda.entities.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
