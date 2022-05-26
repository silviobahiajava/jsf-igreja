package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.LancamentoDao;
import daos.OfertaDasCriancasDao;
import entidades.Grupo;
import entidades.Lancamento;
import entidades.OfertaDasCriancas;

@ManagedBean(name="lancamentoofertadascrincasBean2")
@ViewScoped
public class LancamentoOfertaDasCriancasBean {
	private Lancamento lancamento=new Lancamento();
	private OfertaDasCriancas oferta;
	private LancamentoDao lancamentoDao=new LancamentoDao();

	private List<OfertaDasCriancas>listaDeOfertas;
	private List<OfertaDasCriancas>listaDeOfertasPraLancamento;
	//private CompraIgrejaDaocigdao=new CompraIgrejaDao();
	private OfertaDasCriancasDao ocdao=new OfertaDasCriancasDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	private BigDecimal valorReceita;
	private BigDecimal valorDespesa;
	private BigDecimal valorSaldo;
	
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaDeOfertas=ocdao.buscarOfertaDasCriancasPorGrupo(3L);
			listaDeOfertasPraLancamento=new ArrayList<OfertaDasCriancas>();
			
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
			OfertaDasCriancas oferta = listaDeOfertasPraLancamento.get(posicao);
			lancamento.setReceita(lancamento.getReceita().add(oferta.getValorOfertaCrianca()));
		}
	}
		public void adicionarOfertas(ActionEvent evento){
			oferta=(OfertaDasCriancas) evento.getComponent().getAttributes().get("ofertaSelecionada");
			listaDeOfertasPraLancamento.add(oferta);
		//calcular();
			Utils.mostrarMensagemJsfSucesso("oferta das crian�as adicionada na lista de compras do lan�amento");
				
			}
		
public void salvarLancamentoDeOfertas(){
			
			try{

				Grupo grupo=new Grupo();
				grupo.setCodigo(3L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(oferta.getDataOfertaCrianca());
				lancamento.setReceita(oferta.getValorOfertaCrianca());
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setHistorico(oferta.getDescricao());
				Long codigo=lancamentoDao.buscaUlitmoCodigo();
				valorSaldo=lancamentoDao.mostrarSaldoPorCodigo(codigo);
				
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
				
				lancamentoDao.salvarLancamentoOfertaDasCriancas(lancamento, listaDeOfertasPraLancamento);
				listaDeOfertas=ocdao.buscarOfertaDasCriancasPorGrupo(3L);
				
			 
			Utils.mostrarMensagemJsfSucesso("compra registrada  com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lan�amento de ofertas");
				e.printStackTrace();
			}
}

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
		}

		public List<OfertaDasCriancas> getListaDeOfertas() {
			return listaDeOfertas;
		}

		public void setListaDeOfertas(List<OfertaDasCriancas> listaDeOfertas) {
			this.listaDeOfertas = listaDeOfertas;
		}

		public List<OfertaDasCriancas> getListaDeOfertasPraLancamento() {
			return listaDeOfertasPraLancamento;
		}

		public void setListaDeOfertasPraLancamento(List<OfertaDasCriancas> listaDeOfertasPraLancamento) {
			this.listaDeOfertasPraLancamento = listaDeOfertasPraLancamento;
		}

		public OfertaDasCriancas getOferta() {
			return oferta;
		}

		public void setOferta(OfertaDasCriancas oferta) {
			this.oferta = oferta;
		}

		public BigDecimal getValorReceita() {
			return valorReceita;
		}

		public void setValorReceita(BigDecimal valorReceita) {
			this.valorReceita = valorReceita;
		}

		public BigDecimal getValorDespesa() {
			return valorDespesa;
		}

		public void setValorDespesa(BigDecimal valorDespesa) {
			this.valorDespesa = valorDespesa;
		}

		public BigDecimal getValorSaldo() {
			return valorSaldo;
		}

		public void setValorSaldo(BigDecimal valorSaldo) {
			this.valorSaldo = valorSaldo;
		}

		
}
