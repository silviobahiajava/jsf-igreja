package br.com.souzawebsistemas.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="funcionario")
public class Funcionario extends GenericDomain{
	private static final long serialVersionUID = 1L;
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
