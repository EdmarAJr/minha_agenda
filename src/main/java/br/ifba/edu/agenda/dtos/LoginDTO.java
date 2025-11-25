package br.ifba.edu.agenda.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//O login é um endpoint que precisa de validação para impedir que sejam vazios
public record LoginDTO(
        @NotBlank(message = "username não pode ser nulo")
        @Email(message = "O email deve ser válido.")
        @Schema(description = "Nome do usuário", example = "joaosilva@email.com")
        String username,
        @Size(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres.")
        @NotBlank(message = "password não pode ser nulo")
        @Schema(description = "Password do usuário", example = "senha123")
        String password
    ) {
}
