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

import daos.LancamentoDao;
import daos.OfertaDao;
import entidades.Grupo;
import entidades.Lancamento;
import entidades.Oferta;
import utils.Utils;
@ManagedBean(name="lancamentoOfertaBean")
@ViewScoped
public class LancamentoOfertaBean {
	private Lancamento lancamento=new Lancamento();
	private Oferta oferta;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	private List<Oferta>listaOfertas;
	private List<Oferta>listaOfertasLancamento;
	//private BigDecimal despesa;
	private BigDecimal saldo;
	private OfertaDao ofertaDao=new OfertaDao();
	private LancamentoDao ldao=new LancamentoDao();
	private String nomecliente;
	private String livrocaixa;
	
	@PostConstruct
	public void novo() {
		/*try {
			Map<String, String> meumapa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			nomecliente=meumapa.get("namekey");
			switch(nomecliente){
			case "1":
				livrocaixa="Livro Caixa do Grupo de Varões";
				break;
			case "2":
				livrocaixa="Livro Caixa do Círculo de Oração";
				break;
			case "3":
				livrocaixa="Livro Caixa do Grupo de Crianças";
				break;
			case "4":
				livrocaixa="Livro Caixa do Grupo de Adolescentes";
				break;
			case "5":
				livrocaixa="Livro Caixa da Mocidade";
				break;
			case "6":
				livrocaixa="Livro Caixa do Grupo de Coreografia Adulto";
				break;
			case "7":
				livrocaixa="Livro Caixa da igreja";
				break;
				default:
					livrocaixa="indefinido";
					
			}
			
			lancamento=new Lancamento();
			lancamento.setData(new Date());
			//lancamento.setDespesa(new BigDecimal("0.00"));
			lancamento.setReceita(new BigDecimal("0.00"));
			
			// listaCompras=compraDao.listar();
			 listaOfertas=ofertaDao.buscarOfertaPorCodigo(Long.parseLong(nomecliente));
            listaOfertasLancamento=new ArrayList<>();
			
		} catch (RuntimeException erro) {
			
			erro.printStackTrace();
		}
		*/
	}

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
	
	public void calcular() {
		//venda.setPrecoTotal(new BigDecimal("0.00"));
        lancamento.setDespesa(new BigDecimal("0.00"));
		for (int posicao = 0; posicao < listaOfertasLancamento.size(); posicao++) {
			Oferta minhaOferta = listaOfertasLancamento.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			lancamento.setDespesa(lancamento.getDespesa().add(minhaOferta.getValorOferta()));
		}
	}
		public void adicionarOfertas(ActionEvent evento){
			oferta=(Oferta) evento.getComponent().getAttributes().get("ofertaSelecionada");
			listaOfertasLancamento.add(oferta);
			//calcular();
			Utils.mostrarMensagemJsfSucesso("oferta adicionada na lista de compras do lançamento");
				listaOfertas.remove(oferta);
			}
		
		public void salvarLancamentoDeOfertas(){
			
			try{
//				GrupoDao gdao=new GrupoDao();
//				Grupo grupopesquisado=gdao.buscar(8L);
//				lancamento.setGrupo(grupopesquisado);
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				lancamento.setData(oferta.getDataOferta());
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setReceita(oferta.getValorOferta());
				
			
			ldao.salvarLancamentoOferta(lancamento, listaOfertasLancamento);
			Utils.mostrarMensagemJsfSucesso("total de saldo do caixa"+saldo);
			Utils.mostrarMensagemJsfSucesso("lançamento de ofertas realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lançamento de ofertas");
				e.printStackTrace();
			}
			
		}
		
		

		public Oferta getOferta() {
			return oferta;
		}

		public void setOferta(Oferta oferta) {
			this.oferta = oferta;
		}

		public List<Oferta> getListaOfertas() {
			return listaOfertas;
		}

		public void setListaOfertas(List<Oferta> listaOfertas) {
			this.listaOfertas = listaOfertas;
		}

		public List<Oferta> getListaOfertasLancamento() {
			return listaOfertasLancamento;
		}

		public void setListaOfertasLancamento(List<Oferta> listaOfertasLancamento) {
			this.listaOfertasLancamento = listaOfertasLancamento;
		}

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
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

		
		
		
}
