package souzawebsistemascomjpa2_2.enums;

public enum TemFilhosEnum {
	SIM("SIM"), 
	NAO("NÃO");
	private String descricao;

	private TemFilhosEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
