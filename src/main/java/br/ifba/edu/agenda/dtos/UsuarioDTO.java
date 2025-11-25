package br.ifba.edu.agenda.dtos;

import br.ifba.edu.agenda.entities.Usuario;

public record UsuarioDTO(Long id, String username) {
    public UsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getUsername());
    }
}