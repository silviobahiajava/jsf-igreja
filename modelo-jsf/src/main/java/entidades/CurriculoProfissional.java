package entidades;

import java.util.Date;
import javax.persistence.*;
@Entity
public class CurriculoProfissional extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	private String nomeCompleto;
	private String email;
	private String telefoneCelular;
	private String telefoneResidencial;
	private String endereco;
	private String numeroEndereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String objetivoCurriculo;
	private String perfilProfissional;
	private String cursoFormcaoAcademica;
	private String periodoFormacaoAcademica;
	private String escolaFormacaoAcademica;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	
	private ExperienciaProfissional experienciaProfissional;
	private CursoProfissional cursoProfissional;
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}
	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumeroEndereco() {
		return numeroEndereco;
	}
	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getObjetivoCurriculo() {
		return objetivoCurriculo;
	}
	public void setObjetivoCurriculo(String objetivoCurriculo) {
		this.objetivoCurriculo = objetivoCurriculo;
	}
	public String getPerfilProfissional() {
		return perfilProfissional;
	}
	public void setPerfilProfissional(String perfilProfissional) {
		this.perfilProfissional = perfilProfissional;
	}
	public String getCursoFormcaoAcademica() {
		return cursoFormcaoAcademica;
	}
	public void setCursoFormcaoAcademica(String cursoFormcaoAcademica) {
		this.cursoFormcaoAcademica = cursoFormcaoAcademica;
	}
	public String getPeriodoFormacaoAcademica() {
		return periodoFormacaoAcademica;
	}
	public void setPeriodoFormacaoAcademica(String periodoFormacaoAcademica) {
		this.periodoFormacaoAcademica = periodoFormacaoAcademica;
	}
	
	/*public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public String getNomedocurso() {
		return nomedocurso;
	}
	public void setNomedocurso(String nomedocurso) {
		this.nomedocurso = nomedocurso;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataConclusao() {
		return dataConclusao;
	}
	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public String getNomeDaEmpresa1() {
		return nomeDaEmpresa1;
	}
	public void setNomeDaEmpresa1(String nomeDaEmpresa1) {
		this.nomeDaEmpresa1 = nomeDaEmpresa1;
	}
	public String getCargo1() {
		return cargo1;
	}
	public void setCargo1(String cargo1) {
		this.cargo1 = cargo1;
	}
	public Date getDataDeAdmmissao1() {
		return dataDeAdmmissao1;
	}
	public void setDataDeAdmmissao1(Date dataDeAdmmissao1) {
		this.dataDeAdmmissao1 = dataDeAdmmissao1;
	}
	public Date getDataDeDemissao1() {
		return dataDeDemissao1;
	}
	public void setDataDeDemissao1(Date dataDeDemissao1) {
		this.dataDeDemissao1 = dataDeDemissao1;
	}
	public String getFuncoesRealizadas1() {
		return funcoesRealizadas1;
	}
	public void setFuncoesRealizadas1(String funcoesRealizadas1) {
		this.funcoesRealizadas1 = funcoesRealizadas1;
	}
	public String getNomeDaEmpresa2() {
		return nomeDaEmpresa2;
	}
	public void setNomeDaEmpresa2(String nomeDaEmpresa2) {
		this.nomeDaEmpresa2 = nomeDaEmpresa2;
	}
	public String getCargo2() {
		return cargo2;
	}
	public void setCargo2(String cargo2) {
		this.cargo2 = cargo2;
	}
	public Date getDataDeAdmmissao2() {
		return dataDeAdmmissao2;
	}
	public void setDataDeAdmmissao2(Date dataDeAdmmissao2) {
		this.dataDeAdmmissao2 = dataDeAdmmissao2;
	}
	public Date getDataDeDemissao2() {
		return dataDeDemissao2;
	}
	public void setDataDeDemissao2(Date dataDeDemissao2) {
		this.dataDeDemissao2 = dataDeDemissao2;
	}
	public String getFuncoesRealizadas2() {
		return funcoesRealizadas2;
	}
	public void setFuncoesRealizadas2(String funcoesRealizadas2) {
		this.funcoesRealizadas2 = funcoesRealizadas2;
	}
	public String getNomeDaEmpresa3() {
		return nomeDaEmpresa3;
	}
	public void setNomeDaEmpresa3(String nomeDaEmpresa3) {
		this.nomeDaEmpresa3 = nomeDaEmpresa3;
	}
	public String getCargo3() {
		return cargo3;
	}
	public void setCargo3(String cargo3) {
		this.cargo3 = cargo3;
	}
	public Date getDataDeAdmmissao3() {
		return dataDeAdmmissao3;
	}
	public void setDataDeAdmmissao3(Date dataDeAdmmissao3) {
		this.dataDeAdmmissao3 = dataDeAdmmissao3;
	}
	public Date getDataDeDemissao3() {
		return dataDeDemissao3;
	}
	public void setDataDeDemissao3(Date dataDeDemissao3) {
		this.dataDeDemissao3 = dataDeDemissao3;
	}
	public String getFuncoesRealizadas3() {
		return funcoesRealizadas3;
	}
	public void setFuncoesRealizadas3(String funcoesRealizadas3) {
		this.funcoesRealizadas3 = funcoesRealizadas3;
	}
	public String getIdade() {
		return idade;
	}
	*/
	
	public String getEscolaFormacaoAcademica() {
		return escolaFormacaoAcademica;
	}
	public void setEscolaFormacaoAcademica(String escolaFormacaoAcademica) {
		this.escolaFormacaoAcademica = escolaFormacaoAcademica;
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
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
}
