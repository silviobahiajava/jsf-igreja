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

import br.com.testejsf.utils.Utils;
import daos.GrupoDao;
import daos.LancamentoDao;
import entidades.Grupo;
import entidades.Lancamento;
import enums.CaixaEnum;
import filtros.LancamentoIgrejaFilter;

@ManagedBean(name="lancamentocriancaBean02")
@ViewScoped
public class LancamentoCriancaBean implements Serializable{

	private static final long serialVersionUID = 1L;
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
	
	private boolean semSaldoInicial;
	private BigDecimal saldoInicial;
	private BigDecimal saldoMensal;
	private BigDecimal saldoMesAnterior;
	private BigDecimal saldoHaverProximoMes;
	private BigDecimal receitaMensa;
	private BigDecimal despesaMensal;
	private String statusCaixa;
	
	private boolean caixaAberto;
	private boolean caixaFechado;
	
	
	
	
	

	
	@PostConstruct
	public void listar() {
		try {
			lancamento=new Lancamento();
			LancamentoDao ldao=new LancamentoDao();
			/*Grupo grupo=new Grupo();
			grupo.setCodigo(2L);*/
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarLancamentoPorGrupo(3L);
		//	lancamentos=ldao.buscarLancamentoPorGrupo(2L);
			//lancamentos=ldao.buscarPorGrupo(grupo.getCodigo());
			
			
			//lancamentos=ldao.listar();
			
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	public void alterarStatusCaixa(){
		if(caixaAberto==true){
			fecharCaixa();
		}if(caixaFechado==true){
			abrirCaixa();
		}
		
	}
	
	
	public void abrirCaixa() {
		lancamento=new Lancamento();
		valorSaldo=mostrarSaldoMensalDoGrupo();
		if(valorSaldo.equals(null)){
		 Utils.mostrarMensagemJsfAlerta("Caixa zerado.Insira um valor ");
		 semSaldoInicial=true;
		}if(valorSaldo!=null){
			lancamento.setSaldo(valorSaldo);
			lancamento.setSaldoInicial(valorSaldo);
		}
		if(semSaldoInicial==true && saldoInicial!=null){
			lancamento.setSaldoInicial(saldoInicial);
		}
		if(semSaldoInicial==true && saldoInicial==null){
			Utils.mostrarMensagemJsfAlerta("Caixa zerado.Insira um valor");
			
		}
		Grupo grupo=new Grupo();
		grupo.setCodigo(2L);
		lancamento.setCaixaEnum(CaixaEnum.ABERTO);
		lancamento.setData(new Date());
		lancamento.setGrupo(grupo);
		lancamento.setCaixaEnum(CaixaEnum.FECHADO);
		lancamento.setSaldoFinal(valorSaldo);
		lancamento.setSaldo(valorSaldo);
		lancamento.setSaldoInicial(valorSaldo);
		
		ldao.abrirCaixa(lancamento);
		statusCaixa="caixa aberto--FECHAR CAIXA";
		caixaAberto=true;
		caixaFechado=false;
		Utils.mostrarMensagemJsfSucesso("caixa aberto com saldo de  "+valorSaldo);
	}
	
	
	
	public void fecharCaixa() {
		lancamento=new Lancamento();
		valorSaldo=mostrarSaldoMensalDoGrupo();
		receitaMensa=mostrarReceitaMensalDoGrupo();
		despesaMensal=mostrarDespesaMensalDoGrupo();
		
		saldoHaverProximoMes=valorSaldo;
		
		Grupo grupo=new Grupo();
		grupo.setCodigo(2L);
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
				if(saldoInicial==null){
					
					lancamento.setSaldoInicial(lancamento.getSaldoInicial());
					lancamento.setSaldo(lancamento.getSaldoInicial());
					lancamento.setReceita(lancamento.getSaldoInicial());
					lancamento.setDespesa(new BigDecimal("0.00"));
					lancamento.setData(lancamento.getData());
					lancamento.setHistorico("saldo inicial");
					
					
					lancamento.setDescricao("ABERTURA DE CAIXA");
					Grupo grupo=new Grupo();
					grupo.setCodigo(2L);
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
					grupo.setCodigo(2L);
					lancamento.setGrupo(grupo);
					lancamento.setCaixaAberto(true);
					lancamento.setCaixaEnum(CaixaEnum.ABERTO);
				}
				
				ldao.abrirCaixa(lancamento);
				Utils.mostrarMensagemJsfSucesso("caixa aberto pela primeira vez com saldo inicial de  "+lancamento.getSaldoInicial());
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
    	    Grupo grupo=gdao.buscar(2L);
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
     public void fazerBalancoMensalPorGrupo(){
    	 saldoMesAnterior=new BigDecimal("0.00");
    	 receitaMensa=mostrarReceitaMensalDoGrupo();
    	 despesaMensal=mostrarDespesaMensalDoGrupo();
    	 saldoMensal=mostrarSaldoMensalDoGrupo();
    	 
    	 saldoHaverProximoMes=saldoMensal;
    	
    	 
     }
     
     
     //FUNCIONA BALAN�O POR GRUPO
   /*  public void fazerBalancoDaIgreja() {
 	   
 		valorReceita=ldao.mostrarReceitaPorGrupo(2L);
 		valorDespesa=ldao.mostrarDespesaPorGrupo(2L);
 		valorSaldo=ldao.mostrarSaldoPorGrupo(2L);
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
     
     
	public void salvar() {
		
		try {
			LancamentoDao ldao=new LancamentoDao();
		Grupo grupo=new Grupo();
		grupo.setCodigo(2L);
		lancamento.setGrupo(grupo);
		lancamento.setData(lancamento.getData());
		lancamento.setDescricao(lancamento.getDescricao());
		lancamento.setDespesa(lancamento.getDespesa());
		lancamento.setReceita(lancamento.getReceita());
	
		
			ldao.merge(lancamento);
			
			//lancamentos=ldao.buscarLancamentoPorGrupo(3L);
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarPorGrupo(2L);
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
			//lancamentos=ldao.buscarLancamentoPorGrupo(2L);
			//lancamentos=ldao.listar();
			lancamentos=ldao.buscarPorGrupo(2L);
			Utils.mostrarMensagemJsfSucesso("lan�amento cancelado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel cancelar este lan�amento");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		lancamento = (Lancamento) evento.getComponent().getAttributes().get("lancamentoSelecionado");
		Grupo grupo=new Grupo();
		grupo.setCodigo(2L);
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
	
	
	
	public BigDecimal getReceitaMensa() {
		return receitaMensa;
	}



	public void setReceitaMensa(BigDecimal receitaMensa) {
		this.receitaMensa = receitaMensa;
	}



	public BigDecimal getDespesaMensal() {
		return despesaMensal;
	}



	public void setDespesaMensal(BigDecimal despesaMensal) {
		this.despesaMensal = despesaMensal;
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
		
		
		 saldoMensal=dao.mostrarSaldoMensalAPorGrupo(2L,filter);
		 return saldoMensal;
		 //System.out.println("saldo do mes "+saldo);
		
		
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("erro ao buscar "+e.getMessage());
		}
		return saldoInicial;
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
		
		
		 saldoMensal=dao.mostrarSaldoMensalAPorGrupo(2L,filter);
		 //System.out.println("saldo do mes "+saldo);
		
		
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("erro ao buscar "+e.getMessage());
		}
	}
	
	
	
	 //FUNCIONA BALAN�O GERAL
    public void fazerBalancoGeralCrianca() {
  	   
 		valorReceita=ldao.mostrarReceitaPorGrupo(2L);
 		valorDespesa=ldao.mostrarDespesaPorGrupo(2L);
 		valorSaldo=ldao.mostrarSaldoPorGrupo(2L);
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



	public boolean isSemSaldoInicial() {
		return semSaldoInicial;
	}



	public void setSemSaldoInicial(boolean semSaldoInicial) {
		this.semSaldoInicial = semSaldoInicial;
	}



	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}



	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
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
		
		lancamentos=lancamentoDao.buscarLancamentoPorGrupo(2L);
	}*/
	
	
	public void abrirLivroCaixa(){
		
		Grupo g=new Grupo();
		g.setCodigo(2L);
			
			saldoInicial=ldao.mostrarSaldoAtualPorGrupo(2L,new Date());
			
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
		g.setCodigo(2L);
		BigDecimal saldoFinal=ldao.mostrarSaldoAtualPorGrupo(2L,new Date());
		if(saldoInicial!=null){
			caixaFechado=true;
			Utils.mostrarMensagemJsfSucesso("CAIXA FECHADO COM SALDO  DE  "+saldoFinal);
			
		}
		if(saldoInicial==null){
			caixaFechado=true;
			Utils.mostrarMensagemJsfSucesso("CAIXA  FECHADO");
		}

		
	}


	
}
