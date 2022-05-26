package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.ComprasDasCriancasDao;
import daos.LancamentoDao;
import entidades.CompraDasCriancas;
import entidades.Grupo;
import entidades.Lancamento;
import utils.Utils;

@ManagedBean(name="lancamentocompracriancasBean")
@ViewScoped
public class LancamentoComprasDasCriancasBean {
	private Lancamento lancamento=new Lancamento();
	private CompraDasCriancas compra;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	private List<CompraDasCriancas>listaCompras;
	private List<CompraDasCriancas>listaComprasLancamento;
	private BigDecimal valorReceita;
	private BigDecimal valorDespesa;
	private BigDecimal valorSaldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			lancamento.setData(new Date());
			lancamento.setDespesa(new BigDecimal("0.00"));
			 ComprasDasCriancasDao compraDao=new ComprasDasCriancasDao();
			// listaCompras=compraDao.listar();
			 listaCompras=compraDao.buscarCompraDasCriancasPorGrupo(2L);
            listaComprasLancamento=new ArrayList<CompraDasCriancas>();
			
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

	

	public CompraDasCriancas getCompra() {
		return compra;
	}

	public void setCompra(CompraDasCriancas compra) {
		this.compra = compra;
	}

	public List<CompraDasCriancas> getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(List<CompraDasCriancas> listaCompras) {
		this.listaCompras = listaCompras;
	}

	public List<CompraDasCriancas> getListaComprasLancamento() {
		return listaComprasLancamento;
	}

	public void setListaComprasLancamento(List<CompraDasCriancas> listaComprasLancamento) {
		this.listaComprasLancamento = listaComprasLancamento;
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
		for (int posicao = 0; posicao < listaComprasLancamento.size(); posicao++) {
			CompraDasCriancas minhaCompra = listaComprasLancamento.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			lancamento.setDespesa(lancamento.getDespesa().add(minhaCompra.getValorCompraCrianca()));
		}
	}
		public void adicionarCompras(ActionEvent evento){
			compra=(CompraDasCriancas) evento.getComponent().getAttributes().get("compraSelecionada");
			listaComprasLancamento.add(compra);
			//calcular();
			Utils.mostrarMensagemJsfSucesso("compra"+compra.getCodigo()+"adicionada na lista de compras do lançamento");
				listaCompras.remove(compra);
			}
		
		public void salvarLancamentoDeCompras(){
			LancamentoDao ldao=new LancamentoDao();
			ComprasDasCriancasDao compraDao=new ComprasDasCriancasDao();
			try{

				Grupo grupo=new Grupo();
				grupo.setCodigo(2L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(compra.getDataCompraCrianca());
				lancamento.setReceita(new BigDecimal("0.00"));
				lancamento.setDespesa(compra.getValorCompraCrianca());
				Long codigo=ldao.buscaUlitmoCodigo();
				valorSaldo=ldao.mostrarSaldoPorCodigo(codigo);
				
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
				
				ldao.salvarLancamentoCompraDasCriancas(lancamento, listaComprasLancamento);
				listaCompras=compraDao.buscarCompraDasCriancasPorGrupo(2L);
				
			 
			Utils.mostrarMensagemJsfSucesso("compra registrada  com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lançamento de ofertas");
				e.printStackTrace();
			}
}

}