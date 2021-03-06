package br.com.jsf_igreja.secretaria.model;
import java.io.Serializable;
import java.util.Objects;

import enums.EstadosBrasileirosEnum;
import jakarta.persistence.*;
import utils.GenericDomain;
@Entity
@Table
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String endreco;
	private String numero;
	private String bairro;
	private String cidade;
	
	@Enumerated(EnumType.STRING)
	private EstadosBrasileirosEnum estadoEnum;

	public String getEndreco() {
		return endreco;
	}

	public void setEndreco(String endreco) {
		this.endreco = endreco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public EstadosBrasileirosEnum getEstadoEnum() {
		return estadoEnum;
	}

	public void setEstadoEnum(EstadosBrasileirosEnum estadoEnum) {
		this.estadoEnum = estadoEnum;
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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}
	

}
