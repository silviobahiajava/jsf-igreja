package entidades;

import java.util.Date;

import enums.EventoEnum;

public class CartaConvite {
	private String convidado;
	
	private Date dataDoEvento;
	private String telefoneConfirmacao;
	private String emailConfirmacao;
	
	private String diaDeApresentacao;
	private String mesDeApresentacao;
	private String anoDeApresentacao;
	
	private String nomeDoEvento;
	
	
	
	public String getConvidado() {
		return convidado;
	}
	public void setConvidado(String convidado) {
		this.convidado = convidado;
	}

	public Date getDataDoEvento() {
		return dataDoEvento;
	}
	public void setDataDoEvento(Date dataDoEvento) {
		this.dataDoEvento = dataDoEvento;
	}
	public String getTelefoneConfirmacao() {
		return telefoneConfirmacao;
	}
	public void setTelefoneConfirmacao(String telefoneConfirmacao) {
		this.telefoneConfirmacao = telefoneConfirmacao;
	}
	public String getEmailConfirmacao() {
		return emailConfirmacao;
	}
	public void setEmailConfirmacao(String emailConfirmacao) {
		this.emailConfirmacao = emailConfirmacao;
	}
	
	
	public String getDiaDeApresentacao() {
		return diaDeApresentacao;
	}
	public void setDiaDeApresentacao(String diaDeApresentacao) {
		this.diaDeApresentacao = diaDeApresentacao;
	}
	public String getMesDeApresentacao() {
		return mesDeApresentacao;
	}
	public void setMesDeApresentacao(String mesDeApresentacao) {
		this.mesDeApresentacao = mesDeApresentacao;
	}
	public String getAnoDeApresentacao() {
		return anoDeApresentacao;
	}
	public void setAnoDeApresentacao(String anoDeApresentacao) {
		this.anoDeApresentacao = anoDeApresentacao;
	}
	public String getNomeDoEvento() {
		return nomeDoEvento;
	}
	public void setNomeDoEvento(String nomeDoEvento) {
		this.nomeDoEvento = nomeDoEvento;
	}
	
	
	
	
	
	
	
}
