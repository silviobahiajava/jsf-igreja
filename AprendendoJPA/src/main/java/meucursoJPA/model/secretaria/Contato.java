package meucursoJPA.model.secretaria;
import javax.persistence.*;

import meucursoJPA.model.GenericDomain;
@Entity
@Table(name="contato")

public class Contato extends GenericDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String telefoneFixo;
	private String celular;
	private String email;
	private String skype;
	private String facebook;
	
	public String getTelefoneFixo() {
		return telefoneFixo;
	}
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	
}
