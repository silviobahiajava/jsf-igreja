package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import org.omnifaces.util.Faces;

import br.com.testejsf.utils.Utils;
import daos.FuncaoUsuarioDao;
import daos.UsuarioDao;
import entidades.FuncaoUsuario;
import entidades.Membro;
import entidades.Usuario;

@ManagedBean(name="autenticationBean")
@SessionScoped
public class AutenticacaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Membro());
	}

	public void autenticar() {
		try {
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			//Messages.addGlobalError(erro.getMessage());
		}
	}



}
