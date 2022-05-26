package entidades;
import enums.EstadoCivil;
import enums.Profissao;
import javax.persistence.*;
@Entity
@Table(name="curriculo")
public class Curriculo extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	private Profissao profisaoAtual;
	private String nomeCompleto;
	private String email;
	private String telefoneCelular;
	private String telefoneResidencial;
	private String cep;
	private String endereco;
	private String numeroEndereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String objetivoCurriculo;
	private String perfilProfissional;
	
	private String nomeEscola;
	private String nomeCurso;
	
	private String informacoesAdicionais;
	private String idade;
	private Profissao cargoPretendido;
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	@Transient
	private String caminho;
	
	private boolean desempregado;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private EmpresaCurriculo empresa;

	

	public Profissao getProfisaoAtual() {
		return profisaoAtual;
	}

	public void setProfisaoAtual(Profissao profisaoAtual) {
		this.profisaoAtual = profisaoAtual;
	}

	public boolean isDesempregado() {
		return desempregado;
	}

	public void setDesempregado(boolean desempregado) {
		this.desempregado = desempregado;
	}

	public EmpresaCurriculo getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaCurriculo empresa) {
		this.empresa = empresa;
	}

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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public Profissao getCargoPretendido() {
		return cargoPretendido;
	}

	public void setCargoPretendido(Profissao cargoPretendido) {
		this.cargoPretendido = cargoPretendido;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	
	
	
	
	
	

}
