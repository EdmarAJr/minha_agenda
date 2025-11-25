package br.ifba.edu.agenda.dtos;

import br.ifba.edu.agenda.entities.Contato;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public record ContatoDTO(
        Long id,
        @NotBlank
        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
        String nome,
        @NotBlank
        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
        String sobrenome,
        @NotBlank
        @Email(message = "O email deve ser válido.")
        String email,
        //@NotBlank
        @Valid
        List<TelefoneDTO> telefones
    ) {

    public ContatoDTO(Contato contato) {
        this(contato.getId(), contato.getNome(), contato.getSobrenome(), contato.getEmail(),
                contato.getTelefones().stream().map(TelefoneDTO::new).toList());
    }
}
