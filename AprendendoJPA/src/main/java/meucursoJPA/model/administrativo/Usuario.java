package meucursoJPA.model.administrativo;
import javax.persistence.Entity;
import javax.persistence.Table;

import meucursoJPA.model.GenericDomain;
import meucursoJPA.model.secretaria.Grupo;
@Entity
@Table(name="usuario")
public class Usuario extends GenericDomain{
	private static final long serialVersionUID = 1L;
	
	
	private String login;
	private String senha;
	private String cpf;
	private Grupo grupo;
	
	
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	
}
