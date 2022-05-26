package br.com.souzawebsistemas.enums;

public enum CaixaEnum {
	ABERTO("ABERTO"),
	ABERTURAINICIAL("ABERTURA INICIAL"),
	FECHADO("FECHADO");
	private String descricao;
	private CaixaEnum(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
