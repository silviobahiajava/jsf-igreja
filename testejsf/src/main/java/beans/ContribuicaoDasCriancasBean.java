package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CampanhaDasCriancasDao;
import daos.ContribuicaoDasCriancasDao;
import daos.MembroDao;
import entidades.CampanhaDasCriancas;
import entidades.ContribuicaoDasCriancas;
import entidades.Grupo;
import entidades.Membro;

@ManagedBean(name="contribuicaodascriancasBean")
@ViewScoped
public class ContribuicaoDasCriancasBean {
	/*private ContribuicaoDasCriancas contribuinte=new ContribuicaoDasCriancas();
	private List<ContribuicaoDasCriancas>contribuintes=new ArrayList<>();*/
	private ContribuicaoDasCriancas contribuicao=new ContribuicaoDasCriancas();
	private List<ContribuicaoDasCriancas>contribuicoesDaCriancas=new ArrayList<ContribuicaoDasCriancas>();
   private List<Membro>listaNovosContribuintes=new ArrayList<Membro>();
   private List<CampanhaDasCriancas>listaCampanhaDasCriancas=new ArrayList<CampanhaDasCriancas>();
   private MembroDao mbdao=new MembroDao();
   private CampanhaDasCriancasDao campanhaDao=new CampanhaDasCriancasDao();
  /// private ContribuinteDao cdao=new ContribuinteDao();
   private ContribuicaoDasCriancasDao cdao=new ContribuicaoDasCriancasDao();
   private String nomeCampanha;
   private boolean campanhaPe;
	
	public void novo() {
		/*contribuinte=new ContribuicaoDasCriancas();*/
		listaNovosContribuintes=mbdao.listar();
		listaCampanhaDasCriancas=campanhaDao.listar();
	}
	
	@PostConstruct
	public void listar() {
		
		try {
		contribuicoesDaCriancas=cdao.buscarContribuicaoDasCriancasPorGrupo(2L);
		}catch(RuntimeException e) {
			
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
		contribuicao=(ContribuicaoDasCriancas) evento.getComponent().getAttributes().get("contribuinteSelecionado");
		cdao.editar(contribuicao);
		}catch(RuntimeException e) {
			
		}
		
	}
	
	
	public void excluir(ActionEvent evento) {
		try {
		contribuicao=(ContribuicaoDasCriancas) evento.getComponent().getAttributes().get("contribuinteSelecionado");
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
		contribuicoesDaCriancas=cdao.listar();
		}catch(RuntimeException e) {
			
		}
	}
	
	
	public void buscar() {
		try {
			contribuicoesDaCriancas=cdao.buscarContribuicaoPorData(contribuicao.getDataPrevistaPraContribuir());
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}

	public ContribuicaoDasCriancas getContribuicao() {
		return contribuicao;
	}

	public void setContribuicao(ContribuicaoDasCriancas contribuicao) {
		this.contribuicao = contribuicao;
	}

	

	public List<ContribuicaoDasCriancas> getContribuicoesDaCriancas() {
		return contribuicoesDaCriancas;
	}

	public void setContribuicoesDaCriancas(List<ContribuicaoDasCriancas> contribuicoesDaCriancas) {
		this.contribuicoesDaCriancas = contribuicoesDaCriancas;
	}

	public List<Membro> getListaNovosContribuintes() {
		return listaNovosContribuintes;
	}

	public void setListaNovosContribuintes(List<Membro> listaNovosContribuintes) {
		this.listaNovosContribuintes = listaNovosContribuintes;
	}

	public List<CampanhaDasCriancas> getListaCampanhaDasCriancas() {
		return listaCampanhaDasCriancas;
	}

	public void setListaCampanhaDasCriancas(List<CampanhaDasCriancas> listaCampanhaDasCriancas) {
		this.listaCampanhaDasCriancas = listaCampanhaDasCriancas;
	}
	
	public void pegarGrupo(){
		nomeCampanha=contribuicao.getCampanha().getNomeCampanhaDasCriancas();
		Utils.mostrarMensagemJsfSucesso("campanha selecionada "+nomeCampanha);
		if(nomeCampanha.equals("CAMPANHA DO P�")){
			campanhaPe=true;
		}
		
	}

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public boolean isCampanhaPe() {
		return campanhaPe;
	}

	public void setCampanhaPe(boolean campanhaPe) {
		this.campanhaPe = campanhaPe;
	}
	
	
}
