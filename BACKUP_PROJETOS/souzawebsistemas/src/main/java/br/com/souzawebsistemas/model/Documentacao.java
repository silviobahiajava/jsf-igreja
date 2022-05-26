package br.com.souzawebsistemas.model;

import javax.persistence.*;
@Entity
public class Documentacao extends GenericDomain {
	private static final long serialVersionUID = 1L;

	private String rg;
	private String cpf;

	@ManyToOne
	//@JoinColumn(name="codigo_membro")
    private Membro membro;
	
	@ManyToOne
	@JoinColumn(name="codigo_cliente")
	private Cliente cliente;

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

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}
	
	
}
