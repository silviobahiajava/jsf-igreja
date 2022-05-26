package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.AlunoAusenteDao;
import daos.AlunoMatriculadoDao;
import daos.AlunosPresentesDao;
import daos.AulaEscolaDominicalDao;
import daos.ClasseDao;
import entidades.AlunosAusentesDaEscolaDominical;
import entidades.AlunosMatriculadosEscolaDominical;
import entidades.AlunosPresentesNaEscolaDominical;
import entidades.AulaEscolaDominical;
import entidades.ClasseEscolaDominical;
import entidades.Grupo;

@ManagedBean(name="aulaEscolaDominicalBean")
@ViewScoped
public class AulaEscolaDominicalBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AulaEscolaDominical aula;
	
	private List<AlunosMatriculadosEscolaDominical>matriculados;//produto
	private List<AlunosPresentesNaEscolaDominical>presentes;//item
	private List<AlunosAusentesDaEscolaDominical>ausentes;//item
	private List<AulaEscolaDominical>aulas;//venda
	private List<ClasseEscolaDominical>classes;//
	private ClasseEscolaDominical classe;
	
	private ClasseDao cdao=new ClasseDao();
	private AlunosMatriculadosEscolaDominical matriculado;
	private int totalDeAlunosPresentes;
	private int totalDeAlunosAusentes;
	private int totalDeMatriculados;
	
	
	@PostConstruct
	public void listar() {
		try {
			
			aula=new AulaEscolaDominical();
			
			AlunoMatriculadoDao amdao=new AlunoMatriculadoDao();
			matriculados=amdao.listar();
			classes=cdao.listar();
			
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	
//	public void novo(){
//		aula=new AulaEscolaDominical();
//		classe=new ClasseEscolaDominical();
//		ausentes=new ArrayList<AlunosAusentesDaEscolaDominical>();
//		presentes=new ArrayList<AlunosPresentesNaEscolaDominical>();
//	}
	
	/*
	 * 
	 * public void adicionar(ActionEvent evento){
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setPrecoParcial(produto.getPreco());
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(new Short("1"));
		
		itensVenda.add(itemVenda);
	}
	 * 
	 * 
	 * 
	 */
	public void definirPresenca(AlunosMatriculadosEscolaDominical matriculado){
		//presentes=new ArrayList<AlunosPresentesNaEscolaDominical>();
		AlunosPresentesNaEscolaDominical alunoPresente=new AlunosPresentesNaEscolaDominical();
		alunoPresente.setMatriculado(matriculado);
		presentes.add(alunoPresente);
		totalizarAlunosPresentes();
		Utils.mostrarMensagemJsfSucesso("o aluno "+alunoPresente.getMatriculado().getMembro().getNome()+" est� presente");
		
	}
	
	public void definirFalta(AlunosMatriculadosEscolaDominical matriculado){
		//ausentes=new ArrayList<AlunosAusentesDaEscolaDominical>();
		AlunosAusentesDaEscolaDominical alunoAusente=new AlunosAusentesDaEscolaDominical();
		alunoAusente.setMatriculado(matriculado);
		ausentes.add(alunoAusente);
		totalizarAlunosAusentes();
		Utils.mostrarMensagemJsfSucesso("lista de alunos ausentes");
	}
	
	public void  totalizarMatriculados(){
		totalDeMatriculados=matriculados.size();
	}
	
	
	
	
	
	
	
	
	
	
	
public List<AlunosPresentesNaEscolaDominical> getPresentes() {
		if(presentes==null){
			presentes=new ArrayList<AlunosPresentesNaEscolaDominical>();
		}
		return presentes;
	}

	public void setPresentes(List<AlunosPresentesNaEscolaDominical> presentes) {
		this.presentes = presentes;
	}

	public List<AlunosAusentesDaEscolaDominical> getAusentes() {
		if(ausentes==null){
			ausentes=new ArrayList<AlunosAusentesDaEscolaDominical>();
		}
		return ausentes;
	}

	public void setAusentes(List<AlunosAusentesDaEscolaDominical> ausentes) {
		this.ausentes = ausentes;
	}

	//	public void adicionarFalta(ActionEvent evento){
//		ausentes=new ArrayList<AlunosMatriculadosEscolaDominical>();
//		matriculado = (AlunosMatriculadosEscolaDominical) evento.getComponent().getAttributes().get("matriculadoSelecionado");
//		ausentes.add(matriculado);
//	}
//	
//	public void adicinoarPresenca(ActionEvent evento){
//		presentes=new ArrayList<AlunosMatriculadosEscolaDominical>();
//		matriculado = (AlunosMatriculadosEscolaDominical) evento.getComponent().getAttributes().get("matriculadoSelecionado");
//		presentes.add(matriculado);
//	}
	public void totalizarAlunosPresentes(){
		totalDeAlunosPresentes=presentes.size();
	}
	public void totalizarAlunosAusentes(){
		totalDeAlunosAusentes=ausentes.size();
	}
	
	public void registrarAula(){
		
		try{
			
			AulaEscolaDominicalDao aulaDao=new AulaEscolaDominicalDao();
//			Grupo grupo=new Grupo();
//			grupo.setCodigo(7L);
//			aula.getGrupo();
			Long codigoVenda=aulaDao.salvarAula(aula);
			
			
			AulaEscolaDominical aulaFk=aulaDao.buscar(codigoVenda);
			for(AlunosPresentesNaEscolaDominical presente:presentes){
				presente.setAula(aulaFk);
				AlunosPresentesDao presenteDao=new AlunosPresentesDao();
				presenteDao.salvar(presente);
			}
			for(AlunosAusentesDaEscolaDominical ausente:ausentes){
				ausente.setAula(aulaFk);
				AlunoAusenteDao ausenteDao=new AlunoAusenteDao();
				ausenteDao.salvar(ausente);
			}
			 aula=new AulaEscolaDominical();
			Utils.mostrarMensagemJsfSucesso("aula registrada "+codigoVenda);
			
		}catch(RuntimeException e){
			
		}
		
	}
	public void salvar() {
		try {
			
			
			
			AulaEscolaDominicalDao aulaDao=new AulaEscolaDominicalDao();
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
			aula.setTotalAlunosAusentes(totalDeAlunosAusentes);
			aula.setTotalAlunosPresentes(totalDeAlunosPresentes);
			aulaDao.merge(aula);
			aula=new AulaEscolaDominical();
			aulas=aulaDao.listar();
			
			Utils.mostrarMensagemJsfSucesso("aula registrada com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao registrar esta aula");
			erro.printStackTrace();
		}
	}

	
	public void excluir(ActionEvent evento) {
		try {
			aula = (AulaEscolaDominical) evento.getComponent().getAttributes().get("aulaSelecionado");

			AulaEscolaDominicalDao aulaDao=new AulaEscolaDominicalDao();
			aulaDao.excluir(aula);
			
			aulas=aulaDao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		aula = (AulaEscolaDominical) evento.getComponent().getAttributes().get("aulaSelecionado");
	}
	public AulaEscolaDominical getAula() {
		return aula;
	}
	public void setAula(AulaEscolaDominical aula) {
		this.aula = aula;
	}
	
	public List<AlunosMatriculadosEscolaDominical> getMatriculados() {
		return matriculados;
	}
	public void setMatriculados(List<AlunosMatriculadosEscolaDominical> matriculados) {
		this.matriculados = matriculados;
	}
	
	public List<AulaEscolaDominical> getAulas() {
		return aulas;
	}
	public void setAulas(List<AulaEscolaDominical> aulas) {
		this.aulas = aulas;
	}
	public ClasseEscolaDominical getClasse() {
		return classe;
	}
	public void setClasse(ClasseEscolaDominical classe) {
		this.classe = classe;
	}
	public AlunosMatriculadosEscolaDominical getMatriculado() {
		return matriculado;
	}
	public void setMatriculado(AlunosMatriculadosEscolaDominical matriculado) {
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

	public List<ClasseEscolaDominical> getClasses() {
		return classes;
	}

	public void setClasses(List<ClasseEscolaDominical> classes) {
		this.classes = classes;
	}
	
	///novos metodos
	public void popular(){
		
			//if(aula.getClasse()!=null){
				AlunoMatriculadoDao matriculadoDao=new AlunoMatriculadoDao();
				matriculados=matriculadoDao.buscarPorClasse(aula.getClasse().getCodigo());
				//Utils.mostrarMensagemSwing("classe selecionada "+aula.getClasse().getNomeClasse());
				totalizarMatriculados();
				//Utils.mostrarMensagemSwing("total de alunos desta classe "+matriculados.size());
				Utils.mostrarMensagemJsfSucesso("classe selecionada "+aula.getClasse().getNomeClasse());
				Utils.mostrarMensagemJsfSucesso("total de alunos matriculados "+matriculados.size());
				
			//}
			
		
		
	}
	
	public void resumoAula(){
		Utils.mostrarMensagemJsfSucesso("ver o resumo ");
		Utils.mostrarMensagemJsfSucesso("total de alunos presentes "+totalDeAlunosPresentes);
	}
}
