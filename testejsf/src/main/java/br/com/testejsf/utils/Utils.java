package br.com.testejsf.utils; 
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

/*import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;
*/
import org.omnifaces.util.Messages;


import entidades.Membro;
import enums.Congregacao;
import enums.EstadoCivil;


public class Utils {
	//conectando com banco de dados
	
	/*private static EntityManagerFactory emf=Persistence.createEntityManagerFactory("igreja1");
	public static EntityManager conecar(){
		try{
			EntityManager em=emf.createEntityManager();
			return em;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	*/
	//mensagens
	public static void mostrarMensagemJsfSucesso(String msg){
		FacesContext fc=FacesContext.getCurrentInstance();
		FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg);
		fc.addMessage(null, fm);
	}
	public static void mostrarMensagemJsfAlerta(String msg){
		FacesContext fc=FacesContext.getCurrentInstance();
		FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_WARN,msg, msg);
		fc.addMessage(null, fm);
	}
	public static void mostrarMensagemJsfErro(String msg){
		FacesContext fc=FacesContext.getCurrentInstance();
		FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", msg);
		fc.addMessage(null, fm);
	}
	public static void mostrarMensagemSwing(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}
	public static void mostrarMensagemConsole(String msg){
		System.out.println(msg);
	}
	
	//recebendo argumentos
	public static String recebeString(String str){
		String resposta=JOptionPane.showInputDialog(null,str);
		
		return resposta;
	}
	public static String getParam(String str){
		FacesContext fc=FacesContext.getCurrentInstance();
		ExternalContext ec=fc.getExternalContext();
		Map<String, String> parametros = ec.getRequestParameterMap();
		String valor = parametros.get(str);
		return valor;
	}
	//convertendo argumentos
	
	//convertendo String para inteiro
	public static int converteStringPraInteiro(String str){
		int resposta=Integer.parseInt(recebeString(str));
		return resposta;
	}
	//convertendo String para double
		public static double converteStringPraDouble(String str){
			double resposta=Double.parseDouble(recebeString(str));
			return resposta;
		}
		
		//convertendo String para float
		public static float converteStringPraFloat(String str){
			float resposta=Float.parseFloat(recebeString(str));
			return resposta;
		}
		
		//convertendo string para bigdecimal
		public static  BigDecimal converteStringPraBigDecimal(String str){
			BigDecimal resposta=new BigDecimal(recebeString(str));
			return resposta;
		}
		
		public static int converteBigDecimalParaInt(BigDecimal valor){
			int resposta=valor.intValue();
			return resposta;
		}
		
		//convertendo string para date
//		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//		//Date data = formato.parse("23/11/2015");
//		Date dataFormatada = formato.parse(dataRecebida);
//		novo.setLancamento(data);
//		System.out.println(formato.format(data));
		public static Date converteStringPraDate(String str){
			SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date data=formato.parse(str);
				return data;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		public static void mostrarDataConsole(Date data){
			SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
			String dataString=formato.format(data);
			mostrarMensagemConsole(dataString);
		}
		
		public static void mostrarDataSwing(Date data){
			SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
			String dataString=formato.format(data);
			mostrarMensagemSwing(dataString);
		}
		
		public static void mostrarDataJsf(Date data){
			SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
			String dataString=formato.format(data);
			mostrarMensagemJsfSucesso(dataString);
		}
		
//		public static Endereco popularEndereco(String cep){
//			Endereco end=new Endereco();
//			WebServiceCep endereco = WebServiceCep.searchCep(cep);
//			String logradouro=endereco.getLogradouro();
//			String bairro=endereco.getBairro();
//			String cidade=endereco.getCidade();
//			String estado=endereco.getUf();
//			end.setCep(cep);
//			end.setLogradouro(logradouro);
//			end.setBairro(bairro);
//			end.setCidade(cidade);
//			end.setEstado(estado);
//			return end;
//		}
		
//		public static Endereco popularEndereco(String cep){
//			
//			WebServiceCep webService = WebServiceCep.searchCep(cep);
//			Endereco endereco=new Endereco();
//			String logradouro=webService.getLogradouro();
//			String bairro=webService.getBairro();
//			String cidade=webService.getCidade();
//			String estado=webService.getUf();
//			endereco.setEndereco(logradouro);
//			endereco.setBairro(bairro);
//			endereco.setCidade(cidade);
//			endereco.setEstado(estado);
//			
//			return endereco;
//		}
		
	public static Membro recuperarEstadoCivil(String strEnum){
		return null;
		/*Membro membro=new Membro();
		switch(strEnum){
		case "SOLTEIRO":
			membro.setEstadoCivil(EstadoCivil.SOLTEIRO);
			break;
			
		case "CASADO":
			membro.setEstadoCivil(EstadoCivil.CASADO);
			break;
			
		case "DIVORCIADO":
			membro.setEstadoCivil(EstadoCivil.DIVORCIADO);
			break;
		case "VIUVO":
			membro.setEstadoCivil(EstadoCivil.VIUVO);
			break;
		}
		return membro;
	}
	
	public static Membro recuperarCongregacao(String strEnum){
		Membro membro=new Membro();
		switch(strEnum){
		case "SEDE":
			//pessoa.setSituacaoDoCristaoEnum(SituacaoDoCristaoEnum.OBREIRO);
			membro.setCongregacao(Congregacao.SEDE);
			break;
			
		case "FRANCISQUINE":
			//pessoa.setSituacaoDoCristaoEnum(SituacaoDoCristaoEnum.NOVOCONVERTIDO);
			membro.setCongregacao(Congregacao.FRANCISQUINE);
			break;
		
	    case "MONTE CARLOS":
	    	//pessoa.setSituacaoDoCristaoEnum(SituacaoDoCristaoEnum.BATIZADO);
	    	membro.setCongregacao(Congregacao.MONTECARLOS);
	    	break;
		}
	
	return membro;
	*/
	}
	
	
	
	
	
	
	/*public static void gerarBoletoStatico() {
		Cedente cedente=new Cedente("Silvio","216.821.248-19");
		Sacado sacado=new Sacado("Luciana","052.183.189-06");
		Endereco enderecoSacado=new Endereco();
		enderecoSacado.setUF(UnidadeFederativa.PR);
		enderecoSacado.setLocalidade("Rolandia");
		enderecoSacado.setCep("216,821.248-19");
		enderecoSacado.setBairro("Conjunto Habitacional San Tiago");
		enderecoSacado.setLogradouro("rua Jos� Makocsi");
		enderecoSacado.setNumero("115");
		sacado.addEndereco(enderecoSacado);
		ContaBancaria contaBancaria=new ContaBancaria(BancosSuportados.BANCO_DO_BRASIL.create());
		//entidades.ContaBancaria contaCedente=new entidades.ContaBancaria();
		contaBancaria.setNumeroDaConta(new NumeroDaConta(741258,"123"));
		Carteira carteira=new Carteira();
		carteira.setCodigo(756);
		contaBancaria.setCarteira(carteira);
		Titulo titulo=new Titulo(contaBancaria,sacado,cedente);
		titulo.setNumeroDoDocumento("98648888");
		titulo.setNossoNumero("287963254");
		titulo.setDigitoDoNossoNumero("987455");
		titulo.setValor(new BigDecimal("350.00"));
		titulo.setDataDoDocumento(new Date());
		titulo.setDataDoVencimento(new Date());
		titulo.setTipoDeDocumento(TipoDeTitulo.DD_DOCUMENTO_DE_DIVIDA);
		titulo.setAceite(Aceite.A);
		titulo.setDesconto(new BigDecimal("350.00"));
		titulo.setDeducao(new BigDecimal("20.00"));
		titulo.setMora(new BigDecimal("10.00"));
		titulo.setAcrecimo(new BigDecimal("15.00"));
		titulo.setValorCobrado(new BigDecimal("600.00"));
		Boleto boletoParaPagar=new Boleto(titulo);
		boletoParaPagar.setLocalPagamento("Pag�vel preferencialmente no Bradesco"
		+"ou em qualquer Banco at�  o vencimento");
		boletoParaPagar.setInstrucaoAoSacado("pague no dia certo para evitar juros indesejados");
		boletoParaPagar.setInstrucao1("Pagamento at� o dia de vencimento N�O COBRAR JUROS");
		boletoParaPagar.setInstrucao2("Pagamento ap�s o vencimento somente no BRADESCO");
		boletoParaPagar.setDataDeProcessamento(new Date());
		BoletoViewer boletoViewer=new BoletoViewer(boletoParaPagar);
		File arquivoPdf = boletoViewer.getPdfAsFile("boleto.pdf");
		Desktop desktop= Desktop.getDesktop();
		try {
			 Utils.mostrarMensagemJsfSucesso("boleto gerado com sucesso");
			 desktop.open(arquivoPdf);
		}catch(IOException e) {
			Messages.addGlobalError("erro ao gerar pdf");
		}
	
	}
	*/
	
	public static Date pegarPrimeiroDiaDoMes(){
		Calendar calendario=Calendar.getInstance();
		int anoAtual=calendario.get(Calendar.YEAR);
		int mes=calendario.get(Calendar.MONTH);
		
		int mesAtual=mes+1;
		
		@SuppressWarnings("deprecation")
		Date primeiroDiaDoMes = new Date(anoAtual+"/"+mesAtual+"/"+Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
//		GregorianCalendar gc=new GregorianCalendar();
//		gc.setTime(primeiroDiaDoMes);
//		int dia1 = gc.get(Calendar.MONTH)-4;
		return primeiroDiaDoMes;
	}
	
	public static Date mostrarPrimeiroDia(){
		Calendar calendario=Calendar.getInstance();
		int anoAtual=calendario.get(Calendar.YEAR);
		int mes=calendario.get(Calendar.MONTH);
		
		int mesAtual=mes+1;
		
		@SuppressWarnings("deprecation")
		Date primeiroDiaDoMes = new Date(anoAtual+"/"+mesAtual+"/"+Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(primeiroDiaDoMes);
		int dia1 = gc.get(Calendar.MONTH)-4;
		return primeiroDiaDoMes;
	}
	
	
	public static Date mostrarSegundoDia(){
		Calendar calendario=Calendar.getInstance();
		int anoAtual=calendario.get(Calendar.YEAR);
		int mes=calendario.get(Calendar.MONTH);
		
		int mesAtual=mes+1;
		
		@SuppressWarnings("deprecation")
		Date ultimoDiaDoMes = new Date(anoAtual+"/"+mesAtual+"/"+Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(ultimoDiaDoMes);
		int dia2 = gc.get(Calendar.MONTH)-25;
		return ultimoDiaDoMes;
	}
	
	
	public static Date pegarUltimoDiaDoMes(){
		Calendar calendario=Calendar.getInstance();
		int anoAtual=calendario.get(Calendar.YEAR);
		int mes=calendario.get(Calendar.MONTH);
		int mesAtual=mes+1;
		@SuppressWarnings("deprecation")
		Date ultimoDiaDoMes = new Date(anoAtual+"/"+mesAtual+"/"+Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		return ultimoDiaDoMes;
	}
	
	}


