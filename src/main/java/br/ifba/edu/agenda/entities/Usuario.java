package br.ifba.edu.agenda.entities;

import br.ifba.edu.agenda.dtos.LoginDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "usuarios")
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String username;
	private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="usuarios_roles",
            joinColumns = @JoinColumn(name="usuarios_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private List<Role> roles= new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contato> contatos = new ArrayList<>();

	public Usuario() { super();}
	
	public Usuario(Long id, String username, String password) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
	}
	
	public Usuario(LoginDTO usuario) {
		super();
		this.username = usuario.username();
		this.password = usuario.password();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

    @Override
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    public List<Contato> getContatos() {
        return contatos;
    }
    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
