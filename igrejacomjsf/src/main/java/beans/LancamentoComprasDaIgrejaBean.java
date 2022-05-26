package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.CompraIgrejaDao;
import daos.LancamentoDao;
import entidades.CompraIgreja;
import entidades.Grupo;
import entidades.Lancamento;
import utils.Utils;


@ManagedBean(name="lancamentocomprasdaigrejaBean")
@ViewScoped
public class LancamentoComprasDaIgrejaBean {
	
	private Lancamento lancamento=new Lancamento();
	private CompraIgreja compra;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	
   	 private BigDecimal valorReceita;
  		private BigDecimal valorDespesa;
  		private BigDecimal valorSaldo;
    

	private List<CompraIgreja>listaDeCompras;
	private List<CompraIgreja>listaDeComprasPraLancamento;
	private CompraIgrejaDao cigdao=new CompraIgrejaDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaDeCompras=cigdao.buscarCompraDaIgrejaPorGrupo(8L);
			listaDeComprasPraLancamento=new ArrayList<CompraIgreja>();
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


	public void calcular() {
		//venda.setPrecoTotal(new BigDecimal("0.00"));
        lancamento.setDespesa(new BigDecimal("0.00"));
		for (int posicao = 0; posicao < listaDeComprasPraLancamento.size(); posicao++) {
			CompraIgreja compra = listaDeComprasPraLancamento.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			lancamento.setDespesa(lancamento.getDespesa().add(compra.getValorCompraIgreja()));
		}
	}
		public void adicionarCompras(ActionEvent evento){
			compra=(CompraIgreja) evento.getComponent().getAttributes().get("compraSelecionada");
			
			listaDeComprasPraLancamento.add(compra);
			//calcular();
			Utils.mostrarMensagemJsfSucesso("compra "+compra.getCodigo()+"adicionada na lista de compras do lançamento");
				
			}
		
		public void salvarLancamentoDeCompras(){
			LancamentoDao ldao=new LancamentoDao();
			try{

				//valorReceita=ldao.mostrarReceitaPorGrupo(8L);
				//valorDespesa=ldao.mostrarDespesaPorGrupo(8L);
				
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(compra.getDataCompraIgreja());
				
				lancamento.setHistorico(compra.getDescricao());
				lancamento.setDespesa(compra.getValorCompraIgreja());
				lancamento.setReceita(new BigDecimal("0.00"));
				Long codigo=ldao.buscaUlitmoCodigo();
				valorSaldo=ldao.mostrarSaldoPorCodigo(codigo);
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
			   ldao.salvarLancamentoCompraDaIgreja(lancamento, listaDeComprasPraLancamento);
			   listaDeCompras=cigdao.buscarCompraDaIgrejaPorGrupo(8L);
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

		public CompraIgreja getCompra() {
			return compra;
		}

		public void setCompra(CompraIgreja compra) {
			this.compra = compra;
		}

		public List<CompraIgreja> getListaDeCompras() {
			return listaDeCompras;
		}

		public void setListaDeCompras(List<CompraIgreja> listaDeCompras) {
			this.listaDeCompras = listaDeCompras;
		}

		public List<CompraIgreja> getListaDeComprasPraLancamento() {
			return listaDeComprasPraLancamento;
		}

		public void setListaDeComprasPraLancamento(List<CompraIgreja> listaDeComprasPraLancamento) {
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

		public BigDecimal getValorSaldo() {
			return valorSaldo;
		}

		public void setValorSaldo(BigDecimal valorSaldo) {
			this.valorSaldo = valorSaldo;
		}

		public CompraIgrejaDao getCigdao() {
			return cigdao;
		}

		public void setCigdao(CompraIgrejaDao cigdao) {
			this.cigdao = cigdao;
		}

	
		
		
}
