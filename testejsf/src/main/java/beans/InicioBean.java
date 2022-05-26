package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="inicioBean")
@SessionScoped
public class InicioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email;
	private String senha;
	
	



	//metodo para acessar o sistema 1
	public void mostrar(){
		System.out.println(email);
		System.out.println(senha);
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getSenha() {
		return senha;
	}





	public void setSenha(String senha) {
		this.senha = senha;
	}
	



}
