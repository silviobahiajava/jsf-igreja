package br.com.jsf_igreja.secretaria.model;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import utils.GenericDomain;
@Entity
@Table(name="filiacao")
public class Filiacao implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nomePai;
	private String nomeMae;
	public String getNomePai() {
		return nomePai;
	}
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
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
		Filiacao other = (Filiacao) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
