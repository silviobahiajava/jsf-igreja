package br.com.souzawebsistemas.enums;

public enum EstadosBrasileirosEnum {
	AC("AC"),
	AM("AM"),
	PA("PA"),
	RO("RO"),
	RR("RR"),
	
	AP("AP"),
	BA("BA"),
	RN("RN"),
	RS("RS"),
	PB("PB"),
	
	PI("PI"),
	PR("PR"),
	SC("SC"),
	SP("SP"),
	RJ("RJ"),
	
	MG("MG"),
	ES("ES"),
	AL("AL"),
	PE("PE"),
	CE("CE"),
	
	MT("MT"),
	SE("SE");
	
	private String descricao;
	private EstadosBrasileirosEnum(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
