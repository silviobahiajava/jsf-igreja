package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.GrupoDao;
import daos.LancamentoDao;
import entidades.Compra;
import entidades.Dizimo;
import entidades.Grupo;
import entidades.Lancamento;

@ManagedBean(name="caixaGrupoBean")
@ViewScoped
public class CaixaGrupoBean {
	private String nomecliente;
	private String livrocaixa;
	private List<Lancamento>lista=new ArrayList<Lancamento>();
	private Lancamento lancamento=new Lancamento();
	private Compra compra;
	private Dizimo dizimo;
	private List<Dizimo>dizimos;
	private List<Grupo>grupos;
	public List<Dizimo> getDizimos() {
		return dizimos;
	}

	public void setDizimos(List<Dizimo> dizimos) {
		this.dizimos = dizimos;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}





	private GrupoDao gdao=new GrupoDao();
	private BigDecimal saldo;
	private BigDecimal saldoGrupo;

	public String getNomecliente() {
		return nomecliente;
	}

	public void setNomecliente(String nomecliente) {
		this.nomecliente = nomecliente;
	}
	
	@PostConstruct
	public void init(){
		Map<String, String> meumapa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		nomecliente=meumapa.get("namekey");
		int numero=Integer.parseInt(nomecliente);
		switch(numero){
		case 1:
			livrocaixa="Livro Caixa do Grupo de Var�es";
			break;
		case 2:
			livrocaixa="Livro Caixa do C�rculo de Ora��o";
			break;
		case 3:
			livrocaixa="Livro Caixa do Grupo de Crian�as";
			break;
		case 4:
			livrocaixa="Livro Caixa do Grupo de Adolescentes";
			break;
		case 5:
			livrocaixa="Livro Caixa da Mocidade";
			break;
		case 6:
			livrocaixa="Livro Caixa do Grupo de Coreografia Adulto";
			break;
		case 7:
			livrocaixa="Livro Caixa da igreja";
			break;
			default:
				livrocaixa="indefinido";
		}
		
		listarLancamento();
	}
	public void listarLancamento(){
		LancamentoDao ldao=new LancamentoDao();
		
		lista=ldao.buscarLancamentoPorCodigo(Long.parseLong(nomecliente));
		
		
	}

	public List<Lancamento> getLista() {
		return lista;
	}

	public void setLista(List<Lancamento> lista) {
		this.lista = lista;
	}

	public String getLivrocaixa() {
		return livrocaixa;
	}

	public void setLivrocaixa(String livrocaixa) {
		this.livrocaixa = livrocaixa;
	}
	//NOVOS METODOS
	public void novo(){
		try{
			lancamento=new Lancamento();
			compra=new Compra();
			dizimo=new Dizimo();
			grupos=gdao.listar();
		
		}catch(Exception e){
			
		}
		
		}
public void salvar() {
		
		try {
			LancamentoDao ldao=new LancamentoDao();
			ldao.merge(lancamento);
			
		   
			lista=ldao.listar();
			calcularSaldo();
		//	calcularSaldoPorGrupo();
			
			
			
			
			Utils.mostrarMensagemJsfSucesso("lan�amento registrado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao tentar registrar este lan�amento");
			erro.printStackTrace();
		}
	}
  
//public void calcularSaldoPorGrupo(){
//	LancamentoDao ldao=new LancamentoDao();
//	saldoGrupo=ldao.buscarSaldoPorGrupo(lancamento.getGrupo().getNome());
//	Utils.mostrarMensagemJsfSucesso("saldo do "+lancamento.getGrupo().getNome()+": "+saldoGrupo);
//}
	
	
	public void excluir(ActionEvent evento) {
		try {
			lancamento = (Lancamento) evento.getComponent().getAttributes().get("lancamentoSelecionado");

			
			LancamentoDao ldao=new LancamentoDao();
			ldao.excluir(lancamento);
			lista=ldao.listar();
			calcularSaldo();
			//calcularSaldoPorGrupo();

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
		LancamentoDao ldao=new LancamentoDao();
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
		lista=ldao.listar();
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

		calcular();*/
	
	
	public void calcularSaldo(){
		LancamentoDao ldao=new LancamentoDao();
		saldo=ldao.buscarSaldo();
	}
}
