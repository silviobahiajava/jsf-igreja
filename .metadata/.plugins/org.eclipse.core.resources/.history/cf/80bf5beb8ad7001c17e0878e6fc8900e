package meucursoJPA.model.secretaria;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Faces;
import org.primefaces.component.datatable.DataTable;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import daos.CargoDao;
import daos.MembroInativoDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import utils.HibernateUtil;
import utils.Utils;

public class Relatorios {
	
	
	
	public static void mostrarCurriculoNaWeb(List<CurriculoProfissional> lista){
		String arquivo="/reports/curriculoprofissional.jasper";
		  FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
	        try {
	            ServletOutputStream servletOutputStream = response.getOutputStream();
	            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);
	            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,null,datasource);
	            servletOutputStream.flush();
	            servletOutputStream.close();
	        } catch (JRException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            facesContext.responseComplete();
	        }
	}
	
	
	
	public static void mostrarCartaConviteNaWeb(List<CartaConvite> lista){
		String arquivo="/reports/exemplo1.jasper";
		  FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
	        try {
	            ServletOutputStream servletOutputStream = response.getOutputStream();
	            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);
	            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,null,datasource);
	            servletOutputStream.flush();
	            servletOutputStream.close();
	        } catch (JRException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            facesContext.responseComplete();
	        }
	}
	
	
	
	
	public static void mostrarCertificadoDeBatismoNaWeb(List<CertificadoBatismo> lista){
		String arquivo="/reports/batizado.jasper";
		  FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
	        try {
	            ServletOutputStream servletOutputStream = response.getOutputStream();
	            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);
	            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,null,datasource);
	            servletOutputStream.flush();
	            servletOutputStream.close();
	        } catch (JRException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            facesContext.responseComplete();
	        }
	}
	
	
	
	public static void mostrarCartaDeApresentacaoNaWeb(List<CertificadoDeApresentacao> lista){
		  String arquivo="/reports/recemnascidojrxml.jasper";
		  FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
	        try {
	            ServletOutputStream servletOutputStream = response.getOutputStream();
	            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);
	            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,null,datasource);
	            servletOutputStream.flush();
	            servletOutputStream.close();
	        } catch (JRException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            facesContext.responseComplete();
	        }
	}
	
	public static void mostrarCartaDeMudancaSolteiroNaaWeb(List<CartaDeMudanca>lista){
		String arquivo="/reports/cartademudanca.jasper";
		  FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
	        try {
	            ServletOutputStream servletOutputStream = response.getOutputStream();
	            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);
	            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,null,datasource);
	            servletOutputStream.flush();
	            servletOutputStream.close();
	        } catch (JRException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            facesContext.responseComplete();
	        }
	}
	
	
	public static void mostrarCartaDeMudancaCasalNaaWeb(List<CartaDeMudanca>lista){
		String arquivo="/reports/cartademudancacasal.jasper";
		  FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
	        try {
	            ServletOutputStream servletOutputStream = response.getOutputStream();
	            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);
	            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,null,datasource);
	            servletOutputStream.flush();
	            servletOutputStream.close();
	        } catch (JRException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            facesContext.responseComplete();
	        }
	}
	
	
	
	public static void mostrarCartaDeMudancaCasaFilhosNalaWeb(List<CartaDeMudanca>lista){
		String arquivo="/reports/cartademudancafamilia.jasper";
		  FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
	        try {
	            ServletOutputStream servletOutputStream = response.getOutputStream();
	            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);
	            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,null,datasource);
	            servletOutputStream.flush();
	            servletOutputStream.close();
	        } catch (JRException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            facesContext.responseComplete();
	        }
	}
	
	
	
	public   void imprimirRelatorioCartaConvite(List<CartaConvite> lista) {
		
		try {
			String caminho = "/reports/teste.jrxml";
			InputStream stream = getClass().getResourceAsStream(caminho);
			JasperReport relatorioCompilado = JasperCompileManager.compileReport(stream);
			JasperPrint print = JasperFillManager.fillReport(relatorioCompilado,null,new JRBeanCollectionDataSource(lista));
			JasperViewer.viewReport(print,false);
			
		} catch (JRException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void gerarCartaConvite(List<CartaConvite>listaCartaConvite) {
		try {
				String arquivo="/reports/exemplo1.jasper";
				String caminho=Faces.getRealPath(arquivo);
				JasperPrint relatorio = JasperFillManager.fillReport(caminho,null,new JRBeanCollectionDataSource(listaCartaConvite));
				JasperPrintManager.printReport(relatorio, true);
				
		}catch(JRException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	}
	
	
	public static void gerarListaAniversariantes(List<Aniversariante>listaAniversariantes) {
		try {
				String arquivo="/reports/listadeaniversariantes.jasper";
				String caminho=Faces.getRealPath(arquivo);
				JasperPrint relatorio = JasperFillManager.fillReport(caminho,null,new JRBeanCollectionDataSource(listaAniversariantes));
				JasperPrintManager.printReport(relatorio, true);
				
		}catch(JRException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	}
	
	
	
	
	
	
	
	public static void gerarCartaMudanca(List<CartaDeMudanca>listaCartaMudanca) {
		try {
				String arquivo="/reports/cartademudanca.jasper";
				String caminho=Faces.getRealPath(arquivo);
				JasperPrint relatorio = JasperFillManager.fillReport(caminho,null,new JRBeanCollectionDataSource(listaCartaMudanca));
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carta gerada com sucesso");
		}catch(JRException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	}
	
	public static void gerarCartaMudancaCasal(List<CartaDeMudanca>listaCartaMudanca) {
		try {
				String arquivo="/reports/cartademudancacasal.jasper";
				String caminho=Faces.getRealPath(arquivo);
				JasperPrint relatorio = JasperFillManager.fillReport(caminho,null,new JRBeanCollectionDataSource(listaCartaMudanca));
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carta gerada com sucesso");
		}catch(JRException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	}
	
	public static void gerarCartaMudancaCasalComFilhos(List<CartaDeMudanca>listaCartaMudanca) {
		try {
				String arquivo="/reports/cartademudancafamilia.jasper";
				String caminho=Faces.getRealPath(arquivo);
				JasperPrint relatorio = JasperFillManager.fillReport(caminho,null,new JRBeanCollectionDataSource(listaCartaMudanca));
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carta gerada com sucesso");
		}catch(JRException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	}
	
	
	public static void gerarCertificadoDeBatismo(List<CertificadoBatismo>listaCertificadoBatismo) {
		try {
				String arquivo="/reports/batizado.jasper";
				String caminho=Faces.getRealPath(arquivo);
				JasperPrint relatorio = JasperFillManager.fillReport(caminho,null,new JRBeanCollectionDataSource(listaCertificadoBatismo));
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carta gerada com sucesso");
		}catch(JRException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	}
	
	public static void gerarCertificadoDeAPresentacao(List<CertificadoDeApresentacao>listaCertificado) {
		try {
				String arquivo="/reports/recemnascidojrxml.jasper";
				String caminho=Faces.getRealPath(arquivo);
				JasperPrint relatorio = JasperFillManager.fillReport(caminho,null,new JRBeanCollectionDataSource(listaCertificado));
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("certificado gerado com sucesso");
		}catch(JRException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	}
	
	public static void gerarCurriculo(List<CartaDeMudanca>listaCartaMudanca) {
		try {
				String arquivo="/reports/cartademudancacasalfilhos.jasper";
				String caminho=Faces.getRealPath(arquivo);
				JasperPrint relatorio = JasperFillManager.fillReport(caminho,null,new JRBeanCollectionDataSource(listaCartaMudanca));
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carta gerada com sucesso");
		}catch(JRException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	}
	
	public static void gerarListaDeAniversariantesDoMes(List<CartaDeMudanca>listaCartaMudanca) {
		try {
				String arquivo="/reports/cartademudancacasalfilhos.jasper";
				String caminho=Faces.getRealPath(arquivo);
				JasperPrint relatorio = JasperFillManager.fillReport(caminho,null,new JRBeanCollectionDataSource(listaCartaMudanca));
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carta gerada com sucesso");
		}catch(JRException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	
	}
	
	public static void mostrarCurriculo(List<CurriculoProfissional>listaCurriculo) {
		try {
				String arquivo="/reports/curriculoprofissional.jasper";
				String caminho=Faces.getRealPath(arquivo);
				JasperPrint relatorio = JasperFillManager.fillReport(caminho,null,new JRBeanCollectionDataSource(listaCurriculo));
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("curriculo gerado com sucesso");
		}catch(JRException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	
	}
	
	//cartão de membro
	
	public static  void gerarCarterinhaFundoAmarelo(Membro membro) {
		try {
				String arquivo="/reports/amarelopreto.jasper";
			//pegando o componente DataTable da �rvore de componentes do primefaces
			DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
			
			//pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			//pegando o filtro da coluna nome
			String nome=(String)filtros.get("nome");
			//String caminho = Faces.getRealPath("/reports/amarelo.jasper");
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			
		   	if(nome==null) {
				parametros.put("NOME","%%");
			}else {
				//parametros.put("NOME","%"+nome+"%");
				parametros.put("NOME", membro.getNome());
			}
			
			
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
		}
	}
	
	
	public static void gerarCarterinhaFundoAzul1(Membro membro) {
		try {
			//pegando o componente DataTable da �rvore de componentes do primefaces
			DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
			
			//pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			//pegando o filtro da coluna nome
			String nome=(String)filtros.get("nome");
			String caminho = Faces.getRealPath("/reports/lightbluepreto.jasper");
			Map<String,Object>parametros=new HashMap<String,Object>();
			
		   	if(nome==null) {
				parametros.put("NOME","%%");
			}else {
				//parametros.put("NOME","%"+nome+"%");
				parametros.put("NOME", membro.getNome());
			}
			
			
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
		}
	}
	
	
	
	
	
	public static void gerarCartaodeMembro(ActionEvent ev) {
		try {
			//pegando o componente DataTable da �rvore de componentes do primefaces
			//DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
			
			//pegando o mapa de filtros do componente datatable
			//Map<String, Object> filtros = tabela.getFilters();
			//pegando o filtro da coluna nome
			//String nome=(String)filtros.get("nome");
			Membro membro=(Membro)ev.getComponent().getAttributes().get("membroSelecionado");
			String caminho = Faces.getRealPath("/reports/verdepreto.jasper");
			Map<String,Object>parametros=new HashMap<String,Object>();
			
		   	if(membro.getNome()==null) {
				//parametros.put("NOME","%%");
				parametros.put("NOME",membro.getNome());
			}else {
				//parametros.put("NOME","%"+nome+"%");
				parametros.put("NOME", membro.getNome());
			}
			
			
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso para "+membro.getNome());
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
		}
	}
	
	//-----------------------------------------------------------------------
	
	
	
			
	//----------------------------------------------------------------------------
	
	//marrom claro-----------------------------------------------------
	
	
	public static void gerarCarterinhaFundoMarrom(Membro membro) {
		try {
			//pegando o componente DataTable da �rvore de componentes do primefaces
			DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
			
			//pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			//pegando o filtro da coluna nome
			String nome=(String)filtros.get("nome");
			String caminho = Faces.getRealPath("/reports/marronclaro.jasper");
			Map<String,Object>parametros=new HashMap<String,Object>();
			
		   	if(nome==null) {
				parametros.put("NOME","%%");
			}else {
				//parametros.put("NOME","%"+nome+"%");
				parametros.put("NOME", membro.getNome());
			}
			
			
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
		}
	}
	
	//---------------------------------------------------------------------
	
	//-----------lightcoral
	
	
	public static void gerarCarterinhaFundoLightCoral(Membro membro) {
		try {
				String arquivo="/reports/lightcoral.jasper";
			
			//pegando o componente DataTable da �rvore de componentes do primefaces
			DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
			
			//pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			//pegando o filtro da coluna nome
			String parametroNome=(String)filtros.get("nome");
			
			
			String parametroCargo="MEMBRO";
		String caminho=Faces.getRealPath(arquivo);
		Map<String,Object>parametros=new HashMap<String,Object>();
		if(parametroNome==null) {
			parametros.put("NOME","%%");
		}else {
			parametros.put("NOME","%"+parametroNome+"%");
			
		}
		
		parametros.put("TIPOCARGO",parametroCargo);
		Connection conexao = HibernateUtil.getConexao();
		JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
		JasperPrintManager.printReport(relatorio, true);
		Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
	}catch(JRException e) {
		Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
	}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//branco preto	
	
	public static void gerarCarterinhaFundoBranco(Membro membro) {
		try {
			String arquivo="/reports/brancopreto.jasper";
			//pegando o componente DataTable da �rvore de componentes do primefaces
			DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
			
			//pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			//pegando o filtro da coluna nome
			String nome=(String)filtros.get("nome");
			//String caminho = Faces.getRealPath("/reports/fundobranco.jasper");
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			
		   	if(nome==null) {
				parametros.put("NOME","%%");
			}else {
				//parametros.put("NOME","%"+nome+"%");
				parametros.put("NOME", membro.getNome());
			}
			
			
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha com fundo branco gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
		}
	}
	
	
	public static void gerarCarterinhaFundoVermelho(Membro membro) {
		try {
			//pegando o componente DataTable da �rvore de componentes do primefaces
			DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
			
			//pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			//pegando o filtro da coluna nome
			String nome=(String)filtros.get("nome");
			String caminho = Faces.getRealPath("/reports/fundovermelho.jasper");
			Map<String,Object>parametros=new HashMap<String,Object>();
			
		   	if(nome==null) {
				parametros.put("NOME","%%");
			}else {
				//parametros.put("NOME","%"+nome+"%");
				parametros.put("NOME", membro.getNome());
			}
			
			
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha com fundo vermelho gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
		}
	}

	
	



//1-CARTAO DE MEMBRO VERDE

public static void gerarCarterinhaMembroFundoVerde() {
	try {
			String arquivo="/reports/verdepreto.jasper";
		
		//pegando o componente DataTable da �rvore de componentes do primefaces
		DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
		
		//pegando o mapa de filtros do componente datatable
		Map<String, Object> filtros = tabela.getFilters();
		//pegando o filtro da coluna nome
		String parametroNome=(String)filtros.get("nome");
		
		
		String parametroCargo="MEMBRO";
	String caminho=Faces.getRealPath(arquivo);
	Map<String,Object>parametros=new HashMap<String,Object>();
	if(parametroNome==null) {
		parametros.put("NOME","%%");
	}else {
		parametros.put("NOME","%"+parametroNome+"%");
		
	}
	
	parametros.put("TIPOCARGO",parametroCargo);
	Connection conexao = HibernateUtil.getConexao();
	JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
	JasperPrintManager.printReport(relatorio, true);
	Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
}catch(JRException e) {
	Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
}
}


//2-CARTAO DE MEMBRO MARRON
		public static void gerarCarterinhaDeMembroFundoMarron(){
			try {
				
				String arquivo="/reports/marronclaro.jasper";
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				
				
				String parametroCargo="MEMBRO";
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			if(parametroNome==null) {
				parametros.put("NOME","%%");
			}else {
				parametros.put("NOME","%"+parametroNome+"%");
				
			}
			
			parametros.put("TIPOCARGO",parametroCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
		}
		}

	
		//3CARTAO DE MEMBRO AZUL
		public static void gerarCartaodeMembroIadmif(){
			try {
				
				String arquivo="/reports/cartaodemembro_iadmif.jasper";
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				
				String parametroTipoCargo="MEMBRO";
				String parametroCargo="MEMBRO";
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			if(parametroNome==null) {
				parametros.put("NOME","%%");
			}else {
				parametros.put("NOME","%"+parametroNome+"%");
				
			}
			
			parametros.put("CARGO",parametroCargo);
			parametros.put("TIPOCARGO",parametroTipoCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de MEMBRO gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de MEMBRO");
		}
		}
		//=================================================================================

		public static void gerarCartaodeMembroIadmif2(){
			try {
				
				String arquivo="/reports/cartaodemembro2_iadmif.jasper";
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				
				String parametroTipoCargo="MEMBRO";
				
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			if(parametroNome==null) {
				parametros.put("NOME","%%");
			}else {
				parametros.put("NOME","%"+parametroNome+"%");
				
			}
			
			
			parametros.put("TIPOCARGO",parametroTipoCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de MEMBRO gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de MEMBRO");
		}
		}
		
		
		//==================================================================================
		//3CARTAO DE MEMBRO AZUL
		public static void gerarCartaodeObreiroIadmif(){
			try {
				
				String arquivo="/reports/cartaodeobreiro_iadmif.jasper";
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				String parametroCargo=(String)filtros.get("cargo");
				
				String parametroTipoCargo="OBREIRO";
				
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			if(parametroNome==null) {
				parametros.put("NOME","%%");
			}else {
				parametros.put("NOME","%"+parametroNome+"%");
				
			}
			
			if(parametroCargo==null) {
				parametros.put("CARGO","%%");
			}else {
				parametros.put("CARGO","%"+parametroCargo+"%");
				
			}
			
			//parametros.put("CARGO",parametroCargo);
			parametros.put("TIPOCARGO",parametroTipoCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de OBREIRO gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de OBREIRO");
			e.printStackTrace();
		}
		}

		
		public static void gerarCartaodeObreiroIadmif2(){
			try {
				
				String arquivo="/reports/cartaodeobreiro2_iadmif.jasper";
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				String parametroCargo=(String)filtros.get("cargo");
				
				String parametroTipoCargo="OBREIRO";
				
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			if(parametroNome==null) {
				parametros.put("NOME","%%");
			}else {
				parametros.put("NOME","%"+parametroNome+"%");
				
			}
			
			if(parametroCargo==null) {
				parametros.put("CARGO","%%");
			}else {
				parametros.put("CARGO","%"+parametroCargo+"%");
				
			}
			
			//parametros.put("CARGO",parametroCargo);
			parametros.put("TIPOCARGO",parametroTipoCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de OBREIRO gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de OBREIRO");
			e.printStackTrace();
		}
		}

		
		
		
		
		
		
		
		
		
		
		
		
		///==================================================================================
		
		//4-CARTAO DE MEMBRO AMARELO
		public static  void gerarCarterinhaDeMembroFundoAmarelo(){
			try {
				
				String arquivo="/reports/amarelopreto.jasper";
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				
				
				String parametroCargo="MEMBRO";
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			if(parametroNome==null) {
				parametros.put("NOME","%%");
			}else {
				parametros.put("NOME","%"+parametroNome+"%");
				
			}
			
			parametros.put("TIPOCARGO",parametroCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
		}
		}


		
		//5-CARTAO DE MEMBRO BRANCO
		public static void gerarCarterinhaDeMembroFundoBranco(){
			try {
				
				String arquivo="/reports/brancopreto.jasper";
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				
				
				String parametroCargo="MEMBRO";
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			if(parametroNome==null) {
				parametros.put("NOME","%%");
			}else {
				parametros.put("NOME","%"+parametroNome+"%");
				
			}
			
			parametros.put("TIPOCARGO",parametroCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
		}
		}


		//6-CARTAO DE MEMBRO CINZA
		public static void gerarCarterinhaDeMembroFundoCinza(){
			try {
				
				String arquivo="/reports/lightcoral.jasper";
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				
				
				String parametroCargo="MEMBRO";
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			if(parametroNome==null) {
				parametros.put("NOME","%%");
			}else {
				parametros.put("NOME","%"+parametroNome+"%");
				
			}
			
			parametros.put("TIPO",parametroCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
		}catch(JRException e) {
			
		}
		}


		public static void gerarPDFItext(String titulo,String texto){
			Document documento=new Document();
			try {
				PdfWriter.getInstance(documento, new FileOutputStream("D://RELATORIOS/arquivo.pdf"));
				documento.open();
				documento.addTitle(titulo);
				documento.add(new Paragraph(texto));
				Utils.mostrarMensagemSwing("arquivo gerado com sucesso");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("erro "+e.getMessage());
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("erro "+e.getMessage());
			}
			finally {
				documento.close();
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
}
