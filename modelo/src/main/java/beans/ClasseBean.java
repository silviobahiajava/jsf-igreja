package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Utils;
import daos.ClasseDao;
import entidades.ClasseEscolaDominical;

@ManagedBean(name="classeBean")
@ViewScoped
public class ClasseBean {
	private List<ClasseEscolaDominical>classes;
	private ClasseEscolaDominical classe;
	@PostConstruct
	public void listar() {
		try {
			ClasseDao classeDao=new ClasseDao();
			classes= classeDao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	public void novo(){
		classe=new ClasseEscolaDominical();
		
	}
	
	
	
	public void salvar() {
		try {
			ClasseDao cdao=new ClasseDao();
			
			cdao.merge(classe);
			classe=new ClasseEscolaDominical();
			
			classes=cdao.listar();
			
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		}
	}

	
	public void excluir(ActionEvent evento) {
		try {
			classe = (ClasseEscolaDominical) evento.getComponent().getAttributes().get("classeSelecionado");
             ClasseDao cdao=new ClasseDao();
			cdao.excluir(classe);
			classes=cdao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("não foi possível excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		classe = (ClasseEscolaDominical) evento.getComponent().getAttributes().get("classeSelecionado");
	}
	public List<ClasseEscolaDominical> getClasses() {
		return classes;
	}
	public void setClasses(List<ClasseEscolaDominical> classes) {
		this.classes = classes;
	}
	public ClasseEscolaDominical getClasse() {
		return classe;
	}
	public void setClasse(ClasseEscolaDominical classe) {
		this.classe = classe;
	}
	
	
}
