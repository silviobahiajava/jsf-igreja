package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.CompraDosVaroesDao;
import daos.LancamentoDao;
import entidades.CompraDosVaroes;
import entidades.Grupo;
import entidades.Lancamento;
import utils.Utils;

@ManagedBean(name="lancamentocomprasdosvaroesBean")
@ViewScoped
public class LancamentoCompraDosVaroesBean {
	private Lancamento lancamento=new Lancamento();
	private CompraDosVaroes compra;
	private LancamentoDao lancamentoDao=new LancamentoDao();

	private List<CompraDosVaroes>listaDeCompras;
	private List<CompraDosVaroes>listaDeComprasPraLancamento;
	//private CompraIgrejaDaocigdao=new CompraIgrejaDao();
	private CompraDosVaroesDao cvdao=new CompraDosVaroesDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	
	private BigDecimal valorReceita;
		private BigDecimal valorDespesa;
		private BigDecimal valorSaldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			
			listaDeCompras=cvdao.buscarCompraDosVaroesPorGrupo(1L);
			listaDeComprasPraLancamento=new ArrayList<CompraDosVaroes>();
           
			
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
		for (int posicao = 0; posicao < listaDeComprasPraLancamento.size(); posicao++) {
			CompraDosVaroes compra = listaDeComprasPraLancamento.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			lancamento.setDespesa(lancamento.getDespesa().add(compra.getValorCompraVarao()));
		}
	}
		public void adicionarCompras(ActionEvent evento){
			compra=(CompraDosVaroes) evento.getComponent().getAttributes().get("compraSelecionada");
			
			listaDeComprasPraLancamento.add(compra);
			//calcular();
			Utils.mostrarMensagemJsfSucesso("compra dos varões adicionada na lista de compras do lançamento");
				
			}
		
		public void salvarLancamentoDeCompras(){
			LancamentoDao ldao=new LancamentoDao();
			try{

				Grupo grupo=new Grupo();
				grupo.setCodigo(1L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(compra.getDataCompraVarao());
				
				lancamento.setHistorico(compra.getDescricao());
				lancamento.setDespesa(compra.getValorCompraVarao());
				lancamento.setReceita(new BigDecimal("0.00"));
				
				Long codigo=ldao.buscaUlitmoCodigo();
				valorSaldo=ldao.mostrarSaldoPorCodigo(codigo);
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
			  
			  ldao.salvarLancamentoCompraDasVaroes(lancamento,listaDeComprasPraLancamento);
			  
			   listaDeCompras=cvdao.buscarCompraDosVaroesPorGrupo(1L);
			Utils.mostrarMensagemJsfSucesso("lançamento de compras realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lançamento de compras");
				e.printStackTrace();
			}
		}

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
		}

		public CompraDosVaroes getCompra() {
			return compra;
		}

		public void setCompra(CompraDosVaroes compra) {
			this.compra = compra;
		}

		public List<CompraDosVaroes> getListaDeCompras() {
			return listaDeCompras;
		}

		public void setListaDeCompras(List<CompraDosVaroes> listaDeCompras) {
			this.listaDeCompras = listaDeCompras;
		}

		public List<CompraDosVaroes> getListaDeComprasPraLancamento() {
			return listaDeComprasPraLancamento;
		}

		public void setListaDeComprasPraLancamento(List<CompraDosVaroes> listaDeComprasPraLancamento) {
			this.listaDeComprasPraLancamento = listaDeComprasPraLancamento;
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

		
	
}
