package beans;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Faces;
import org.primefaces.component.datatable.DataTable;

import br.com.testejsf.utils.HibernateUtil;
import br.com.testejsf.utils.Utils;
import daos.MembroDao;
import entidades.Membro;
import enums.CargoEnum;
import enums.TipoCadastroEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperRunManager;

@ManagedBean(name = "cartaodemembroBean")
public class CartaodeMembroBean {
	private List<Membro> membros;
	private MembroDao membroDao = new MembroDao();
	private Membro membro = new Membro();
	private String tipoCargo;
	private List<Membro> listaCadastroCompleto = new ArrayList<Membro>();
	private List<Membro> listaCadastroIncompleto = new ArrayList<Membro>();

	@PostConstruct
	public void listar() {
		try {
			// membros=membroDao.buscarPorStatusMembroTipoCargo("ATIVO","MEMBRO");
			// membros = membroDao.buscarPorStatusMembroTipo("ATIVO");
		   // membros = membroDao.buscarPorTipoDeCargo("MEMBRO");
			//listaCadastroCompleto=mostrarCadastroCompleto();
			membros=membroDao.buscarPorStatusTipoCargo("ATIVO","MEMBRO");

		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}

	/* public List<Membro>mostrarCadastroCompleto(){
		 membros = membroDao.buscarPorTipoDeCargo("MEMBRO");
		 for(Membro m:membros){
			 
			/* if(m.getNome()!=" " || !m.getEstadoCivil().equals(null)|| m.getCongregacao().getDescricao()!=" "
					|| !m.getCargoEnum().equals(null)|| !m.getDataNascimento().equals(null)|| m.getNomeMae()!=" "||
					m.getNatualidade()!=" "|| m.getNacionalidade()!=" "|| !m.getEstadosBrasileirosEnum().equals(null)
					|| m.getRg()!=" " || m.getCpf()!=" "){
				 listaCadastroCompleto=membros;
				 membro.setTipoCadastro(TipoCadastroEnum.ATIVO);
			 }*/
			 
			/* if(m.getStatusCadastro()==0 && m.getStatusMembro().equals("ATIVO") && m.getTipoCadastro().equals("ATIVO")){
				/* listaCadastroCompleto.add(m);*/
			// }
			
		/* }
		 return listaCadastroCompleto;
		 
		 */
	// }
	
	// FUNCIONA PERFEITAMENTE
	public void mostrarCartaoDeMembrosNaWeb() {
		String arquivo = "/reports/membro2.jasper";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
		String parametroTipoCargo = "MEMBRO";
		String parametroStatus="ATIVO";
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("STATUS",parametroStatus);
		parametros.put("TIPOCARGO", parametroTipoCargo);
		Connection conexao = HibernateUtil.getConexao();

		try {
			ServletOutputStream servletOutputStream = response.getOutputStream();
			JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametros, conexao);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			facesContext.responseComplete();
		}
	}

	public void listarmembroPorCodigo() {
		membros = membroDao.buscarPorTipoDeCargoECodigo("MEMBRO", membro.getCodigo());
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public void gerarCartaodeMembro() {
		try {

			String arquivo = "/reports/membro2.jasper";
			String caminho = Faces.getRealPath(arquivo);
			String parametroTipoCargo = "MEMBRO";
			String parametroStatus="ATIVO";
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("STATUS",parametroStatus);
			parametros.put("TIPOCARGO", parametroTipoCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de membro gerada com sucesso");
		} catch (JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de membro");
			e.printStackTrace();
		}
	}

	public MembroDao getMembroDao() {
		return membroDao;
	}

	public void setMembroDao(MembroDao membroDao) {
		this.membroDao = membroDao;
	}

	public String getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public List<Membro> getListaCadastroCompleto() {
		return listaCadastroCompleto;
	}

	public void setListaCadastroCompleto(List<Membro> listaCadastroCompleto) {
		this.listaCadastroCompleto = listaCadastroCompleto;
	}

	public List<Membro> getListaCadastroIncompleto() {
		return listaCadastroIncompleto;
	}

	public void setListaCadastroIncompleto(List<Membro> listaCadastroIncompleto) {
		this.listaCadastroIncompleto = listaCadastroIncompleto;
	}

}
