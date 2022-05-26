package souzawebsistemascomjpa2_2.enums;

public enum MesEnum {
	JANEIRO("JANEIRO"),
	FEVEREIRO("FEVEREIRO"),
	MARCO("MAIO"),
	ABRIL("ABRIL"),
	MAIO("MAIO"),
	JUNHO("JUNHO"),
	JULHO("JULHO"),
	AGOSTO("AGOSTO"),
	SETEMBRO("SETEMBRO"),
	OUTUBRO("OUTUBRO"),
	NOVEMBRO("NOVEMBRO"),
	DEZEMBRO("DEZEMBRO");
	

	private String descricao;
	private MesEnum(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
