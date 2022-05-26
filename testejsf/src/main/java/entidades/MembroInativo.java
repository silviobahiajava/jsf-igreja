package entidades;




import java.util.Date;

import javax.persistence.*;




import enums.Congregacao;
import enums.EstadoCivil;
import enums.EstadosBrasileirosEnum;
import enums.MotivoInatividadeEnum;


@Entity
@Table(name="membroinativo")
public class MembroInativo extends GenericDomain{
	private static final long serialVersionUID = 1L;
	private String nome;
	
	private String nacionalidade;
	private String natualidade;
	@Enumerated(EnumType.STRING)
	private EstadosBrasileirosEnum estadosBrasileirosEnum;
	
	private Long codigoMotivoInatividade;
	
	@Enumerated(EnumType.STRING)
	private MotivoInatividadeEnum motivoSaida;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	private String rg;
	
	@OneToOne
	private Membro membro;
	
	
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
	
	
	
	private String tipoCargo;
	
	private String nomedamae;
	private String nomedopai;
	
	
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private Endereco endereco;
	

	@Temporal(TemporalType.DATE)
	private Date dataBatismo;
	
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private Contato contato;///classe
	
	@OneToOne(cascade=CascadeType.ALL)
	private Filiacao filiacao;
	
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;//enum
	
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
	
	
	
	
	public Endereco getEndereco() {
		return endereco;
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
	public Membro getMembro() {
		return membro;
	}
	public void setMembro(Membro membro) {
		this.membro = membro;
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
	
	
	
	
}
