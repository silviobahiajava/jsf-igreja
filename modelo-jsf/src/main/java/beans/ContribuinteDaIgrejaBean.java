package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.ContribuicaoDaIgrejaDao;
import daos.ContribuinteDao;
import daos.MembroDao;
import entidades.ContribuicaoDaIgreja;
import entidades.Grupo;
import entidades.Membro;
import utils.Utils;

@ManagedBean(name="contribuicaodaigrejaBean")
@ViewScoped
public class ContribuinteDaIgrejaBean {
	/*private ContribuicaoDaIgreja contribuinte=new ContribuicaoDaIgreja();
	private List<ContribuicaoDaIgreja>contribuintes=new ArrayList<>();*/
	private ContribuicaoDaIgreja contribuicao=new ContribuicaoDaIgreja();
	private List<ContribuicaoDaIgreja>contribuicoesDaIgreja=new ArrayList<ContribuicaoDaIgreja>();
   private List<Membro>listaNovosContribuintes=new ArrayList<Membro>();
   private MembroDao mbdao=new MembroDao();
  /// private ContribuinteDao cdao=new ContribuinteDao();
   private ContribuicaoDaIgrejaDao cdao=new ContribuicaoDaIgrejaDao();
	
	
	public void novo() {
		/*contribuinte=new ContribuicaoDaIgreja();*/
		listaNovosContribuintes=mbdao.listar();
	}
	
	@PostConstruct
	public void listar() {
		
		try {
		contribuicoesDaIgreja=cdao.buscarContribuicaoDaIgrejaPorGrupo(8L);
		}catch(RuntimeException e) {
			
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
		contribuicao=(ContribuicaoDaIgreja) evento.getComponent().getAttributes().get("contribuinteSelecionado");
		cdao.editar(contribuicao);
		}catch(RuntimeException e) {
			
		}
		
	}
	
	
	public void excluir(ActionEvent evento) {
		try {
		contribuicao=(ContribuicaoDaIgreja) evento.getComponent().getAttributes().get("contribuinteSelecionado");
		cdao.excluir(contribuicao);
		}catch(RuntimeException e) {
			
		}
	}
	
	public void salvar() {
		Grupo grupo=new Grupo();
		grupo.setCodigo(8L);
		contribuicao.setGrupo(grupo);
		try {
		contribuicao.setDataPrevistaPraContribuir(contribuicao.getDataPrevistaPraContribuir());
		contribuicao.setValorQuePodeContribuir(contribuicao.getValorQuePodeContribuir());
		contribuicao.setContribuinte(contribuicao.getContribuinte());
		contribuicao.setCampanha(contribuicao.getCampanha());
		cdao.salvar(contribuicao);
		Utils.mostrarMensagemJsfSucesso("contribuicao registrado com sucesso");
		contribuicoesDaIgreja=cdao.listar();
		}catch(RuntimeException e) {
			
		}
	}
	
	
	public void buscar() {
		try {
			contribuicoesDaIgreja=cdao.buscarContribuicaoPorData(contribuicao.getDataPrevistaPraContribuir());
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}
	
	public ContribuicaoDaIgreja getContribuicao() {
		return contribuicao;
	}

	public void setContribuicao(ContribuicaoDaIgreja contribuicao) {
		this.contribuicao = contribuicao;
	}

	public List<ContribuicaoDaIgreja> getContribuicoesDaIgreja() {
		return contribuicoesDaIgreja;
	}

	public void setContribuicoesDaIgreja(List<ContribuicaoDaIgreja> contribuicoesDaIgreja) {
		this.contribuicoesDaIgreja = contribuicoesDaIgreja;
	}

	public List<Membro> getListaNovosContribuintes() {
		return listaNovosContribuintes;
	}

	public void setListaNovosContribuintes(List<Membro> listaNovosContribuintes) {
		this.listaNovosContribuintes = listaNovosContribuintes;
	}
	
	
}
