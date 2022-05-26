package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CampanhaDao;
import daos.CompraIgrejaDao;
import daos.LancamentoDao;
import entidades.Campanha;
import entidades.Lancamento;

@ManagedBean(name="lancamentoCampanhaBean")
@ViewScoped
public class LancamentoCampanhaBean {
	private Lancamento lancamento=new Lancamento();
	private Campanha campanha=new Campanha();
	private LancamentoDao lancamentoDao=new LancamentoDao();
	private List<Campanha>listaCampanhas=new ArrayList<Campanha>();
	private List<Campanha>listaCampanhasLancamento=new ArrayList<Campanha>();
	private String nomecliente;
	private String livrocaixa;
	CampanhaDao campanhaDao=new CampanhaDao();
	BigDecimal base;
	/*@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			lancamento.setData(new Date());
			lancamento.setDespesa(new BigDecimal("0.00"));
			 OfertaDao ofertaDao=new OfertaDao();
			// listaOfertas=ofertaDao.listar();
			// listaOfertas=ofertaDao.buscarOfertaPorGrupo("var�es");
			 listaOfertas=ofertaDao.buscarOfertaPorCodigo(1L);
            listaOfertasLancamento=new ArrayList<>();
			
		} catch (RuntimeException erro) {
			
			erro.printStackTrace();
		}
	}*/
	
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			lancamento.setData(new Date());
			lancamento.setDespesa(new BigDecimal("0.00"));
			listaCampanhas=campanhaDao.buscarCampanhaPorCodigo(8L);
			listaCampanhasLancamento=new ArrayList<Campanha>();
			
           
			
		} catch (RuntimeException erro) {
			
			erro.printStackTrace();
		}
	}
	
	/*@PostConstruct
	public void init(){
		Map<String, String> meumapa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		nomecliente=meumapa.get("namekey");
		switch(nomecliente){
		case "1":
			livrocaixa="Livro Caixa do Grupo de Var�es";
			
			listaCampanhas=campanhaDao.buscarCampanhaPorCodigo(1L);
			break;
		case "2":
			livrocaixa="Livro Caixa do C�rculo de Ora��o";
			
			listaCampanhas=campanhaDao.buscarCampanhaPorCodigo(2L);
			break;
		case "3":
			livrocaixa="Livro Caixa do Grupo de Crian�as";
			listaCampanhas=campanhaDao.buscarCampanhaPorCodigo(3L);
			break;
		case "4":
			livrocaixa="Livro Caixa do Grupo de Adolescentes";
			listaCampanhas=campanhaDao.buscarCampanhaPorCodigo(4L);
			break;
		case "5":
			livrocaixa="Livro Caixa da Mocidade";
			listaCampanhas=campanhaDao.buscarCampanhaPorCodigo(5L);
			break;
		case "6":
			livrocaixa="Livro Caixa do Grupo de Coreografia Adulto";
			listaCampanhas=campanhaDao.buscarCampanhaPorCodigo(6L);
			break;
		case "7":
			livrocaixa="Livro Caixa da igreja";
			listaCampanhas=campanhaDao.buscarCampanhaPorCodigo(7L);
			break;
			default:
				livrocaixa="indefinido";
		}
		
		//listarCampanha();
	}
	
	/*public void listarCampanha(){
		CampanhaDao campanhaDao=new CampanhaDao();
		listaCampanhas=campanhaDao.buscarCamoanhaPorCodigo(Long.parseLong(nomecliente));
	}*/

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	

	public LancamentoDao getLancamentoDao() {
		return lancamentoDao;
	}

	public void setLancamentoDao(LancamentoDao lancamentoDao) {
		this.lancamentoDao = lancamentoDao;
	}

	

	
	
	//novo metodo
	
//	public void calcular() {
//		
//		//lancamento.setReceita(new BigDecimal("30.00"));
//		
//		// base=BigDecimal.ZERO;
//		 
//		for (int posicao = 0; posicao < listaCampanhasLancamento.size(); posicao++) {
//			Campanha campanha = listaCampanhasLancamento.get(posicao);
//			lancamento.setReceita(lancamento.getReceita().add(campanha.getValorCampanha()));
//			
//			//lancamento.setReceita(base.add(campanha.getValorCampanha()));
//			//base=base.add(campanha.getValorCampanha());
//		//	base=base.add(new BigDecimal(campanha.getValorCampanha()));
//			Utils.mostrarMensagemJsfSucesso("valor da receita "+lancamento.getReceita());
//		}
//	}
		public void adicionarCampanha(ActionEvent evento){
			campanha=(Campanha) evento.getComponent().getAttributes().get("campanhaSelecionada");
			listaCampanhasLancamento.add(campanha);
			
			Utils.mostrarMensagemJsfSucesso("valor da campanha"+campanha.getValorCampanha());
				
			}
		
		public void salvarLancamentoDeCampanhas(){
			LancamentoDao ldao=new LancamentoDao();
			try{

			lancamento.setData(campanha.getDataCampanha());
			lancamento.setDescricao(campanha.getNomeCampanha());
			lancamento.setReceita(campanha.getValorCampanha());
			lancamento.setDespesa(new BigDecimal("00.00"));
			
			ldao.salvarLancamentoCampanha(lancamento, listaCampanhasLancamento);
			
			Utils.mostrarMensagemJsfSucesso("lan�amento de campannha realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lan�amento de compras");
				e.printStackTrace();
			}
		}
		public Campanha getCampanha() {
			return campanha;
		}
		public void setCampanha(Campanha campanha) {
			this.campanha = campanha;
		}
		public List<Campanha> getListaCampanhas() {
			return listaCampanhas;
		}
		public void setListaCampanhas(List<Campanha> listaCampanhas) {
			this.listaCampanhas = listaCampanhas;
		}
		public List<Campanha> getListaCampanhasLancamento() {
			return listaCampanhasLancamento;
		}
		public void setListaCampanhasLancamento(List<Campanha> listaCampanhasLancamento) {
			this.listaCampanhasLancamento = listaCampanhasLancamento;
		}
		public String getNomecliente() {
			return nomecliente;
		}
		public void setNomecliente(String nomecliente) {
			this.nomecliente = nomecliente;
		}
		public String getLivrocaixa() {
			return livrocaixa;
		}
		public void setLivrocaixa(String livrocaixa) {
			this.livrocaixa = livrocaixa;
		}

		public BigDecimal getBase() {
			return base;
		}

		public void setBase(BigDecimal base) {
			this.base = base;
		}
		
		
}
