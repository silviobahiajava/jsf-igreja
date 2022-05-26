package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

//import daos.CompraDao;
import daos.GrupoDao;
import daos.LancamentoDao;
import entidades.Grupo;
import entidades.Lancamento;
import enums.CaixaEnum;
import filtros.LancamentoIgrejaFilter;
import utils.Utils;

@ManagedBean(name="lancamentoBean2")
@ViewScoped
public class LancamentoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Lancamento lancamento=new Lancamento();
	private List<Grupo>grupos=new ArrayList<Grupo>();
	private GrupoDao gdao=new GrupoDao();
	private List<Lancamento> lancamentos=new ArrayList<Lancamento>();
	private List<Lancamento> lancamentos2=new ArrayList<Lancamento>();
	private LancamentoDao ldao=new LancamentoDao();
	private boolean isReceita;
	private boolean isDespesa;
	private BigDecimal valorDespesa;
	private BigDecimal valorReceita;

	
	private BigDecimal valorSaldo;
	private boolean saldopositivo;
	private boolean saldonegativo;
	private BigDecimal saldoInicial;
	private BigDecimal saldoFinal;
	private Long codigo;
	private Lancamento teste;
	private String statusCaixa;
	private boolean caixaAberto;
	private boolean caixaFechado;
	private boolean semSaldoInicial;
	private BigDecimal receitaMensal;
	private BigDecimal despesaMensal;
	private BigDecimal saldoMensal;
	private BigDecimal saldoHaverProximoMes;
	private BigDecimal saldoMesAnterior;
	
	
	private boolean temSaldoInicial=false;//oç
	private boolean naoTemSaldoInicial=true; //ok
	
	private BigDecimal receitaFinal;
	private BigDecimal despesaFinal;
	
	private BigDecimal saldoIncial;
	private BigDecimal receitaInicial;
	private BigDecimal despesaInicial;
	
	//metodo pra abrir e fechar o caixa
	//metodo pra abrir e fechar o caixa
	
		public void abrirCaixa() {
			lancamento=new Lancamento();
			valorSaldo=ldao.mostrarSaldoPorGrupo(8L);
			if(valorSaldo.equals(null)){
			 Utils.mostrarMensagemJsfAlerta("Caixa zerado.Insira um valor \npra inicial a movimenta��o financeira ");
			 semSaldoInicial=true;
			}if(valorSaldo!=null){
				lancamento.setSaldo(valorSaldo);
				lancamento.setSaldoInicial(valorSaldo);
			}
			if(semSaldoInicial==true && saldoInicial!=null){
				lancamento.setSaldoInicial(saldoInicial);
			}
			if(semSaldoInicial==true && saldoInicial==null){
				Utils.mostrarMensagemJsfAlerta("Caixa zerado.Insira um valor \npra inicial a movimenta��o financeira ");
				
			}
			Grupo grupo=new Grupo();
			grupo.setCodigo(8L);
			lancamento.setCaixaEnum(CaixaEnum.ABERTO);
			lancamento.setData(new Date());
			lancamento.setGrupo(grupo);
			
			
			ldao.abrirCaixa(lancamento);
			Utils.mostrarMensagemJsfSucesso("caixa aberto com saldo de  "+valorSaldo);
		}
		
		
		
	//ABRE O CAIXA	
		
		
		
		
		//FECHA O CAIXA
		public void fecharCaixa() {
			lancamento=new Lancamento();
			valorSaldo=mostrarSaldoMensalDoGrupo();
			receitaMensal=mostrarReceitaMensalDoGrupo();
			despesaMensal=mostrarDespesaMensalDoGrupo();
			
			saldoHaverProximoMes=valorSaldo;
			
			Grupo grupo=new Grupo();
			grupo.setCodigo(8L);
			lancamento.setCaixaEnum(CaixaEnum.FECHADO);
			lancamento.setSaldoFinal(valorSaldo);
			lancamento.setSaldo(valorSaldo);
			lancamento.setSaldoInicial(valorSaldo);
			lancamento.setHistorico("caixa fechado com "+valorSaldo);
			lancamento.setData(new Date());
			lancamento.setGrupo(grupo);
			
			
			ldao.fecharCaixa(lancamento);
			statusCaixa="caixa fechado --ABRIR CAIXA";
			caixaFechado=true;
			caixaAberto=false;
			Utils.mostrarMensagemJsfSucesso("caixa fechado com saldo de  "+valorSaldo);
		}
		
		//FECHA O CAIXA
		public void registrarSaldoInicial(){
			BigDecimal saldoInicial=ldao.mostrarSaldoAtualPorGrupo(8L,new Date());
			BigDecimal receitaFinal=ldao.mostrarReceitaAtualPorGrupo(8L,new Date());
			BigDecimal despesaFinal=ldao.mostrarDespesaAtualPorGrupo(8L,new Date());
			
			if(saldoInicial==null && !temSaldoInicial){
				
				lancamento.setSaldoInicial(lancamento.getSaldoInicial());
				lancamento.setSaldo(lancamento.getSaldoInicial());
				lancamento.setReceita(lancamento.getSaldoInicial());
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setData(lancamento.getData());
				lancamento.setHistorico("saldo inicial");
				
				
				lancamento.setDescricao("ABERTURA DE CAIXA");
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				lancamento.setCaixaAberto(true);
				lancamento.setCaixaEnum(CaixaEnum.ABERTO);
				temSaldoInicial=true;
				ldao.abrirCaixa(lancamento);
				
				
				
			}if(saldoInicial!=null && temSaldoInicial){
				lancamento.setSaldoInicial(lancamento.getSaldoInicial());
				lancamento.setSaldo(lancamento.getSaldoInicial());
				lancamento.setReceita(lancamento.getSaldoInicial());
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setData(lancamento.getData());
				lancamento.setHistorico("saldo inicial");
				
				
				lancamento.setDescricao("ABERTURA DE CAIXA");
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				lancamento.setCaixaAberto(true);
				lancamento.setCaixaEnum(CaixaEnum.ABERTO);
				temSaldoInicial=false;
				ldao.abrirCaixa(lancamento);
			}
			
			
			Utils.mostrarMensagemJsfSucesso("caixa aberto    "+lancamento.getSaldoInicial());
		}
		public void verificarSaldoInicial(){
			Grupo g=new Grupo();
			g.setCodigo(8L);
			
			 saldoFinal=ldao.mostrarSaldoAtualPorGrupo(8L,new Date());
			 receitaFinal=ldao.mostrarReceitaAtualPorGrupo(8L,new Date());
			 despesaFinal=ldao.mostrarDespesaAtualPorGrupo(8L,new Date());
			if(saldoFinal!=null){
				temSaldoInicial=true;
			}if(saldoFinal==null){

					naoTemSaldoInicial=true;
				}
		}

		public void abrirLivroCaixa(){
			
			Grupo g=new Grupo();
			g.setCodigo(8L);
				
				saldoInicial=ldao.mostrarSaldoAtualPorGrupo(8L,new Date());
				
				if(saldoInicial!=null){
					Utils.mostrarMensagemJsfSucesso("CAIXA ABERTO COM SALDO INICIAL DE  "+saldoInicial);
					caixaAberto=true;
				}
				if(saldoInicial==null){
					caixaAberto=true;
					Utils.mostrarMensagemJsfSucesso("CAIXA ABERTO ");
				}
			}
		 
		public void abrirLivroCaixaPelaPrimeiraVez(){
			Grupo g=new Grupo();
			g.setCodigo(8L);
			naoTemSaldoInicial=true;
			
			lancamento.setSaldo(lancamento.getSaldoInicial());
			lancamento.setSaldoInicial(lancamento.getSaldoInicial());
			lancamento.setReceita(lancamento.getSaldoInicial());
			lancamento.setDespesa(new BigDecimal("0.00"));

			lancamento.setCaixaEnum(CaixaEnum.ABERTURAINICIAL);
			lancamento.setData(new Date());
			lancamento.setDescricao("ABERTURA INCIAL");
			lancamento.setHistorico("ABERTURA INICIAL");
			lancamento.setGrupo(g);
			lancamento.setSaldoInicial(saldoInicial);
			ldao.abrirCaixa(lancamento);
			temSaldoInicial=true;
			naoTemSaldoInicial=false;
			Utils.mostrarMensagemJsfSucesso("ABERTURA INICIAL DE  "+lancamento.getSaldoInicial());
				
			}
		
		
		
		public void fecharLivroCaixaPelaPrimeiraVez(){
			Grupo g=new Grupo();
			g.setCodigo(8L);
			
			 saldoFinal=ldao.mostrarSaldoAtualPorGrupo(8L,new Date());
			 receitaFinal=ldao.mostrarReceitaAtualPorGrupo(8L,new Date());
			 despesaFinal=ldao.mostrarDespesaAtualPorGrupo(8L,new Date());
			
			
			lancamento.setSaldo(saldoFinal);
			lancamento.setSaldoFinal(saldoFinal);
			//lancamento.setSaldoInicial(lancamento.getSaldo());
			lancamento.setReceita(receitaFinal);
			lancamento.setDespesa(despesaFinal);

			lancamento.setCaixaEnum(CaixaEnum.FECHADO);
			lancamento.setData(new Date());
			lancamento.setDescricao("CAIXA FECHADO");
			lancamento.setHistorico("CAIXA FECHADO");
			lancamento.setGrupo(g);
			ldao.fecharCaixa(lancamento);
			
			Utils.mostrarMensagemJsfSucesso("CAIXA FECHADO COM   "+lancamento.getSaldoFinal());
				
			}
		
		
		
		
		
		/*public void registrarSaldoInicial(){
			if(saldoInicial==null){
				Utils.mostrarMensagemJsfAlerta("digite o saldo inicial pra abrir o caixa");
			}if(saldoInicial!=null){
				lancamento.setSaldoInicial(saldoInicial);
				lancamento.setSaldo(saldoInicial);
				lancamento.setData(new Date());
				lancamento.setHistorico("saldo inicial");
				
				lancamento.setReceita(valorSaldo);
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setDescricao("saldo inicial");
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				lancamento.setCaixaAberto(true);
				lancamento.setCaixaEnum(CaixaEnum.ABERTO);
				ldao.abrirCaixa(lancamento);
				Utils.mostrarMensagemJsfSucesso("caixa aberto com "+lancamento.getSaldoInicial());
				
			}
		
		}*/
		
		
	
	/*public void fecharCaixa() {
		Grupo grupo=new Grupo();
		grupo.setCodigo(8L);
		valorSaldo=ldao.mostrarSaldoPorGrupo(8L);
		lancamento.setCaixaEnum(CaixaEnum.FECHADO);
		lancamento.setData(lancamento.getData());
		lancamento.setGrupo(grupo);
		lancamento.setSaldoFinal(valorSaldo);
		ldao.fecharCaixa(lancamento);
		Utils.mostrarMensagemJsfSucesso("caixa fechado com o saldo "+valorSaldo);
	}*/
	
	public void zerarCaixa(){
		Grupo grupo=new Grupo();
		grupo.setCodigo(8L);
		lancamento.setGrupo(grupo);
		lancamento.setCaixaEnum(CaixaEnum.FECHADO);
		lancamento.setSaldo(null);
		lancamento.setSaldoInicial(null);
		lancamento.setReceita(null);
		lancamento.setDespesa(null);
		ldao.salvar(lancamento);
	}
	
	

	
	@PostConstruct
	public void listar() {
		try {
			lancamento=new Lancamento();
			LancamentoDao ldao=new LancamentoDao();
			/*Grupo grupo=new Grupo();
			grupo.setCodigo(8L);*/
			//lancamentos=ldao.listar();
			///Utils.mostrarMensagemJsfSucesso("caixa aberto com o saldo inicial de "+valorSaldo);
			lancamentos=ldao.buscarLancamentoPorGrupo(8L);
			
			/*for(Lancamento l:lancamentos) {
				l.setReceita(l.getReceita());
				l.setDespesa(l.getDespesa());
				l.setSaldo(l.getSaldo().add(l.getReceita().subtract(getValorDespesa())));
				lancamentos2.add(l);
			}*/
		//	lancamentos=ldao.buscarLancamentoPorGrupo(2L);
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
	
	//BALAN�O GERAL DO GRUPO
	 public void fazerBalancoPorGrupo() {
 	    Grupo grupo=gdao.buscar(8L);
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
	
	 
	
	     
	 
	 public BigDecimal mostrarReceitaMensalDoGrupo(){
			LancamentoIgrejaFilter filter=new LancamentoIgrejaFilter();
			/*SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
			filter.setDataInical(formato.parse("08/06/2017"));
			filter.setDataFinal(formato.parse("19/06/2017"));*/
			Date primeiroDiaDoMes=Utils.pegarPrimeiroDiaDoMes();
			GregorianCalendar gc=new GregorianCalendar();
			gc.setTime(primeiroDiaDoMes);
			int dia1 = gc.get(Calendar.MONTH)-4;
			
			Date ultimoDiaDoMes=Utils.pegarUltimoDiaDoMes();
			GregorianCalendar gc2=new GregorianCalendar();
			gc.setTime(ultimoDiaDoMes);
			int dia2 = gc2.get(Calendar.MONTH)+25;
			
			filter.setDataInical(primeiroDiaDoMes);
			filter.setDataFinal(ultimoDiaDoMes);
			
			
			LancamentoDao dao=new LancamentoDao();
			try{
			 saldoMensal=dao.mostrarReceitaMensalAPorGrupo(2L, filter);
			 return saldoMensal;
			 //System.out.println("saldo do mes "+saldo);
			
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("erro ao buscar "+e.getMessage());
			}
			return saldoInicial;
		}
		

	 
	 
	 public BigDecimal mostrarDespesaMensalDoGrupo(){
			LancamentoIgrejaFilter filter=new LancamentoIgrejaFilter();
			/*SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
			filter.setDataInical(formato.parse("08/06/2017"));
			filter.setDataFinal(formato.parse("19/06/2017"));*/
			Date primeiroDiaDoMes=Utils.pegarPrimeiroDiaDoMes();
			GregorianCalendar gc=new GregorianCalendar();
			gc.setTime(primeiroDiaDoMes);
			int dia1 = gc.get(Calendar.MONTH)-4;
			
			Date ultimoDiaDoMes=Utils.pegarUltimoDiaDoMes();
			GregorianCalendar gc2=new GregorianCalendar();
			gc.setTime(ultimoDiaDoMes);
			int dia2 = gc2.get(Calendar.MONTH)+25;
			
			filter.setDataInical(primeiroDiaDoMes);
			filter.setDataFinal(ultimoDiaDoMes);
			
			
			LancamentoDao dao=new LancamentoDao();
			try{
			
			
			 saldoMensal=dao.mostrarDespesaMensalAPorGrupo(2L, filter);
			 return saldoMensal;
			 //System.out.println("saldo do mes "+saldo);
			
			
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("erro ao buscar "+e.getMessage());
			}
			return saldoInicial;
		}

	 
	 
	 public BigDecimal mostrarSaldoMensalDoGrupo(){
			LancamentoIgrejaFilter filter=new LancamentoIgrejaFilter();
			/*SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
			filter.setDataInical(formato.parse("08/06/2017"));
			filter.setDataFinal(formato.parse("19/06/2017"));*/
			Date primeiroDiaDoMes=Utils.pegarPrimeiroDiaDoMes();
			GregorianCalendar gc=new GregorianCalendar();
			gc.setTime(primeiroDiaDoMes);
			int dia1 = gc.get(Calendar.MONTH)-4;
			
			Date ultimoDiaDoMes=Utils.pegarUltimoDiaDoMes();
			GregorianCalendar gc2=new GregorianCalendar();
			gc.setTime(ultimoDiaDoMes);
			int dia2 = gc2.get(Calendar.MONTH)+25;
			
			filter.setDataInical(primeiroDiaDoMes);
			filter.setDataFinal(ultimoDiaDoMes);
			
			
			LancamentoDao dao=new LancamentoDao();
			try{
			
			
			 saldoMensal=dao.mostrarSaldoMensalAPorGrupo(8L,filter);
			 return saldoMensal;
			 //System.out.println("saldo do mes "+saldo);
			
			
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("erro ao buscar "+e.getMessage());
			}
			return saldoInicial;
		}
	 
	
	
	 
		public void buscarLancamentoMensalPorGrupo() {
			LancamentoIgrejaFilter filter=new LancamentoIgrejaFilter();
			/*SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
			filter.setDataInical(formato.parse("08/06/2017"));
			filter.setDataFinal(formato.parse("19/06/2017"));*/
			Date primeiroDiaDoMes=Utils.pegarPrimeiroDiaDoMes();
			GregorianCalendar gc=new GregorianCalendar();
			gc.setTime(primeiroDiaDoMes);
			int dia1 = gc.get(Calendar.MONTH)-4;
			
			Date ultimoDiaDoMes=Utils.pegarUltimoDiaDoMes();
			GregorianCalendar gc2=new GregorianCalendar();
			gc.setTime(ultimoDiaDoMes);
			int dia2 = gc2.get(Calendar.MONTH)+25;
			
			filter.setDataInical(primeiroDiaDoMes);
			filter.setDataFinal(ultimoDiaDoMes);
			
			
			LancamentoDao dao=new LancamentoDao();
			try{
			
			
			 saldoMensal=dao.mostrarSaldoMensalAPorGrupo(8L,filter);
			 //System.out.println("saldo do mes "+saldo);
			
			
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("erro ao buscar "+e.getMessage());
			}
		}

		
		public void fazerBalancoGeralIgreja() {
		  	   
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
	  }
		
		
	 
	 
    /* public void fazerBalancoPorGrupo() {
    	    Grupo grupo=gdao.buscar(codigo);
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
     }*/
     
     
     //FUNCIONA BALAN�O GERAL
    /* public void fazerBalancoGeralIgreja() {
   	   
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
     
     
     public void fazerBalancoMensalPorGrupo(){
    	 saldoMesAnterior=new BigDecimal("0.00");
    	 receitaMensal=mostrarReceitaMensalDoGrupo();
    	 despesaMensal=mostrarDespesaMensalDoGrupo();
    	 saldoMensal=mostrarSaldoMensalDoGrupo();
    	 
    	 saldoHaverProximoMes=saldoMensal;
    	
    	 
     }
     
     
     
     //FUNCIONA BALAN�O POR GRUPO
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
	
     
     //FUNCIONA BALAN�O GERAL
     public void fazerBalancoGeral() {
   	   
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
   }
    
     
	public void salvar() {
		try {
		LancamentoDao ldao=new LancamentoDao();
		Grupo grupo=new Grupo();
		grupo.setCodigo(8L);
		lancamento.setGrupo(grupo);
		lancamento.setData(lancamento.getData());
		lancamento.setDescricao(lancamento.getDescricao());
		lancamento.setDespesa(lancamento.getDespesa());
		lancamento.setReceita(lancamento.getReceita());
		//l.setSaldo(l.getSaldo().add(l.getReceita().subtract(getValorDespesa())));
		lancamento.setSaldo(valorSaldo.add(valorReceita).subtract(valorDespesa));
		ldao.salvar(lancamento);
	
		
			//ldao.merge(lancamento);
		//Lancamento lancamento2=ldao.merge2(lancamento);
			
			//lancamentos=ldao.buscarLancamentoPorGrupo(3L);
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarPorGrupo(8L);
			Utils.mostrarMensagemJsfSucesso("lan�amento registrado com sucesso\ntotal do saldo "+lancamento.getSaldo());
			
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
			//lancamentos=ldao.buscarLancamentoPorGrupo(8L);
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarPorGrupo(8L);
			Utils.mostrarMensagemJsfSucesso("lan�amento cancelado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel cancelar este lan�amento");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		lancamento = (Lancamento) evento.getComponent().getAttributes().get("lancamentoSelecionado");
		Grupo grupo=new Grupo();
		grupo.setCodigo(8L);
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








	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}








	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}








	public Lancamento getTeste() {
		return teste;
	}








	public void setTeste(Lancamento teste) {
		this.teste = teste;
	}


	public List<Lancamento> getLancamentos2() {
		return lancamentos2;
	}


	public void setLancamentos2(List<Lancamento> lancamentos2) {
		this.lancamentos2 = lancamentos2;
	}



	public boolean isSemSaldoInicial() {
		return semSaldoInicial;
	}



	public void setSemSaldoInicial(boolean semSaldoInicial) {
		this.semSaldoInicial = semSaldoInicial;
	}


	public String getStatusCaixa() {
		return statusCaixa;
	}


	public void setStatusCaixa(String statusCaixa) {
		this.statusCaixa = statusCaixa;
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


	public BigDecimal getReceitaMensal() {
		return receitaMensal;
	}


	public void setReceitaMensal(BigDecimal receitaMensal) {
		this.receitaMensal = receitaMensal;
	}


	public BigDecimal getDespesaMensal() {
		return despesaMensal;
	}


	public void setDespesaMensal(BigDecimal despesaMensal) {
		this.despesaMensal = despesaMensal;
	}


	public BigDecimal getSaldoMensal() {
		return saldoMensal;
	}


	public void setSaldoMensal(BigDecimal saldoMensal) {
		this.saldoMensal = saldoMensal;
	}


	public BigDecimal getSaldoHaverProximoMes() {
		return saldoHaverProximoMes;
	}


	public void setSaldoHaverProximoMes(BigDecimal saldoHaverProximoMes) {
		this.saldoHaverProximoMes = saldoHaverProximoMes;
	}


	public BigDecimal getSaldoMesAnterior() {
		return saldoMesAnterior;
	}


	public void setSaldoMesAnterior(BigDecimal saldoMesAnterior) {
		this.saldoMesAnterior = saldoMesAnterior;
	}
	
	
	
	
	
	
	
	
	 
	
	/*public void registrarLancamento(){
		if(lancamento.getTipoDespesa().getDescricao().equals("COMPRA")){
		 Utils.mostrarMensagemJsfSucesso("voce escolheu compras");	
		 

		}else if(lancamento.getTipoDespesa().getDescricao().equals("PAGAMENTO DE AGUA")){
			Utils.mostrarMensagemJsfSucesso("voce escolheu pagamento de agua");
		}
		
		lancamentos=lancamentoDao.buscarLancamentoPorGrupo(8L);
	}*/
	
	
	
	public void fecharLivroCaixa(){
		statusCaixa="CAIXA FECHADO";
		Grupo g=new Grupo();
		g.setCodigo(8L);
		BigDecimal saldoFinal=ldao.mostrarSaldoAtualPorGrupo(8L,new Date());
		if(saldoInicial!=null){
			caixaFechado=true;
			Utils.mostrarMensagemJsfSucesso("CAIXA FECHADO COM SALDO  DE  "+saldoFinal);
			
		}
		if(saldoInicial==null){
			caixaFechado=true;
			Utils.mostrarMensagemJsfSucesso("CAIXA  FECHADO");
		}

		
	}


	public boolean isTemSaldoInicial() {
		return temSaldoInicial;
	}


	public void setTemSaldoInicial(boolean temSaldoInicial) {
		this.temSaldoInicial = temSaldoInicial;
	}


	public boolean isNaoTemSaldoInicial() {
		return naoTemSaldoInicial;
	}


	public void setNaoTemSaldoInicial(boolean naoTemSaldoInicial) {
		this.naoTemSaldoInicial = naoTemSaldoInicial;
	}



	public boolean isReceita() {
		return isReceita;
	}



	public void setReceita(boolean isReceita) {
		this.isReceita = isReceita;
	}



	public boolean isDespesa() {
		return isDespesa;
	}



	public void setDespesa(boolean isDespesa) {
		this.isDespesa = isDespesa;
	}



	public BigDecimal getReceitaFinal() {
		return receitaFinal;
	}



	public void setReceitaFinal(BigDecimal receitaFinal) {
		this.receitaFinal = receitaFinal;
	}



	public BigDecimal getDespesaFinal() {
		return despesaFinal;
	}



	public void setDespesaFinal(BigDecimal despesaFinal) {
		this.despesaFinal = despesaFinal;
	}



	public BigDecimal getSaldoIncial() {
		return saldoIncial;
	}



	public void setSaldoIncial(BigDecimal saldoIncial) {
		this.saldoIncial = saldoIncial;
	}



	public BigDecimal getReceitaInicial() {
		return receitaInicial;
	}



	public void setReceitaInicial(BigDecimal receitaInicial) {
		this.receitaInicial = receitaInicial;
	}



	public BigDecimal getDespesaInicial() {
		return despesaInicial;
	}



	public void setDespesaInicial(BigDecimal despesaInicial) {
		this.despesaInicial = despesaInicial;
	}
	
	
	


	
	
	
}
