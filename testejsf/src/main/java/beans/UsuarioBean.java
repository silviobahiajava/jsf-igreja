package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.MembroDao;
import daos.UsuarioDao;
import entidades.FuncaoUsuario;
import entidades.Membro;
import entidades.Usuario;

@ManagedBean(name="usuarioBean")
@ViewScoped
public class UsuarioBean {
	private List<Usuario>usuarios;
	private Usuario usuario;
	private List<Membro>membros;//chave estrangeira
	private MembroDao membroDao=new MembroDao();
	private FuncaoUsuario funcao=new FuncaoUsuario();

	
	private UsuarioDao usuarioDao=new UsuarioDao();
	@PostConstruct
	public void listar() {
		try {
			usuarios=usuarioDao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	public void novo(){
		usuario=new Usuario();
		membros=membroDao.listar();
		funcao=new FuncaoUsuario();
	}
	
	
	
	public void salvar() {
		try {
			funcao.setDescricao(funcao.getDescricao());
			usuario.setFuncaoUsuario(funcao);
			usuarioDao.merge(usuario);
			//membros=membroDao.listar();
			usuario=new Usuario();
			usuarios=usuarioDao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		}
	}

	
	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			usuarioDao.excluir(usuario);
			usuarios=usuarioDao.listar();
			Utils.mostrarMensagemJsfSucesso("usuario excluido  com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		Utils.mostrarMensagemSwing("usuario pra editar "+usuario.getLogin()+" senha "+usuario.getSenha());
		/*usuario.setLogin(usuario.getLogin());
		usuario.setSenha(usuario.getSenha());
		usuario.setTipoUsuario(usuario.getTipoUsuario());*/
		//membros=membroDao.listar();
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Membro> getMembros() {
		return membros;
	}
	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}
	public FuncaoUsuario getFuncao() {
		return funcao;
	}
	public void setFuncao(FuncaoUsuario funcao) {
		this.funcao = funcao;
	}
	
	
	

}
