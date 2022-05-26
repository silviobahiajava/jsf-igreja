package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.GrupoDao;
import daos.LancamentoDao;
import entidades.Grupo;
import entidades.Lancamento;
import enums.CaixaEnum;
import utils.Utils;

@ManagedBean(name="lancamentoescoladominicalBean")
@ViewScoped
public class LancamentoEscolaDominicalBean {
	private List<Grupo>grupos=new ArrayList<Grupo>();
	private GrupoDao gdao=new GrupoDao();
	private List<Lancamento> lancamentos=new ArrayList<Lancamento>();
	private LancamentoDao ldao=new LancamentoDao();
	private Lancamento lancamento=new Lancamento();
	
	private BigDecimal valorDespesa;
	private BigDecimal valorReceita;
	private BigDecimal valorSaldo;
	private boolean saldopositivo;
	private boolean saldonegativo;
	private Long codigo;
	
	private boolean caixaAberto;
	private boolean caixaFechado;
	private BigDecimal saldoInicial;
	private String statusCaixa;
	private boolean semSaldoInicial;
	
	
	
	
	
	

	
	@PostConstruct
	public void listar() {
		try {
			lancamento=new Lancamento();
			LancamentoDao ldao=new LancamentoDao();
			/*Grupo grupo=new Grupo();
			grupo.setCodigo(7L);*/
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarLancamentoPorGrupo(7L);
			
		   //lancamentos=ldao.buscarLancamentoPorGrupo(7L);
			//lancamentos=ldao.buscarPorGrupo(grupo.getCodigo());
			
			
			//lancamentos=ldao.listar();
			
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	
	
	public void novo() {
		try {
			lancamento = new Lancamento();
			
			grupos = gdao.listar();
			

		} catch (Exception e) {

		}

	}
	
	
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	
     public void fazerBalancoPorGrupo() {
    	    Grupo grupo=gdao.buscar(7L);
    		valorReceita=ldao.mostrarReceitaPorGrupo(grupo.getCodigo());
    		valorDespesa=ldao.mostrarDespesaPorGrupo(grupo.getCodigo());
    		valorSaldo=ldao.mostrarSaldoPorGrupo(grupo.getCodigo());
    		Utils.mostrarMensagemJsfSucesso("total das receitas "+valorReceita+"\ntotal das despesa "+valorDespesa+
    				"saldo "+valorSaldo);
    		if(valorDespesa.compareTo(valorReceita)==1) {
    			Utils.mostrarMensagemJsfSucesso("saldo negativo");
    		}else {
    			Utils.mostrarMensagemJsfSucesso("saldo positivo");
    		}
     }
     
     
     //FUNCIONA BALAN�O POR GRUPO
   /*  public void fazerBalancoDaIgreja() {
 	   
 		valorReceita=ldao.mostrarReceitaPorGrupo(7L);
 		valorDespesa=ldao.mostrarDespesaPorGrupo(7L);
 		valorSaldo=ldao.mostrarSaldoPorGrupo(7L);
 		Utils.mostrarMensagemJsfSucesso("total das receitas "+valorReceita+"\ntotal das despesa "+valorDespesa+
 				"saldo "+valorSaldo);
 		if(valorDespesa.compareTo(valorReceita)==1) {
 			saldopositivo=false;
 			saldonegativo=true;
 			Utils.mostrarMensagemJsfSucesso("saldo negativo");
 		}else {
 			Utils.mostrarMensagemJsfSucesso("saldo positivo");
 			saldopositivo=true;
 			saldonegativo=false;
 		}
  }*/
	
     
     //FUNCIONA BALAN�O GERAL
     public void fazerBalancoGeral() {
   	   
  		valorReceita=ldao.mostrarReceitaGeral();
  		valorDespesa=ldao.mostrarDespesaGeral();
  		valorSaldo=ldao.mostrarSaldoGeral();
  		Utils.mostrarMensagemJsfSucesso("total das receitas "+valorReceita+"\ntotal das despesa "+valorDespesa+
  				"saldo "+valorSaldo);
  		if(valorDespesa.compareTo(valorReceita)==1) {
  			saldopositivo=false;
  			saldonegativo=true;
  			Utils.mostrarMensagemJsfSucesso("saldo negativo");
  		}else {
  			Utils.mostrarMensagemJsfSucesso("saldo positivo");
  			saldopositivo=true;
  			saldonegativo=false;
  		}
   }
     
     
     //FUNCIONA BALAN�O GERAL
     public void fazerBalancoGeralEscolaDominical() {
   	   
  		valorReceita=ldao.mostrarReceitaPorGrupo(7L);
  		valorDespesa=ldao.mostrarDespesaPorGrupo(7L);
  		valorSaldo=ldao.mostrarSaldoPorGrupo(7L);
  		Utils.mostrarMensagemJsfSucesso("total das receitas "+valorReceita+"\ntotal das despesa "+valorDespesa+
  				"saldo "+valorSaldo);
  		if(valorDespesa.compareTo(valorReceita)==1) {
  			saldopositivo=false;
  			saldonegativo=true;
  			Utils.mostrarMensagemJsfSucesso("saldo negativo");
  		}else {
  			Utils.mostrarMensagemJsfSucesso("saldo positivo");
  			saldopositivo=true;
  			saldonegativo=false;
  		}
   }
     
     
	public void salvar() {
		
		try {
			LancamentoDao ldao=new LancamentoDao();
		Grupo grupo=new Grupo();
		grupo.setCodigo(7L);
		lancamento.setGrupo(grupo);
		lancamento.setData(lancamento.getData());
		lancamento.setDescricao(lancamento.getDescricao());
		lancamento.setDespesa(lancamento.getDespesa());
		lancamento.setReceita(lancamento.getReceita());
	
		
			ldao.salvar(lancamento);
			
			//lancamentos=ldao.buscarLancamentoPorGrupo(3L);
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarPorGrupo(1L);
			//Utils.mostrarMensagemJsfSucesso("lan�amento registrado com sucesso");
			
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
			//lancamentos=ldao.buscarLancamentoPorGrupo(7L);
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarPorGrupo(7L);
			Utils.mostrarMensagemJsfSucesso("lan�amento cancelado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel cancelar este lan�amento");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		lancamento = (Lancamento) evento.getComponent().getAttributes().get("lancamentoSelecionado");
		Grupo grupo=new Grupo();
		grupo.setCodigo(7L);
		lancamento.setGrupo(grupo);
		lancamentos=ldao.buscarPorGrupo(grupo.getCodigo());
	}
	
	

		
	
	

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


	public BigDecimal getValorDespesa() {
		return valorDespesa;
	}


	public void setValorDespesa(BigDecimal valorDespesa) {
		this.valorDespesa = valorDespesa;
	}


	public BigDecimal getValorReceita() {
		return valorReceita;
	}


	public void setValorReceita(BigDecimal valorReceita) {
		this.valorReceita = valorReceita;
	}


	public BigDecimal getValorSaldo() {
		return valorSaldo;
	}


	public void setValorSaldo(BigDecimal valorSaldo) {
		this.valorSaldo = valorSaldo;
	}


	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public boolean isSaldopositivo() {
		return saldopositivo;
	}


	public void setSaldopositivo(boolean saldopositivo) {
		this.saldopositivo = saldopositivo;
	}


	public boolean isSaldonegativo() {
		return saldonegativo;
	}


	public void setSaldonegativo(boolean saldonegativo) {
		this.saldonegativo = saldonegativo;
	}
	
	
	
	
	
	
	
	
	 
	
	/*public void registrarLancamento(){
		if(lancamento.getTipoDespesa().getDescricao().equals("COMPRA")){
		 Utils.mostrarMensagemJsfSucesso("voce escolheu compras");	
		 

		}else if(lancamento.getTipoDespesa().getDescricao().equals("PAGAMENTO DE AGUA")){
			Utils.mostrarMensagemJsfSucesso("voce escolheu pagamento de agua");
		}
		
		lancamentos=lancamentoDao.buscarLancamentoPorGrupo(7L);
	}*/
	
	
	
	public void abrirLivroCaixa(){
		
		Grupo g=new Grupo();
		g.setCodigo(7L);
			
			saldoInicial=ldao.mostrarSaldoAtualPorGrupo(7L,new Date());
			
			if(saldoInicial!=null){
				Utils.mostrarMensagemJsfSucesso("CAIXA ABERTO COM SALDO INICIAL DE  "+saldoInicial);
				caixaAberto=true;
			}
			if(saldoInicial==null){
				caixaAberto=true;
				Utils.mostrarMensagemJsfSucesso("CAIXA ABERTO ");
			}
		}
	
	public void fecharLivroCaixa(){
		statusCaixa="CAIXA FECHADO";
		Grupo g=new Grupo();
		g.setCodigo(7L);
		BigDecimal saldoFinal=ldao.mostrarSaldoAtualPorGrupo(7L,new Date());
		if(saldoInicial!=null){
			caixaFechado=true;
			Utils.mostrarMensagemJsfSucesso("CAIXA FECHADO COM SALDO  DE  "+saldoFinal);
			
		}
		if(saldoInicial==null){
			caixaFechado=true;
			Utils.mostrarMensagemJsfSucesso("CAIXA  FECHADO");
		}

		
	}


	public boolean isCaixaAberto() {
		return caixaAberto;
	}


	public void setCaixaAberto(boolean caixaAberto) {
		this.caixaAberto = caixaAberto;
	}


	public boolean isCaixaFechado() {
		return caixaFechado;
	}


	public void setCaixaFechado(boolean caixaFechado) {
		this.caixaFechado = caixaFechado;
	}


	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}


	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}


	public String getStatusCaixa() {
		return statusCaixa;
	}


	public void setStatusCaixa(String statusCaixa) {
		this.statusCaixa = statusCaixa;
	}

	
	
	
	 public boolean isSemSaldoInicial() {
		return semSaldoInicial;
	}


	public void setSemSaldoInicial(boolean semSaldoInicial) {
		this.semSaldoInicial = semSaldoInicial;
	}


	public void fazerBalancoGeralEBD() {
	  	   
	 		valorReceita=ldao.mostrarReceitaPorGrupo(7L);
	 		valorDespesa=ldao.mostrarDespesaPorGrupo(7L);
	 		valorSaldo=ldao.mostrarSaldoPorGrupo(7L);
	 		Utils.mostrarMensagemJsfSucesso("total das receitas "+valorReceita+"\ntotal das despesa "+valorDespesa+
	 				"saldo "+valorSaldo);
	 		if(valorDespesa.compareTo(valorReceita)==1) {
	 			saldopositivo=false;
	 			saldonegativo=true;
	 			Utils.mostrarMensagemJsfSucesso("saldo negativo");
	 		}else {
	 			Utils.mostrarMensagemJsfSucesso("saldo positivo");
	 			saldopositivo=true;
	 			saldonegativo=false;
	 		}
	  }

	 
	public void registrarSaldoInicial(){
		if(saldoInicial==null){
			
			lancamento.setSaldoInicial(lancamento.getSaldoInicial());
			lancamento.setSaldo(lancamento.getSaldoInicial());
			lancamento.setReceita(lancamento.getSaldoInicial());
			lancamento.setDespesa(new BigDecimal("0.00"));
			lancamento.setData(lancamento.getData());
			lancamento.setHistorico("saldo inicial");
			
			
			lancamento.setDescricao("ABERTURA DE CAIXA");
			Grupo grupo=new Grupo();
			grupo.setCodigo(7L);
			lancamento.setGrupo(grupo);
			lancamento.setCaixaAberto(true);
			lancamento.setCaixaEnum(CaixaEnum.ABERTO);
			
			
			
		}if(saldoInicial!=null){
			lancamento.setSaldoInicial(lancamento.getSaldoInicial());
			lancamento.setSaldo(lancamento.getSaldoInicial());
			lancamento.setReceita(lancamento.getSaldoInicial());
			lancamento.setDespesa(new BigDecimal("0.00"));
			lancamento.setData(lancamento.getData());
			lancamento.setHistorico("saldo inicial");
			
			
			lancamento.setDescricao("ABERTURA DE CAIXA");
			Grupo grupo=new Grupo();
			grupo.setCodigo(7L);
			lancamento.setGrupo(grupo);
			lancamento.setCaixaAberto(true);
			lancamento.setCaixaEnum(CaixaEnum.ABERTO);
		}
		
		ldao.abrirCaixa(lancamento);
		Utils.mostrarMensagemJsfSucesso("caixa aberto pela primeira vez com saldo inicial de  "+lancamento.getSaldoInicial());
	}
	

	
}
