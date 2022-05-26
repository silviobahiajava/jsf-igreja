package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CompraDaMissaoDao;
import daos.LancamentoDao;
import entidades.CompraDaMissao;
import entidades.Grupo;
import entidades.Lancamento;

@ManagedBean(name="lancamentocompradamissaoBean")
@ViewScoped
public class LancamentoCompraDaMissaoBean {
	private Lancamento lancamento=new Lancamento();
	private CompraDaMissao compra;
	private LancamentoDao lancamentoDao=new LancamentoDao();

	private List<CompraDaMissao>listaDeCompras;
	private List<CompraDaMissao>listaDeComprasPraLancamento;
	//private CompraIgrejaDaocigdao=new CompraIgrejaDao();
	private CompraDaMissaoDao cmdao=new CompraDaMissaoDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			
			listaDeCompras=cmdao.buscarCompraDaMissaoPorGrupo(4L);
			listaDeComprasPraLancamento=new ArrayList<CompraDaMissao>();
           
			
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
			CompraDaMissao compra = listaDeComprasPraLancamento.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			lancamento.setDespesa(lancamento.getDespesa().add(compra.getValorCompraMissao()));
		}
	}
		public void adicionarCompras(ActionEvent evento){
			compra=(CompraDaMissao) evento.getComponent().getAttributes().get("compraSelecionada");
			
			listaDeComprasPraLancamento.add(compra);
			//calcular();
			Utils.mostrarMensagemJsfSucesso("compra do Departamento de Miss�o adicionada na lista de compras do lan�amento");
				
			}
		
		public void salvarLancamentoDeCompras(){
			LancamentoDao ldao=new LancamentoDao();
			try{

				Grupo grupo=new Grupo();
				grupo.setCodigo(4L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(compra.getDataCompraMissao() );
				
				lancamento.setHistorico(compra.getDescricao());
				lancamento.setDespesa(compra.getValorCompraMissao() );
				lancamento.setReceita(new BigDecimal("0.00"));
			  ldao.salvarLancamentoCompraDaMissao(lancamento,listaDeComprasPraLancamento);
			  
			  // listaDeCompras=cvdao.buscarCompraDaMissaoPorGrupo(1L);
			  listaDeCompras=cmdao.buscarCompraDaMissaoPorGrupo(4L);
			   
			Utils.mostrarMensagemJsfSucesso("lan�amento de compras realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lan�amento de compras");
				e.printStackTrace();
			}
		}

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
		}

		public CompraDaMissao getCompra() {
			return compra;
		}

		public void setCompra(CompraDaMissao compra) {
			this.compra = compra;
		}

		public List<CompraDaMissao> getListaDeCompras() {
			return listaDeCompras;
		}

		public void setListaDeCompras(List<CompraDaMissao> listaDeCompras) {
			this.listaDeCompras = listaDeCompras;
		}

		public List<CompraDaMissao> getListaDeComprasPraLancamento() {
			return listaDeComprasPraLancamento;
		}

		public void setListaDeComprasPraLancamento(List<CompraDaMissao> listaDeComprasPraLancamento) {
			this.listaDeComprasPraLancamento = listaDeComprasPraLancamento;
		}

}
