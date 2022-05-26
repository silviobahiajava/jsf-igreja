package beans;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testejsf.utils.Utils;
import daos.AulaEscolaDominicalDao;
import entidades.AulaEscolaDominical;
import filtros.AulaEscolaDominicalFiltro;

@ManagedBean(name="resumoBean")
@ViewScoped
public class ResumoEscolaDominicalBean {
	private AulaEscolaDominical aula=new AulaEscolaDominical();
	//private AulaEscolaDominicalFiltro filtro;
	
	private List<AulaEscolaDominical>aulas=new ArrayList<AulaEscolaDominical>();
	
	private AulaEscolaDominicalDao aulaDao=new AulaEscolaDominicalDao();
	@PostConstruct
	public void listar() {
		try {
			//aulas=aulaDao.listar();
			aulas=aulaDao.buscarAulaPorData(aula.getDataAula());
//			for(AulaEscolaDominical aula:aulas){
//				System.out.println("classe "+aula.getClasse()+"total de alunos na aula "+aula.getTotalAlunosPresentes());
//			}
			
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	public void buscar(){
		try{
		aulas=aulaDao.buscarAulaPorData(aula.getDataAula());
		for(AulaEscolaDominical aula:aulas){
			System.out.println(aula.getClasse().getNomeClasse()+"total de alunos na aula "+aula.getTotalAlunosPresentes());
		}
		}catch(RuntimeException e){
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar "+e.getMessage());
		}
	}
	public AulaEscolaDominical getAula() {
		return aula;
	}
	public void setAula(AulaEscolaDominical aula) {
		this.aula = aula;
	}
	public List<AulaEscolaDominical> getAulas() {
		return aulas;
	}
	public void setAulas(List<AulaEscolaDominical> aulas) {
		this.aulas = aulas;
	}
	public AulaEscolaDominicalDao getAulaDao() {
		return aulaDao;
	}
	public void setAulaDao(AulaEscolaDominicalDao aulaDao) {
		this.aulaDao = aulaDao;
	}
	
	/*public AulaEscolaDominicalFiltro getFiltro() {
		if(filtro==null){
			filtro=new AulaEscolaDominicalFiltro();
		}
		return filtro;
	}
	public void setFiltro(AulaEscolaDominicalFiltro filtro) {
		this.filtro = filtro;
	}*/
	
}
