package br.ifba.edu.agenda.entities;

import br.ifba.edu.agenda.dtos.ContatoDTO;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "contatos")
public class Contato{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    @Column(unique = true)
    private String email;
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones= new ArrayList<>();

    public Contato(){
        super();
    }

    public Contato(ContatoDTO contatoDTO, UserDetails usuario){
        super();
        this.nome = contatoDTO.nome();
        this.email = contatoDTO.email();
        this.sobrenome = contatoDTO.sobrenome();
        this.email = contatoDTO.email();
        this.usuario = (Usuario) usuario;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getSobrenome(){
        return sobrenome;
    }
    public void setSobrenome(String sobrenome){
        this.sobrenome = sobrenome;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    public List<Telefone> getTelefones(){
        return telefones;
    }
    public void setTelefones(List<Telefone> telefones){
        this.telefones = telefones;
    }
}
