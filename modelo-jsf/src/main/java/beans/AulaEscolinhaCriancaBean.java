package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.AulaEscolinhaCriancaDao;
import daos.CriancaMatriculadaNaEscolinhaAusenteDao;
import daos.CriancaMatriculadaNaEscolinhaDao;
import daos.CriancaMatriculadaNaEscolinhaPresenteDao;
import entidades.AulaEscolinhaCrianca;
import entidades.CriancaMatriculadaNaEscolinha;
import entidades.CriancaMatriculadaNaEscolinhaAusente;
import entidades.CriancaMatriculadaNaEscolinhaPresente;
import utils.Utils;
@ManagedBean(name="aulaEscolinhaCriancaBean")
@ViewScoped
public class AulaEscolinhaCriancaBean {
private AulaEscolinhaCrianca aula;
	
	private List<CriancaMatriculadaNaEscolinha>matriculados;//produto
	private List<CriancaMatriculadaNaEscolinhaPresente>presentes;//item
	private List<CriancaMatriculadaNaEscolinhaAusente>ausentes;//item
	private List<AulaEscolinhaCrianca>aulas;//venda
	private CriancaMatriculadaNaEscolinha matriculado;
	private int totalDeAlunosPresentes;
	private int totalDeAlunosAusentes;
	private int totalDeMatriculados;
	
	
	//metodos
	public void listar() {
		try {
			
			aula=new AulaEscolinhaCrianca();
			
			CriancaMatriculadaNaEscolinhaDao matriculadaDao=new CriancaMatriculadaNaEscolinhaDao();
			matriculados=matriculadaDao.listar();
			
			
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	
	
	public void definirPresenca(CriancaMatriculadaNaEscolinha matriculado){
		//presentes=new ArrayList<AlunosPresentesNaEscolaDominical>();
		  CriancaMatriculadaNaEscolinhaPresente alunoPresente=new CriancaMatriculadaNaEscolinhaPresente();
		alunoPresente.setMatriculado(matriculado);
		///alunoPresente.setMatriculado(matriculado);
		alunoPresente.setAula(aula);
		presentes.add(alunoPresente);
	
		Utils.mostrarMensagemJsfSucesso("o aluno "+alunoPresente.getMatriculado().getCrianca().getNome()+" está presente");
		
	}
	
	public void definirFalta(CriancaMatriculadaNaEscolinha matriculado){
		//presentes=new ArrayList<AlunosPresentesNaEscolaDominical>();
		  CriancaMatriculadaNaEscolinhaAusente alunoAusente=new CriancaMatriculadaNaEscolinhaAusente();
		alunoAusente.setMatriculado(matriculado);
		///alunoPresente.setMatriculado(matriculado);
		alunoAusente.setAula(aula);
		ausentes.add(alunoAusente);
	
		Utils.mostrarMensagemJsfSucesso("o aluno "+alunoAusente.getMatriculado().getCrianca().getNome()+" está ausente");
		
	}
	public void  totalizarMatriculados(){
		totalDeMatriculados=matriculados.size();
	}
	
public void registrarAula(){
		
		try{
			
			AulaEscolinhaCriancaDao aulaDao=new AulaEscolinhaCriancaDao();
			Long codigoAula=aulaDao.salvarAula(aula);
			AulaEscolinhaCrianca aulaFk=aulaDao.buscar(codigoAula);
			for(CriancaMatriculadaNaEscolinhaPresente presente:presentes){
				presente.setAula(aulaFk);
				CriancaMatriculadaNaEscolinhaPresenteDao presenteDao=new CriancaMatriculadaNaEscolinhaPresenteDao();
				presenteDao.salvar(presente);
			}
			for(CriancaMatriculadaNaEscolinhaAusente ausente:ausentes){
				ausente.setAula(aulaFk);
				 CriancaMatriculadaNaEscolinhaAusenteDao ausenteDao=new CriancaMatriculadaNaEscolinhaAusenteDao();
				ausenteDao.salvar(ausente);
			}
			 aula=new AulaEscolinhaCrianca();
			Utils.mostrarMensagemJsfSucesso("aula registrada "+codigoAula);
			
		}catch(RuntimeException e){
			
		}
		
	}
	public void salvar() {
		try {
			
			
			
			AulaEscolinhaCriancaDao aulaDao=new AulaEscolinhaCriancaDao();
			//aula.setAlunosAusentes(ausentes);
			//aula.setAlunosPresentes(presentes);
			/*aula.setClasse(classe);
			aula.setTotalAlunosAusentes(totalDeAlunosAusentes);
			aula.setTotalAlunosPresentes(totalDeAlunosPresentes);
			aula.setTotalMatriculado(totalDeMatriculados);
			aula.setAlunosAusentes(ausentes);
			aula.setAlunosPresentes(presentes);
			
			
			aula.setTotalBiblias(aula.getTotalBiblias());
			aula.setTotalRevistas(aula.getTotalRevistas());
			
			aula.setTotalOfertas(new BigDecimal("20.00"));
			*/
			aula.setTotalMatriculado(totalDeMatriculados);
			aula.setTotalCriancasAusentes(totalDeAlunosAusentes);
			aula.setTotalCriancasPresentes(totalDeAlunosPresentes);
			
			aulaDao.merge(aula);
			aula=new AulaEscolinhaCrianca();
			aulas=aulaDao.listar();
			
			Utils.mostrarMensagemJsfSucesso("aula registrada com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao registrar esta aula");
			erro.printStackTrace();
		}
	}

	
	public void excluir(ActionEvent evento) {
		try {
			aula = (AulaEscolinhaCrianca) evento.getComponent().getAttributes().get("aulaEscolinhaSelecionada");

			AulaEscolinhaCriancaDao aulaDao=new AulaEscolinhaCriancaDao();
			aulaDao.excluir(aula);
			
			aulas=aulaDao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("não foi possível excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		aula = (AulaEscolinhaCrianca) evento.getComponent().getAttributes().get("aulaEscolinhaSelecionada");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<CriancaMatriculadaNaEscolinha> getMatriculados() {
		return matriculados;
	}
	public void setMatriculados(List<CriancaMatriculadaNaEscolinha> matriculados) {
		this.matriculados = matriculados;
	}
	public List<CriancaMatriculadaNaEscolinhaPresente> getPresentes() {
		return presentes;
	}
	public void setPresentes(List<CriancaMatriculadaNaEscolinhaPresente> presentes) {
		this.presentes = presentes;
	}
	public List<CriancaMatriculadaNaEscolinhaAusente> getAusentes() {
		return ausentes;
	}
	public void setAusentes(List<CriancaMatriculadaNaEscolinhaAusente> ausentes) {
		this.ausentes = ausentes;
	}
	public List<AulaEscolinhaCrianca> getAulas() {
		return aulas;
	}
	public void setAulas(List<AulaEscolinhaCrianca> aulas) {
		this.aulas = aulas;
	}
	public CriancaMatriculadaNaEscolinha getMatriculado() {
		return matriculado;
	}
	public void setMatriculado(CriancaMatriculadaNaEscolinha matriculado) {
		this.matriculado = matriculado;
	}
	public int getTotalDeAlunosPresentes() {
		return totalDeAlunosPresentes;
	}
	public void setTotalDeAlunosPresentes(int totalDeAlunosPresentes) {
		this.totalDeAlunosPresentes = totalDeAlunosPresentes;
	}
	public int getTotalDeAlunosAusentes() {
		return totalDeAlunosAusentes;
	}
	public void setTotalDeAlunosAusentes(int totalDeAlunosAusentes) {
		this.totalDeAlunosAusentes = totalDeAlunosAusentes;
	}
	public int getTotalDeMatriculados() {
		return totalDeMatriculados;
	}
	public void setTotalDeMatriculados(int totalDeMatriculados) {
		this.totalDeMatriculados = totalDeMatriculados;
	}


	public AulaEscolinhaCrianca getAula() {
		return aula;
	}


	public void setAula(AulaEscolinhaCrianca aula) {
		this.aula = aula;
	}
	
	
}
