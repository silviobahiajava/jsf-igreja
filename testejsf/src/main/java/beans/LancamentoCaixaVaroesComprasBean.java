package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CompraDao;
import daos.LancamentoDao;
import entidades.Compra;
import entidades.Lancamento;


@ManagedBean(name="lancamentoCaixaVaroesComprasBean")
@ViewScoped
public class LancamentoCaixaVaroesComprasBean {
	
	private Lancamento lancamento=new Lancamento();
	private Compra compra;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	private List<Compra>listaCompras;
	private List<Compra>listaComprasLancamento;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			lancamento.setData(new Date());
			lancamento.setDespesa(new BigDecimal("0.00"));
			 CompraDao compraDao=new CompraDao();
			 listaCompras=compraDao.listar();
            listaComprasLancamento=new ArrayList<Compra>();
			
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

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public LancamentoDao getLancamentoDao() {
		return lancamentoDao;
	}

	public void setLancamentoDao(LancamentoDao lancamentoDao) {
		this.lancamentoDao = lancamentoDao;
	}

	public List<Compra> getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(List<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}

	public List<Compra> getListaComprasLancamento() {
		return listaComprasLancamento;
	}

	public void setListaComprasLancamento(List<Compra> listaComprasLancamento) {
		this.listaComprasLancamento = listaComprasLancamento;
	}
	
	
	//novo metodo
	
	public void calcular() {
		//venda.setPrecoTotal(new BigDecimal("0.00"));
        lancamento.setDespesa(new BigDecimal("0.00"));
		for (int posicao = 0; posicao < listaComprasLancamento.size(); posicao++) {
			Compra minhaCompra = listaComprasLancamento.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			lancamento.setDespesa(lancamento.getDespesa().add(minhaCompra.getPrecodacompra()));
		}
	}
		public void adicionarCompras(ActionEvent evento){
			compra=(Compra) evento.getComponent().getAttributes().get("compraSelecionada");
			listaComprasLancamento.add(compra);
			calcular();
			Utils.mostrarMensagemJsfSucesso("compra"+compra.getCodigo()+"adicionada na lista de compras do lan�amento");
				listaCompras.remove(compra);
			}
		
		public void salvarLancamentoDeCompras(){
			LancamentoDao ldao=new LancamentoDao();
			try{
			ldao.salvarLancamentoCompra(lancamento, listaComprasLancamento);
			Utils.mostrarMensagemJsfSucesso("lan�amento de compras realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lan�amento de compras");
				e.printStackTrace();
			}
			
		}
		
		
}
