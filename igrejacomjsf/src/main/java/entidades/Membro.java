package entidades;




import java.util.Date;

import javax.persistence.*;

import enums.BairroCidadeEnum;
import enums.CargoEnum;
import enums.CidadesNorteParanaEnum;
import enums.Congregacao;
import enums.EstadoCivil;
import enums.EstadosBrasileirosEnum;
import enums.MotivoExclusaodeMembrosEnum;
import enums.MotivoInatividadeEnum;
import enums.TipoCadastroEnum;


@Entity
@Table(name="membro")
public class Membro extends GenericDomain{
	private static final long serialVersionUID = 1L;
	private String nome;
	
	private String nacionalidade;
	private String natualidade;
	
	private String localDeBatismo;
	private String estadoBatismo;
	@Enumerated(EnumType.STRING)
	private EstadosBrasileirosEnum estadosBrasileirosEnum;
	private String status;
	
	@Enumerated(EnumType.STRING)
	private MotivoExclusaodeMembrosEnum motivoExclusaoDeMembrosEnum;
	
	private String justificativaDeExclusao;
	
	
	private Long codigoMotivoInatividade;
	
	@Enumerated(EnumType.STRING)
	private MotivoInatividadeEnum motivoSaida;
	
	@Enumerated(EnumType.STRING)
	private CidadesNorteParanaEnum cidadeEnum;
	
	@Temporal(TemporalType.DATE)
	private Date validadeCarterinha;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Enumerated(EnumType.STRING)
	private BairroCidadeEnum bairroEnum;
	
	private String rg;
	private String statusMembro;
	
	
	private String cpf;
	@Transient
	private String caminho;
	
	@Lob
	private byte[]foto;
	
	/*
	 * no managendbean
	 * private Part arquivofoto;
	 * private Membrmo membro
	 * public void salvar(){
	 * 	Byte[]arquivobyte=(convertido em array de byte)arquivofoto.getInputStream();
	 * membro.setFoto(arquivobyte);
	 * dao.salvar(membro);
	 *testar  amanh�
	*/
	
//	@ManyToOne
//	@JoinColumn(nullable=false)
//	private Cargo cargo;
	
	private String tipoCargo;
	
	private String nomedamae;
	private String nomedopai;
	private int statusCadastro;
	
	@Enumerated(EnumType.STRING)
	private TipoCadastroEnum tipoCadastro;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private Endereco endereco;
	

	@Temporal(TemporalType.DATE)
	private Date dataBatismo;
	private String cidadeBatismo;
	private String localBatismo;
	private String nomeMae;
	private String nomePai;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private Contato contato;///classe
	
	@OneToOne(cascade=CascadeType.ALL)
	private Filiacao filiacao;
	
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;//enum
	
	@Enumerated(EnumType.STRING)
	private CargoEnum cargoEnum;//enum
	
	@Enumerated(EnumType.STRING)
	private Congregacao congregacao;//enum
	
	
	
	
	//conta bancaria do membro pra gerar boleto
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	
	public String getEstadoBatismo() {
		return estadoBatismo;
	}
	public void setEstadoBatismo(String estadoBatismo) {
		this.estadoBatismo = estadoBatismo;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Congregacao getCongregacao() {
		return congregacao;
	}
	public void setCongregacao(Congregacao congregacao) {
		this.congregacao = congregacao;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Filiacao getFiliacao() {
		return filiacao;
	}
	public void setFiliacao(Filiacao filiacao) {
		this.filiacao = filiacao;
	}
	
	
//	public Cargo getCargo() {
//		return cargo;
//	}
//	public void setCargo(Cargo cargo) {
//		this.cargo = cargo;
//	}
	
	
	public Endereco getEndereco() {
		return endereco;
	}
	public CargoEnum getCargoEnum() {
		return cargoEnum;
	}
	public void setCargoEnum(CargoEnum cargoEnum) {
		this.cargoEnum = cargoEnum;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
	
	public Date getDataBatismo() {
		return dataBatismo;
	}
	public void setDataBatismo(Date dataBatismo) {
		this.dataBatismo = dataBatismo;
	}
	
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getNatualidade() {
		return natualidade;
	}
	public void setNatualidade(String natualidade) {
		this.natualidade = natualidade;
	}
	public Date getValidadeCarterinha() {
		return validadeCarterinha;
	}
	public void setValidadeCarterinha(Date validadeCarterinha) {
		this.validadeCarterinha = validadeCarterinha;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNomedamae() {
		return nomedamae;
	}
	public void setNomedamae(String nomedamae) {
		this.nomedamae = nomedamae;
	}
	public String getNomedopai() {
		return nomedopai;
	}
	public void setNomedopai(String nomedopai) {
		this.nomedopai = nomedopai;
	}
	public EstadosBrasileirosEnum getEstadosBrasileirosEnum() {
		return estadosBrasileirosEnum;
	}
	public void setEstadosBrasileirosEnum(EstadosBrasileirosEnum estadosBrasileirosEnum) {
		this.estadosBrasileirosEnum = estadosBrasileirosEnum;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getTipoCargo() {
		return tipoCargo;
	}
	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}
	public String getStatusMembro() {
		return statusMembro;
	}
	public void setStatusMembro(String statusMembro) {
		this.statusMembro = statusMembro;
	}
	public BairroCidadeEnum getBairroEnum() {
		return bairroEnum;
	}
	public void setBairroEnum(BairroCidadeEnum bairroEnum) {
		this.bairroEnum = bairroEnum;
	}
	public CidadesNorteParanaEnum getCidadeEnum() {
		return cidadeEnum;
	}
	public void setCidadeEnum(CidadesNorteParanaEnum cidadeEnum) {
		this.cidadeEnum = cidadeEnum;
	}
	public Long getCodigoMotivoInatividade() {
		return codigoMotivoInatividade;
	}
	public void setCodigoMotivoInatividade(Long codigoMotivoInatividade) {
		this.codigoMotivoInatividade = codigoMotivoInatividade;
	}
	public MotivoInatividadeEnum getMotivoSaida() {
		return motivoSaida;
	}
	public void setMotivoSaida(MotivoInatividadeEnum motivoSaida) {
		this.motivoSaida = motivoSaida;
	}
	public MotivoExclusaodeMembrosEnum getMotivoExclusaoDeMembrosEnum() {
		return motivoExclusaoDeMembrosEnum;
	}
	public void setMotivoExclusaoDeMembrosEnum(MotivoExclusaodeMembrosEnum motivoExclusaoDeMembrosEnum) {
		this.motivoExclusaoDeMembrosEnum = motivoExclusaoDeMembrosEnum;
	}
	public String getJustificativaDeExclusao() {
		return justificativaDeExclusao;
	}
	public void setJustificativaDeExclusao(String justificativaDeExclusao) {
		this.justificativaDeExclusao = justificativaDeExclusao;
	}
	public String getLocalDeBatismo() {
		return localDeBatismo;
	}
	public void setLocalDeBatismo(String localDeBatismo) {
		this.localDeBatismo = localDeBatismo;
	}
	public String getCidadeBatismo() {
		return cidadeBatismo;
	}
	public void setCidadeBatismo(String cidadeBatismo) {
		this.cidadeBatismo = cidadeBatismo;
	}
	public String getLocalBatismo() {
		return localBatismo;
	}
	public void setLocalBatismo(String localBatismo) {
		this.localBatismo = localBatismo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getStatusCadastro() {
		return statusCadastro;
	}
	public void setStatusCadastro(int statusCadastro) {
		this.statusCadastro = statusCadastro;
	}
	public TipoCadastroEnum getTipoCadastro() {
		return tipoCadastro;
	}
	public void setTipoCadastro(TipoCadastroEnum tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public String getNomePai() {
		return nomePai;
	}
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
