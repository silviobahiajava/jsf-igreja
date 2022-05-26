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

@ManagedBean(name="lancamentomissaoBean")
@ViewScoped
public class LancamentoMissaoBean {
	private Lancamento lancamento=new Lancamento();
	private List<Grupo>grupos=new ArrayList<Grupo>();
	private GrupoDao gdao=new GrupoDao();
	private List<Lancamento> lancamentos=new ArrayList<Lancamento>();
	private LancamentoDao ldao=new LancamentoDao();
	
	private BigDecimal valorDespesa;
	private BigDecimal valorReceita;
	private BigDecimal valorSaldo;
	private boolean saldopositivo;
	private boolean saldonegativo;
	private Long codigo;
	private BigDecimal saldoInicial;
	private boolean  semSaldoInicial;
	
	

	public void abrirCaixa() {
		lancamento=new Lancamento();
		valorSaldo=ldao.mostrarSaldoPorGrupo(4L);
		
		if(valorSaldo.equals(null)){
		 Utils.mostrarMensagemJsfAlerta("Caixa zerado.Insira um valor \npra inicial a movimentação financeira ");
		 semSaldoInicial=true;
		}if(valorSaldo!=null){
			lancamento.setSaldo(valorSaldo);
			lancamento.setSaldoInicial(valorSaldo);
		}
		if(semSaldoInicial==true && saldoInicial!=null){
			lancamento.setSaldoInicial(saldoInicial);
		}
		if(semSaldoInicial==true && saldoInicial==null){
			Utils.mostrarMensagemJsfAlerta("Caixa zerado.Insira um valor \npra inicial a movimentação financeira ");
			
		}
		Grupo grupo=new Grupo();
		grupo.setCodigo(4L);
		lancamento.setCaixaEnum(CaixaEnum.ABERTO);
		lancamento.setData(new Date());
		lancamento.setGrupo(grupo);
		
		
		ldao.abrirCaixa(lancamento);
		Utils.mostrarMensagemJsfSucesso("caixa aberto com saldo de  "+valorSaldo);
	}
	
	
	//FECHA O CAIXA
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
					grupo.setCodigo(4L);
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
					grupo.setCodigo(4L);
					lancamento.setGrupo(grupo);
					lancamento.setCaixaAberto(true);
					lancamento.setCaixaEnum(CaixaEnum.ABERTO);
				}
				
				ldao.abrirCaixa(lancamento);
				Utils.mostrarMensagemJsfSucesso("caixa aberto pela primeira vez com saldo inicial de  "+lancamento.getSaldoInicial());
			}
			

	
	
	
	
	
	

	
	@PostConstruct
	public void listar() {
		try {
			lancamento=new Lancamento();
			LancamentoDao ldao=new LancamentoDao();
			/*Grupo grupo=new Grupo();
			grupo.setCodigo(8L);*/
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarLancamentoPorGrupo(4L);
		//	lancamentos=ldao.buscarLancamentoPorGrupo(4L);
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
    	    Grupo grupo=gdao.buscar(4L);
    		valorReceita=ldao.mostrarReceitaPorGrupo(grupo.getCodigo());
    		valorDespesa=ldao.mostrarDespesaPorGrupo(grupo.getCodigo());
    		valorSaldo=ldao.mostrarSaldoPorGrupo(grupo.getCodigo());
    		Utils.mostrarMensagemJsfSucesso("receitas:"+valorReceita+"\ndespesa "+valorDespesa+"saldo :"+valorSaldo);
    				
    		if(valorDespesa.compareTo(valorReceita)==1) {
    			Utils.mostrarMensagemJsfSucesso("saldo negativo");
    		}else {
    			Utils.mostrarMensagemJsfSucesso("saldo positivo");
    		}
     }
     
     
     //FUNCIONA BALANÇO POR GRUPO
   /*  public void fazerBalancoDaIgreja() {
 	   
 		valorReceita=ldao.mostrarReceitaPorGrupo(8L);
 		valorDespesa=ldao.mostrarDespesaPorGrupo(8L);
 		valorSaldo=ldao.mostrarSaldoPorGrupo(8L);
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
	
     
     //FUNCIONA BALANÇO GERAL
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
     
     
	public void salvar() {
		
		try {
			LancamentoDao ldao=new LancamentoDao();
		Grupo grupo=new Grupo();
		grupo.setCodigo(4L);
		lancamento.setGrupo(grupo);
		lancamento.setData(lancamento.getData());
		lancamento.setDescricao(lancamento.getDescricao());
		lancamento.setDespesa(lancamento.getDespesa());
		lancamento.setReceita(lancamento.getReceita());
	
		
			ldao.merge(lancamento);
			
			//lancamentos=ldao.buscarLancamentoPorGrupo(3L);
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarPorGrupo(4L);
			//Utils.mostrarMensagemJsfSucesso("lançamento registrado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao tentar registrar este lançamento");
			erro.printStackTrace();
		}
	}

	
	
	public void excluir(ActionEvent evento) {
		try {
			lancamento = (Lancamento) evento.getComponent().getAttributes().get("lancamentoSelecionado");
			LancamentoDao ldao=new LancamentoDao();
			
			ldao.excluir(lancamento);
			//lancamentos=ldao.buscarLancamentoPorGrupo(8L);
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarPorGrupo(1L);
			Utils.mostrarMensagemJsfSucesso("lançamento cancelado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("não foi possível cancelar este lançamento");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		lancamento = (Lancamento) evento.getComponent().getAttributes().get("lancamentoSelecionado");
		Grupo grupo=new Grupo();
		grupo.setCodigo(4L);
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


	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}


	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}


	public boolean isSemSaldoInicial() {
		return semSaldoInicial;
	}


	public void setSemSaldoInicial(boolean semSaldoInicial) {
		this.semSaldoInicial = semSaldoInicial;
	}
	
	
	
	
	
	
	
	
	 
	
	/*public void registrarLancamento(){
		if(lancamento.getTipoDespesa().getDescricao().equals("COMPRA")){
		 Utils.mostrarMensagemJsfSucesso("voce escolheu compras");	
		 

		}else if(lancamento.getTipoDespesa().getDescricao().equals("PAGAMENTO DE AGUA")){
			Utils.mostrarMensagemJsfSucesso("voce escolheu pagamento de agua");
		}
		
		lancamentos=lancamentoDao.buscarLancamentoPorGrupo(8L);
	}*/
	
	
	
}
