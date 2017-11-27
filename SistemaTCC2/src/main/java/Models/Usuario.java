package Models;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "TipoUsuario", length = 5, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("user")
public class Usuario {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
    private String nome;
    private String matricula;
    private String cpf;
    private String senha;
	
    public Usuario() {
    	super();
    }
    
    public Usuario(Integer id) {
    	super();
    	this.id = id;
    }
    
    public Usuario(String nome, String matricula, String cpf, String senha) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.cpf = cpf;
		this.senha = senha;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", cpf=" + cpf + ", senha=" + senha
				+ "]";
	}
    
    
}
