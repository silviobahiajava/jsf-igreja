package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="origemBean")
@ViewScoped
public class OrigemBean {
	private String nome;
//	public String salvar(){
//		return "destino?faces-redirect=true?namekey="+nome;
//	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
