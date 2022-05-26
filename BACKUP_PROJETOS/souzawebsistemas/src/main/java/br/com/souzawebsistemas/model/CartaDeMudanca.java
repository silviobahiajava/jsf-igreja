
package br.com.souzawebsistemas.model;

import java.util.Date;

import br.com.souzawebsistemas.enums.*;

public class CartaDeMudanca {
	private String nome;
	private String cargo;
	private String anoAdmissao;
	private String estadoCivil;
	private String esposa;
	private String filhos;
	private EstadoCivilEnum estadoCivilEnum;
	private TemFilhosEnum temFilhosEnum;
	//private boolean casado;
	private String dataCarta;
	
//	private String diaDeApresentacao;
//	private String mesDeApresentacao;
	private String anoDeApresentacao;
	
	
	public String getAnoAdmissao() {
		return anoAdmissao;
	}
	public void setAnoAdmissao(String anoAdmissao) {
		this.anoAdmissao = anoAdmissao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getDataCarta() {
		return dataCarta;
	}
	public void setDataCarta(String dataCarta) {
		this.dataCarta = dataCarta;
	}
	public String getEsposa() {
		return esposa;
	}
	public void setEsposa(String esposa) {
		this.esposa = esposa;
	}
	public String getFilhos() {
		return filhos;
	}
	public void setFilhos(String filhos) {
		this.filhos = filhos;
	}
	public EstadoCivilEnum getEstadoCivilEnum() {
		return estadoCivilEnum;
	}
	public void setEstadoCivilEnum(EstadoCivilEnum estadoCivilEnum) {
		this.estadoCivilEnum = estadoCivilEnum;
	}
	public TemFilhosEnum getTemFilhosEnum() {
		return temFilhosEnum;
	}
	public void setTemFilhosEnum(TemFilhosEnum temFilhosEnum) {
		this.temFilhosEnum = temFilhosEnum;
	}
	public String getAnoDeApresentacao() {
		return anoDeApresentacao;
	}
	public void setAnoDeApresentacao(String anoDeApresentacao) {
		this.anoDeApresentacao = anoDeApresentacao;
	}
	
	
}
