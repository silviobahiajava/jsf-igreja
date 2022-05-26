package beans;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperRunManager;

@ManagedBean(name = "cartaodeobreiroBean")
public class CartoDeObreiroBean {
	private List<Membro> membros;
	private Membro membro = new Membro();
	private MembroDao membroDao = new MembroDao();

	@PostConstruct
	public void listar() {
		try {
			// membros=membroDao.buscarPorStatusMembroTipoCargo("ATIVO","MINISTRO");
			// membros = membroDao.buscarPorStatusMembroTipo("ATIVO");
			//membros = membroDao.buscarPorTipoDeCargo("MINISTRO");
			membros=membroDao.buscarPorStatusTipoCargo("ATIVO","MINISTRO");

		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}

	// FUNCIONA PERFEITAMENTE
	// FUNCIONA PERFEITAMENTE
		public void mostrarCartaoDeObreirosNaWeb() {
			String arquivo = "/reports/obreiro2.jasper";
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
			String parametroTipoCargo = "MINISTRO";
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
		membros = membroDao.buscarPorTipoDeCargoECodigo("MINISTRO", membro.getCodigo());
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

	public void gerarCartaodeObreiro() {
		try {

			String arquivo = "/reports/obreiro2.jasper";
			String caminho = Faces.getRealPath(arquivo);
			String parametroTipoCargo = "MINISTRO";
			String parametroStatus="ATIVO";
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("STATUS",parametroStatus);
			parametros.put("TIPOCARGO", parametroTipoCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
		} catch (JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
			e.printStackTrace();
		}
	}

}
