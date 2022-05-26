package br.com.jsf_igreja.secretaria.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import utils.GenericDomain;
@Entity
@Table(name="documentacao")
public class Documentacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String rg;
	private String cpf;
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
		Documentacao other = (Documentacao) obj;
		return Objects.equals(id, other.id);
	}
	

}
