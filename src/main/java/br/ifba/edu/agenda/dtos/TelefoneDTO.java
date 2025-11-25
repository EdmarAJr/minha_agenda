package br.ifba.edu.agenda.dtos;

import br.ifba.edu.agenda.entities.CategoriaTelefone;
import br.ifba.edu.agenda.entities.Telefone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TelefoneDTO(
        @NotBlank(message = "O número do telefone é obrigatório.")
        String numero,
        @Schema(description = "Categoria do telefone", example = "CELULAR")
        @NotNull(message = "A categoria do telefone é obrigatória.")
        CategoriaTelefone categoria,
        boolean principal
    ) {
    public TelefoneDTO(Telefone telefone){
        this(telefone.getNumero(), telefone.getCategoria(), false);
    }
}
