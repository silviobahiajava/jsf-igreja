package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Utils;
import daos.AlunoMatriculadoDao;
import daos.ClasseDao;
import daos.MembroDao;
import entidades.AlunosMatriculadosEscolaDominical;
import entidades.ClasseEscolaDominical;
import entidades.Membro;

@ManagedBean(name="matriculadoBean")
@ViewScoped
public class AlunoMatriculadoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AlunosMatriculadosEscolaDominical matriculado;
	private List<ClasseEscolaDominical> classes;
	private List<Membro> membros;
	
	private List<AlunosMatriculadosEscolaDominical>matriculados;
	
	@PostConstruct
	public void listar() {
		try {
			AlunoMatriculadoDao amdao= new AlunoMatriculadoDao();
			matriculados=amdao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	public void novo(){
		try{
		matriculado=new AlunosMatriculadosEscolaDominical();
		ClasseDao cdao=new ClasseDao();
		MembroDao mdao=new MembroDao();
		classes=cdao.listar();
		membros=mdao.listar();
		}catch(Exception e){
			
		}
		
	}
	
	
	
	public void salvar() {
		
		try {
			AlunoMatriculadoDao amdao= new AlunoMatriculadoDao();
			amdao.merge(matriculado);
			
		    matriculado=new AlunosMatriculadosEscolaDominical();
			ClasseDao cdao=new ClasseDao();
			classes=cdao.listar();
			matriculados=amdao.listar();
			
			
			
			Utils.mostrarMensagemJsfSucesso("aluno matriculado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao tentar matricular este aluno");
			erro.printStackTrace();
		}
	}

	
	public void excluir(ActionEvent evento) {
		try {
			matriculado = (AlunosMatriculadosEscolaDominical) evento.getComponent().getAttributes().get("alunoSelecionado");

			
			AlunoMatriculadoDao amdao=new AlunoMatriculadoDao();
			amdao.excluir(matriculado);
			matriculados=amdao.listar();


			Utils.mostrarMensagemJsfSucesso("matricula cancelada com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel cancelar a matr�cula deste aluno");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		matriculado = (AlunosMatriculadosEscolaDominical) evento.getComponent().getAttributes().get("alunoSelecionado");
		ClasseDao cdao=new ClasseDao();
		classes=cdao.listar();
	}
	public AlunosMatriculadosEscolaDominical getMatriculado() {
		return matriculado;
	}
	public void setMatriculado(AlunosMatriculadosEscolaDominical matriculado) {
		this.matriculado = matriculado;
	}
	public List<AlunosMatriculadosEscolaDominical> getMatriculados() {
		return matriculados;
	}
	public void setMatriculados(List<AlunosMatriculadosEscolaDominical> matriculados) {
		this.matriculados = matriculados;
	}
	public List<ClasseEscolaDominical> getClasses() {
		return classes;
	}
	public void setClasses(List<ClasseEscolaDominical> classes) {
		this.classes = classes;
	}
	public List<Membro> getMembros() {
		return membros;
	}
	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}
	
	
	
}
