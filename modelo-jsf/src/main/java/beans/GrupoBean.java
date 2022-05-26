package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Utils;
import daos.GrupoDao;
import entidades.Grupo;

@ManagedBean(name="grupoBean")
@ViewScoped
public class GrupoBean {
	private List<Grupo>grupos;
	private Grupo grupo;
	@PostConstruct
	public void listar() {
		try {
			GrupoDao  grupoDao=new GrupoDao();
			grupos= grupoDao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	public void novo(){
		grupo=new Grupo();
	}
	
	public void salvar() {
		try {
			GrupoDao  grupoDao=new GrupoDao();
			grupoDao.merge(grupo);
			 grupo=new Grupo();
			grupos= grupoDao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		}
	}

	
	public void excluir(ActionEvent evento) {
		try {
			grupo = (Grupo) evento.getComponent().getAttributes().get("grupoSelecionado");
			GrupoDao grupoDao=new GrupoDao();
			grupoDao.excluir(grupo);
			grupos= grupoDao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("não foi possível excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		grupo = (Grupo) evento.getComponent().getAttributes().get("grupoSelecionado");
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	
	
}
