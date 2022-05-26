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
import daos.OfertaDosVaroesDao;
import entidades.Grupo;
import entidades.Lancamento;
import entidades.OfertaDosVaroes;

@ManagedBean(name="lancamentoofertadosvaroesBean")
@ViewScoped
public class LancamentoOfertaDosVaroesBean {
	private Lancamento lancamento=new Lancamento();
	private OfertaDosVaroes oferta;
	private LancamentoDao lancamentoDao=new LancamentoDao();

	private List<OfertaDosVaroes>listaDeOfertas;
	private List<OfertaDosVaroes>listaDeOfertasPraLancamento;
	//private CompraIgrejaDaocigdao=new CompraIgrejaDao();
	private OfertaDosVaroesDao ocdao=new OfertaDosVaroesDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	
	private BigDecimal valorReceita;
	private BigDecimal valorDespesa;
	private BigDecimal valorSaldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaDeOfertas=ocdao.buscarOfertaDosVaroesPorGrupo(1L);
			listaDeOfertasPraLancamento=new ArrayList<OfertaDosVaroes>();
			
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

	//novo metodo
	
	

	

	

	public void calcular() {
		//venda.setPrecoTotal(new BigDecimal("0.00"));
        lancamento.setDespesa(new BigDecimal("0.00"));
		for (int posicao = 0; posicao < listaDeOfertasPraLancamento.size(); posicao++) {
			OfertaDosVaroes oferta = listaDeOfertasPraLancamento.get(posicao);
			lancamento.setReceita(lancamento.getReceita().add(oferta.getValorOfertaVarao()));
		}
	}
		public void adicionarOfertas(ActionEvent evento){
			oferta=(OfertaDosVaroes) evento.getComponent().getAttributes().get("ofertaSelecionada");
			listaDeOfertasPraLancamento.add(oferta);
		//calcular();
			Utils.mostrarMensagemJsfSucesso("oferta das var�es adicionada na lista de compras do lan�amento");
				
			}
		
		public void setLancamentoDao(LancamentoDao lancamentoDao) {
			this.lancamentoDao = lancamentoDao;
		}

		public void salvarLancamentoDeOfertas(){
			LancamentoDao ldao=new LancamentoDao();
			try{

				Grupo grupo=new Grupo();
				grupo.setCodigo(1L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(oferta.getDataOfertaVarao());
				lancamento.setReceita(oferta.getValorOfertaVarao());
				lancamento.setDespesa(new BigDecimal("0.00"));
				Long codigo=ldao.buscaUlitmoCodigo();
				valorSaldo=ldao.mostrarSaldoPorCodigo(codigo);
				
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
				ldao.salvarLancamentoOfertaDosVaroes(lancamento, listaDeOfertasPraLancamento);
				listaDeOfertas=ocdao.buscarOfertaDosVaroesPorGrupo(1L);
			 
			Utils.mostrarMensagemJsfSucesso("lan�amento de ofertas realizado com sucesso");
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

		public List<OfertaDosVaroes> getListaDeOfertas() {
			return listaDeOfertas;
		}

		public void setListaDeOfertas(List<OfertaDosVaroes> listaDeOfertas) {
			this.listaDeOfertas = listaDeOfertas;
		}

		public List<OfertaDosVaroes> getListaDeOfertasPraLancamento() {
			return listaDeOfertasPraLancamento;
		}

		public void setListaDeOfertasPraLancamento(List<OfertaDosVaroes> listaDeOfertasPraLancamento) {
			this.listaDeOfertasPraLancamento = listaDeOfertasPraLancamento;
		}

		public OfertaDosVaroes getOferta() {
			return oferta;
		}

		public void setOferta(OfertaDosVaroes oferta) {
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
