package beans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.primefaces.component.datatable.DataTable;

import daos.AniversarioDao;
import entidades.Aniversariante;
import entidades.Membro;
import entidades.Relatorios;
import filtros.AniversarioFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import utils.HibernateUtil;
import utils.Utils;



@ManagedBean(name="aniversarianteadultoBean")
@ViewScoped
public class AniversarianteAdultoBean {
	private List<Membro>aniversariantes=new ArrayList<Membro>();
	private AniversarioDao dao=new AniversarioDao();
	
	@PostConstruct
	public void buscarAniversariantesDoDia() {
		try {
			
			
			Date dataAtual=new Date();
			GregorianCalendar calendario=new GregorianCalendar();
			calendario.setTime(dataAtual);
			int diaAtual=calendario.get(Calendar.DAY_OF_MONTH);
			int mesAtual = calendario.get(Calendar.MONTH)+1;
			//Utils.mostrarMensagemJsfSucesso("mes atual "+mesAtual+"\ndia atual "+mesAtual);
			 aniversariantes=dao.buscarAniversariantes(diaAtual,mesAtual);
			//aniversariantes=dao.buscarAniversariantes2(mesAtual);
			
			 
			
		
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}
	
	
	//3CARTAO DE MEMBRO AZUL
	public void gerarCartaodeMembroIadmif(){
		try {
			
			String arquivo="/reports/listadeaniversariantes.jasper";
			
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
	public List<Membro> getAniversariantes() {
		return aniversariantes;
	}
	public void setAniversariantes(List<Membro> aniversariantes) {
		this.aniversariantes = aniversariantes;
	}
	
	
	public void mostarAniversariantesDoMes(){
try {
			
			
			Date dataAtual=new Date();
			GregorianCalendar calendario=new GregorianCalendar();
			calendario.setTime(dataAtual);
			//int diaAtual=calendario.get(Calendar.DAY_OF_MONTH);
			int mesAtual = calendario.get(Calendar.MONTH)+1;
			//Utils.mostrarMensagemJsfSucesso("mes atual "+mesAtual+"\ndia atual "+mesAtual);
			 //aniversariantes=dao.buscarAniversariantes(diaAtual,mesAtual);
			aniversariantes=dao.buscarAniversariantes2(mesAtual);
		
			
			 
			
		
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}

	
	public void gerarListaAniversariantes(){
		List<Aniversariante>lista=new ArrayList<Aniversariante>();
		for(Membro m:aniversariantes){
			Aniversariante aniversariante=new Aniversariante();
			aniversariante.setNome(m.getNome());
			aniversariante.setDataAniversario(m.getDataNascimento());
			lista.add(aniversariante);
		}
			Relatorios.gerarListaAniversariantes(lista);
			
			
		}
	
	}
	
	
	
	
	
	

	
	

