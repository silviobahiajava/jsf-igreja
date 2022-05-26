package souzawebsistemascomjpa2_2.enums;

public enum EstadoCivilEnum {
	
	SIM("SIM"), 
	NAO("NÃO");

	private String descricao;

	private EstadoCivilEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
