package br.ifba.edu.agenda.entities;

import br.ifba.edu.agenda.dtos.TelefoneDTO;
import jakarta.persistence.*;

@Entity (name = "telefones")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaTelefone categoria;

    @Column(name = "telefone_principal", nullable = false)
    private Boolean principal = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contato_id", nullable = false)
    private Contato contato;

    public Telefone(){
        super();
    }
    public void TelefoneDTO (Telefone telefone){
        telefone.getNumero();
        telefone.getCategoria();
    }

    public Telefone(TelefoneDTO telefoneDTO, Contato contato){
        super();
        this.numero = telefoneDTO.numero();
        this.categoria = telefoneDTO.categoria();
        this.principal = telefoneDTO.principal();
        this.contato = contato;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNumero(){
        return numero;
    }
    public void setNumero(String numero){
        this.numero = numero;
    }
    public CategoriaTelefone getCategoria(){
        return categoria;
    }
    public void setCategoria(CategoriaTelefone categoria){
        this.categoria = categoria;
    }
    public Boolean getPrincipal(){
        return principal;
    }
    public void setPrincipal(Boolean principal){
        this.principal = principal;
    }
    public Contato getContato(){
        return contato;
    }
    public void setContato(Contato contato){
        this.contato = contato;
    }
}
