package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.CompraDao;
import daos.LancamentoDao;
import daos.OfertaDaIgrejaDao;
import daos.OfertaDasCriancasDao;
import entidades.Compra;
import entidades.Dizimo;
import entidades.Grupo;
import entidades.Lancamento;
import entidades.OfertaDaIgreja;
import entidades.OfertaDasCriancas;
import utils.Utils;

@ManagedBean(name="lancamentoofertadaigrejaBean")
@ViewScoped
public class LancamenrtoOfertaDaIgrejaBean {
	private Lancamento lancamento=new Lancamento();
	private OfertaDaIgreja oferta;
	private LancamentoDao lancamentoDao=new LancamentoDao();

	private List<OfertaDaIgreja>listaDeOfertas;
	private List<OfertaDaIgreja>listaDeOfertasPraLancamento;
	//private CompraIgrejaDaocigdao=new CompraIgrejaDao();
	private OfertaDaIgrejaDao oidao=new OfertaDaIgrejaDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	private BigDecimal valorReceita;
		private BigDecimal valorDespesa;
		private BigDecimal valorSaldo;
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaDeOfertas=oidao.buscarOfertaDaIgrejaPorGrupo(8L);
			listaDeOfertasPraLancamento=new ArrayList<OfertaDaIgreja>();
			
		} catch (RuntimeException erro) {
			
			erro.printStackTrace();
		}
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
		for (int posicao = 0; posicao < listaDeOfertasPraLancamento.size(); posicao++) {
			OfertaDaIgreja oferta = listaDeOfertasPraLancamento.get(posicao);
			lancamento.setReceita(lancamento.getReceita().add(oferta.getValorOfertaIgreja()));
		}
	}
		public void adicionarOfertas(ActionEvent evento){
			oferta=(OfertaDaIgreja) evento.getComponent().getAttributes().get("ofertaSelecionada");
			listaDeOfertasPraLancamento.add(oferta);
		//calcular();
			Utils.mostrarMensagemJsfSucesso("oferta das crianças adicionada na lista de compras do lançamento");
				
			}
		
		public void salvarLancamentoDeOfertas(){
			LancamentoDao ldao=new LancamentoDao();
			try{

				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(oferta.getDataOfertaIgreja());
				lancamento.setReceita(oferta.getValorOfertaIgreja());
				lancamento.setDespesa(new BigDecimal("0.00"));
				Long codigo=ldao.buscaUlitmoCodigo();
				valorSaldo=ldao.mostrarSaldoPorCodigo(codigo);
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
				ldao.salvarLancamentoOfertaDaIgreja(lancamento, listaDeOfertasPraLancamento);
				listaDeOfertas=oidao.buscarOfertaDaIgrejaPorGrupo(8L);
				
			 
			Utils.mostrarMensagemJsfSucesso("lançamento de ofertas realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lançamento de ofertas");
				e.printStackTrace();
			}
		}

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
		}

		public OfertaDaIgreja getOferta() {
			return oferta;
		}

		public void setOferta(OfertaDaIgreja oferta) {
			this.oferta = oferta;
		}

		public List<OfertaDaIgreja> getListaDeOfertas() {
			return listaDeOfertas;
		}

		public void setListaDeOfertas(List<OfertaDaIgreja> listaDeOfertas) {
			this.listaDeOfertas = listaDeOfertas;
		}

		public List<OfertaDaIgreja> getListaDeOfertasPraLancamento() {
			return listaDeOfertasPraLancamento;
		}

		public void setListaDeOfertasPraLancamento(List<OfertaDaIgreja> listaDeOfertasPraLancamento) {
			this.listaDeOfertasPraLancamento = listaDeOfertasPraLancamento;
		}

	
}
