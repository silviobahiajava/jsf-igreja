package br.com.jsf_igreja.secretaria.model;

import java.io.Serializable;
import java.util.Objects;

import enums.CargoEnum;
import enums.Congregacao;
import enums.EstadoCivil;
import enums.EstadosBrasileirosEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="membro")
public class Membro implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String nome;
	private String nacionalidade;
	private String natualidade;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_documentacao")
	private Documentacao documentacao;///classe
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_contato")
	private Contato contato;///classe
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_filiacao")
	private Filiacao filiacao;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_endereco")
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivilEnum;//enum
	
	@Enumerated(EnumType.STRING)
	private CargoEnum cargoEnum;//enum
	
	@Enumerated(EnumType.STRING)
	private Congregacao congregacaoEnum;//enum
	
	@Enumerated(EnumType.STRING)
	private EstadosBrasileirosEnum estadosBrasileirosEnum;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Filiacao getFiliacao() {
		return filiacao;
	}

	public void setFiliacao(Filiacao filiacao) {
		this.filiacao = filiacao;
	}

	public EstadoCivil getEstadoCivilEnum() {
		return estadoCivilEnum;
	}

	public void setEstadoCivilEnum(EstadoCivil estadoCivilEnum) {
		this.estadoCivilEnum = estadoCivilEnum;
	}

	public CargoEnum getCargoEnum() {
		return cargoEnum;
	}

	public void setCargoEnum(CargoEnum cargoEnum) {
		this.cargoEnum = cargoEnum;
	}

	public Congregacao getCongregacaoEnum() {
		return congregacaoEnum;
	}

	public void setCongregacaoEnum(Congregacao congregacaoEnum) {
		this.congregacaoEnum = congregacaoEnum;
	}

	public EstadosBrasileirosEnum getEstadosBrasileirosEnum() {
		return estadosBrasileirosEnum;
	}

	public void setEstadosBrasileirosEnum(EstadosBrasileirosEnum estadosBrasileirosEnum) {
		this.estadosBrasileirosEnum = estadosBrasileirosEnum;
	}

	public Documentacao getDocumentacao() {
		return documentacao;
	}

	public void setDocumentacao(Documentacao documentacao) {
		this.documentacao = documentacao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Membro other = (Membro) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
