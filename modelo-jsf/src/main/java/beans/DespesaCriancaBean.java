package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


import daos.DespesaCriancaDao;
import daos.GrupoDao;

import entidades.DespesaCrianca;
import entidades.Grupo;
import utils.Utils;

@ManagedBean(name="despesaCriancaBean")
@ViewScoped
public class DespesaCriancaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private DespesaCrianca despesaCrianca = new DespesaCrianca();
	private DespesaCriancaDao despesaDao=new DespesaCriancaDao();
	private List<DespesaCrianca> listaDespesaCrianca = new ArrayList<DespesaCrianca>();
	private List<Grupo> grupos;
	private String nomeGrupo;
	public void pegarGrupoSelecionado(){
		
		nomeGrupo=despesaCrianca.getGrupo().getNome();
		if(!nomeGrupo.equals("infantil")) {
			Utils.mostrarMensagemJsfErro("voce n�o tem permiss�o para acessar este grupo\nselecione o grupo certo e tente novamente");
		}
	}
	@PostConstruct
	public void listar() {
		listaDespesaCrianca=despesaDao.buscarDespesasDasCriancasPorGrupo(2L);
	}
	
	public void novo() {
		despesaCrianca = new DespesaCrianca();
		GrupoDao grupoDao = new GrupoDao();
		grupos = grupoDao.listar();
	}

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(2L);
			despesaCrianca.setGrupo(grupo);
			despesaCrianca.setDataPagamentoDespesaCrianca(despesaCrianca.getDataPagamentoDespesaCrianca());
			despesaCrianca.setDescricao(despesaCrianca.getDescricao());
			despesaCrianca.setValorDespescaCrianca(despesaCrianca.getValorDespescaCrianca());
			despesaDao.salvar(despesaCrianca);
			Utils.mostrarMensagemJsfSucesso("pagamento de despesa registrado com sucesso");
			novo();
			listaDespesaCrianca=despesaDao.buscarDespesasDasCriancasPorGrupo(2L);
			
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			despesaCrianca = (DespesaCrianca) evento.getComponent().getAttributes().get("despesaSelecionada");
			despesaDao.excluir(despesaCrianca);
			listaDespesaCrianca = despesaDao.buscarDespesasDasCriancasPorGrupo(2L);
			Utils.mostrarMensagemJsfSucesso("opera��o realizada com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			despesaCrianca = (DespesaCrianca) evento.getComponent().getAttributes().get("despesaSelecionada");
			despesaDao.editar(despesaCrianca);
			listaDespesaCrianca = despesaDao.buscarDespesasDasCriancasPorGrupo(2L);
			Utils.mostrarMensagemJsfSucesso("opera��o realizada com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}
	
	
	
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	public String getNomeGrupo() {
		return nomeGrupo;
	}
	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}
	public DespesaCrianca getDespesaCrianca() {
		return despesaCrianca;
	}
	public void setDespesaCrianca(DespesaCrianca despesaCrianca) {
		this.despesaCrianca = despesaCrianca;
	}
	public List<DespesaCrianca> getListaDespesaCrianca() {
		return listaDespesaCrianca;
	}
	public void setListaDespesaCrianca(List<DespesaCrianca> listaDespesaCrianca) {
		this.listaDespesaCrianca = listaDespesaCrianca;
	}
	
	
	
}
