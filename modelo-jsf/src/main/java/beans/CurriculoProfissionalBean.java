package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;

import daos.CurriculoProfissionalDao;
import entidades.CurriculoProfissional;
import entidades.CursoProfissional;
import entidades.ExperienciaProfissional;
import entidades.Relatorios;
import utils.Utils;

@ManagedBean(name="curriculoprofissionalBean")
@ViewScoped
public class CurriculoProfissionalBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ExperienciaProfissional experienciaProfissional=new ExperienciaProfissional();
	private CursoProfissional cursoProfissional=new CursoProfissional();
	private CurriculoProfissional curriculoProfissional=new CurriculoProfissional();
	
	private List<CurriculoProfissional>listaCurriculos=new ArrayList<CurriculoProfissional>();
	
	private  List<CursoProfissional>listaCursos=new ArrayList<CursoProfissional>();
	private List<ExperienciaProfissional>listaExperienciaProfissional=new ArrayList<ExperienciaProfissional>();
	
	@PostConstruct
	public void init(){
		curriculoProfissional=new CurriculoProfissional();
		cursoProfissional=new CursoProfissional();
		experienciaProfissional=new ExperienciaProfissional();
		listaCursos=new ArrayList<CursoProfissional>();
		listaExperienciaProfissional=new ArrayList<ExperienciaProfissional>();
	}
	
	public void adicionarCursos(){
		
		cursoProfissional.setNomeCurso(cursoProfissional.getNomeCurso());
		cursoProfissional.setInstituicaoCurso(cursoProfissional.getInstituicaoCurso());
		cursoProfissional.setDataInicioCurso(cursoProfissional.getDataInicioCurso());
		cursoProfissional.setDataConclusaoCurso(cursoProfissional.getDataConclusaoCurso());
		listaCursos.add(cursoProfissional);
	//	cursoProfissional=new CursoProfissional();
		//listaCursos=new ArrayList<>();
		limparCamposCurso();
	}
	
	public void adicionarExperienciaProfissional(){
		
		experienciaProfissional.setNomeDaEmpresa(experienciaProfissional.getNomeDaEmpresa());
		experienciaProfissional.setCargo(experienciaProfissional.getCargo());
		experienciaProfissional.setDataDeAdmmissao(experienciaProfissional.getDataDeAdmmissao());
		experienciaProfissional.setFuncoesRealizadas(experienciaProfissional.getFuncoesRealizadas());
		listaExperienciaProfissional.add(experienciaProfissional);
	//	experienciaProfissional=new ExperienciaProfissional();
		//listaExperienciaProfissional=new ArrayList<>();
	}
	public void limparCamposExperienciaProfissional(){
		if(experienciaProfissional!=null){
			experienciaProfissional=new ExperienciaProfissional();
		}
	}
	public void limparCamposCurso(){
		if(cursoProfissional!=null){
			cursoProfissional=new CursoProfissional();
		}
	}
	
	 public void registrarCurriculo(){
		listaCurriculos.add(curriculoProfissional);
		Relatorios.mostrarCurriculoNaWeb(listaCurriculos);
	}
	
	
	
	
	public CurriculoProfissional getCurriculoProfissional() {
		return curriculoProfissional;
	}
	public void setCurriculoProfissional(CurriculoProfissional curriculoProfissional) {
		this.curriculoProfissional = curriculoProfissional;
	}
	public List<CurriculoProfissional> getListaCurriculos() {
		return listaCurriculos;
	}
	public void setListaCurriculos(List<CurriculoProfissional> listaCurriculos) {
		this.listaCurriculos = listaCurriculos;
	}
	public ExperienciaProfissional getExperienciaProfissional() {
		return experienciaProfissional;
	}
	public void setExperienciaProfissional(ExperienciaProfissional experienciaProfissional) {
		this.experienciaProfissional = experienciaProfissional;
	}
	public CursoProfissional getCursoProfissional() {
		return cursoProfissional;
	}
	public void setCursoProfissional(CursoProfissional cursoProfissional) {
		this.cursoProfissional = cursoProfissional;
	}
	public List<CursoProfissional> getListaCursos() {
		return listaCursos;
	}
	public void setListaCursos(List<CursoProfissional> listaCursos) {
		this.listaCursos = listaCursos;
	}
	public List<ExperienciaProfissional> getListaExperienciaProfissional() {
		return listaExperienciaProfissional;
	}
	public void setListaExperienciaProfissional(List<ExperienciaProfissional> listaExperienciaProfissional) {
		this.listaExperienciaProfissional = listaExperienciaProfissional;
	}
	
	
	public void salvarCurriculo() {
		
		try {
			CurriculoProfissionalDao dao=new CurriculoProfissionalDao();
			curriculoProfissional.setNomeCompleto(curriculoProfissional.getNomeCompleto());
			curriculoProfissional.setTelefoneCelular(curriculoProfissional.getTelefoneCelular());
			curriculoProfissional.setTelefoneResidencial(curriculoProfissional.getTelefoneResidencial());
			curriculoProfissional.setEndereco(curriculoProfissional.getEndereco());
			curriculoProfissional.setNumeroEndereco(curriculoProfissional.getNumeroEndereco());
			curriculoProfissional.setBairro(curriculoProfissional.getBairro());
			curriculoProfissional.setCidade(curriculoProfissional.getCidade());
			curriculoProfissional.setEstado(curriculoProfissional.getEstado());
			curriculoProfissional.setObjetivoCurriculo(curriculoProfissional.getObjetivoCurriculo());
			curriculoProfissional.setPerfilProfissional(curriculoProfissional.getPerfilProfissional());
			curriculoProfissional.setCursoFormcaoAcademica(curriculoProfissional.getCursoFormcaoAcademica());
			curriculoProfissional.setPeriodoFormacaoAcademica(curriculoProfissional.getPeriodoFormacaoAcademica());
			curriculoProfissional.setEscolaFormacaoAcademica(curriculoProfissional.getEscolaFormacaoAcademica());
			for(CursoProfissional curso:listaCursos){
				  curriculoProfissional.setCursoProfissional(curso);
			  }
			  for(ExperienciaProfissional experiencia:listaExperienciaProfissional){
				  curriculoProfissional.setExperienciaProfissional(experiencia);
			  }
			  listaCurriculos.add(curriculoProfissional);
			
			//dao.salvarCurriculoProfissional(curriculoProfissional, listaExperienciaProfissional, listaCursos);
			Utils.mostrarMensagemJsfSucesso("curriculo registrado com sucesso");
			Relatorios.mostrarCurriculo(listaCurriculos);
			//gerarCurriculoItext();
			curriculoProfissional=new CurriculoProfissional();
		//Faces.redirect("./secretaria/curriculos.xhtml");
		} 
		catch (RuntimeException  e) {
			Utils.mostrarMensagemJsfErro("erro ao registrar o curriculo");
			e.printStackTrace();
		}
	}
	
	public void gerarCurriculoItext(){
		CurriculoProfissionalDao dao=new CurriculoProfissionalDao();
		Long codigo=dao.buscaUlitmoCodigo();
		CurriculoProfissional curriculo=dao.buscar(codigo);
		String cabecalho="CURRICULO PROFISSIONAL";
		String texto="";
		String nome=curriculo.getNomeCompleto();
		String perfil=curriculo.getPerfilProfissional();
		String objetivo=curriculo.getObjetivoCurriculo();
		String telefoneResidencial=curriculo.getTelefoneResidencial();
		String telefoneCelular=curriculo.getTelefoneCelular();
		String empresa=curriculo.getExperienciaProfissional().getNomeDaEmpresa();
		texto+=nome+"\n"+perfil+"\n"+objetivo+"\n"+telefoneResidencial+" "+telefoneCelular+"\n"+empresa;
		Relatorios.gerarPDFItext(cabecalho, texto);
	
		
	}
	
}
