package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import org.omnifaces.util.Faces;

import daos.FuncaoUsuarioDao;
import daos.UsuarioDao;
import entidades.FuncaoUsuario;
import entidades.Membro;
import entidades.Usuario;
import utils.Utils;

@ManagedBean(name="autenticaoBean")
@SessionScoped
public class AutenticacaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Usuario usuarioLogado;
	private List<FuncaoUsuario>funcoes;
	private List<Usuario>usuarios;


	private UsuarioDao usuarioDao=new UsuarioDao();
	private FuncaoUsuarioDao funcaoDao=new FuncaoUsuarioDao();
	@PostConstruct
	public void iniciar(){

		usuario=new Usuario();
		usuarios=usuarioDao.listar();
		funcoes=funcaoDao.listar();

		//usuario.setMembro(new Membro());
	}
	//metodo para acessar o sistema 2
	public void autenticar(){
		try{
			Faces.redirect("./public/principal.xhtml");
		}catch(IOException e){
			e.printStackTrace();
		}
	}




	//metodo para acessar o sistema 1
	public void acessarSistema(){
		try{
			usuarioLogado = usuarioDao.autenticarUsuario(usuario.getCpf(),usuario.getSenha());
			if(usuarioLogado==null){
				Utils.mostrarMensagemJsfErro("dados incorretos");
			}else{
				Utils.mostrarMensagemJsfSucesso("usuario logado com sucesso");

				autenticar();

			}

		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao tentar acessar o sistema");
			e.printStackTrace();
		}
	}
	//metodo que verifica se o usuario logado tem permiss�es para acessar paginas privadas do sistema

	/*public boolean temPermissoes(List<String> permissoes){
		for(String permissao : permissoes){
			if(usuarioLogado.getTipoUsuario().equals(permissao)){
				return true;
			}
		}

		return false;
	}*/


	/*public boolean temPermissao(String permissao){
		if(usuarioLogado.getFuncaoUsuario().getDescricao().contains(permissao)){
			return true;
		}
		return false;
	}*/

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	public List<FuncaoUsuario> getFuncoes() {
		return funcoes;
	}
	public void setFuncoes(List<FuncaoUsuario> funcoes) {
		this.funcoes = funcoes;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}




}
