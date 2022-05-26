package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CompraDao;
import daos.GrupoDao;
import daos.LancamentoDao;
import entidades.Compra;
import entidades.Dizimo;
import entidades.Grupo;
import entidades.Lancamento;

@ManagedBean(name="lancamentoCaixaSenhorasBean")
@ViewScoped
public class LancamentoCaixaSenhorasBean {
	private Lancamento lancamento=new Lancamento();
	private List<Grupo>grupos=new ArrayList<Grupo>();
	private GrupoDao gdao=new GrupoDao();
	private List<Lancamento> lancamentos=new ArrayList<Lancamento>();
	private BigDecimal valordadespesa;
	private List<BigDecimal>listaValorDespesa;
	private BigDecimal saldo;
	private BigDecimal saldoGrupo;
	//private String nomeGrupo;
	private CompraDao compraDao=new CompraDao();
	private Dizimo dizimo;
	private Compra compra;
	private LancamentoDao lancamentoDao=new LancamentoDao();
//	private List<Compra>listaCompras;
//	private List<Compra>listaComprasLancamento;
	
	@PostConstruct
	public void listar() {
		try {
			lancamento=new Lancamento();
			LancamentoDao ldao=new LancamentoDao();
			
			//lancamentos=ldao.listar();
			//lancamentos=ldao.buscarLancamentoPorGrupo("var�es");
			lancamentos=ldao.buscarLancamentoPorGrupo(2L);
			
			
			
			
			
			
			
			
			
			//listaCompras=compraDao.listar();
			
			//calcularSaldo();
			
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	public void novo(){
	try{
		lancamento=new Lancamento();
		compra=new Compra();
		dizimo=new Dizimo();
		grupos=gdao.listar();
	
	}catch(Exception e){
		
	}
	
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	public void calcularSaldo(){
		LancamentoDao ldao=new LancamentoDao();
		saldo=ldao.buscarSaldo();
	}
	public void calcularSaldoPorGrupo(){
		LancamentoDao ldao=new LancamentoDao();
		saldoGrupo=ldao.buscarSaldoPorGrupo(lancamento.getGrupo().getNome());
		Utils.mostrarMensagemJsfSucesso("saldo do "+lancamento.getGrupo().getNome()+": "+saldoGrupo);
	}
	
	public void salvar() {
		
		try {
			LancamentoDao ldao=new LancamentoDao();
			Grupo grupopesquisado=gdao.buscar(2L);
			lancamento.setGrupo(grupopesquisado);
			ldao.merge(lancamento);
			
		   
			lancamentos=ldao.listar();
			calcularSaldo();
			calcularSaldoPorGrupo();
			
			
			
			
			Utils.mostrarMensagemJsfSucesso("lan�amento registrado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao tentar registrar este lan�amento");
			erro.printStackTrace();
		}
	}

	
	
	public void excluir(ActionEvent evento) {
		try {
			lancamento = (Lancamento) evento.getComponent().getAttributes().get("lancamentoSelecionado");

			
			LancamentoDao ldao=new LancamentoDao();
			ldao.excluir(lancamento);
			lancamentos=ldao.listar();
			calcularSaldo();
			calcularSaldoPorGrupo();

			Utils.mostrarMensagemJsfSucesso("lan�amento cancelado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel cancelar este lan�amento");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		lancamento = (Lancamento) evento.getComponent().getAttributes().get("lancamentoSelecionado");
		
		
	}
	
	
/*	public void popular(){
		
		//if(aula.getClasse()!=null){
			LancamentoDao ldao=new LancamentoDao();
			lancamentos=ldao.buscarPorGrupo(lancamento.getGrupo().getCodigo());
//			GrupoDao gdao=new GrupoDao();
//			Grupo g=gdao.buscar(lancamento.getGrupo().getCodigo());
//			
//			Utils.mostrarMensagemJsfSucesso("grupo encontrado "+g.getNome());
			
			//Utils.mostrarMensagemSwing("classe selecionada "+aula.getClasse().getNomeClasse());
			calcularSaldoPorGrupo();
			
		
			
		//}
		
	
	
}*/
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public BigDecimal getSaldoGrupo() {
		return saldoGrupo;
	}
	public void setSaldoGrupo(BigDecimal saldoGrupo) {
		this.saldoGrupo = saldoGrupo;
	}
	public Dizimo getDizimo() {
		return dizimo;
	}
	public void setDizimo(Dizimo dizimo) {
		this.dizimo = dizimo;
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	
	/*public List<Compra> getListaCompras() {
		return listaCompras;
	}
	public void setListaCompras(List<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}*/
	//novo metodo
	/*
	 * data tipomovimentacao tipodespesa tiporeceita descricao receita despesa
	 * 
	 * 
	 */
	
	public void registrarLancamento(){
		if(lancamento.getTipoDespesa().getDescricao().equals("COMPRA")){
		 Utils.mostrarMensagemJsfSucesso("voce escolheu compras");	
		 
//		 valordadespesa=compraDao.buscarValorDaCompraPorData(compra.getDataCompra());
//		 listaValorDespesa=compraDao.buscarValorDaCompraPorDataUnitario(compra.getDataCompra());
//		 Utils.mostrarMensagemJsfSucesso("valor de compras nesta data "+listaValorDespesa);
//		 Utils.mostrarMensagemJsfSucesso("valor desta compra "+valordadespesa);
//		 lancamento.setData(lancamento.getData());
//		// lancamento.setGrupo(grupo);
//		 lancamento.setTipo(TipoMovimentacao.DESPESA);
//		 lancamento.setTipoDespesa(TipoDespesa.COMPRA);
//		 lancamento.setDescricao(lancamento.getDescricao());
//		 lancamento.setDespesa(valordadespesa);
//		 salvarLancamentoDeCompras();
//		 lancamentoDao.merge(lancamento);
//		 Utils.mostrarMensagemJsfSucesso("lancamento de compra realizado com sucesso");
		//adicionarCompras();
		}else if(lancamento.getTipoDespesa().getDescricao().equals("PAGAMENTO DE AGUA")){
			Utils.mostrarMensagemJsfSucesso("voce escolheu pagamento de agua");
		}
		lancamentos=lancamentoDao.listar();
	}
	public BigDecimal getValordadespesa() {
		return valordadespesa;
	}
	public void setValordadespesa(BigDecimal valordadespesa) {
		this.valordadespesa = valordadespesa;
	}
	public List<BigDecimal> getListaValorDespesa() {
		return listaValorDespesa;
	}
	public void setListaValorDespesa(List<BigDecimal> listaValorDespesa) {
		this.listaValorDespesa = listaValorDespesa;
	}
	
	
	

	/*public void adicionar(ActionEvent evento) {
		Compra compraParaLancamento = (Compra) evento.getComponent().getAttributes().get("compraSelecionada");

		int achou = -1;
		for (int posicao = 0; posicao < listaComprasLancamento.size(); posicao++) {
			if (listaComprasLancamento.get(posicao).g {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setPrecoParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}

		calcular();
	}
	*/
}
