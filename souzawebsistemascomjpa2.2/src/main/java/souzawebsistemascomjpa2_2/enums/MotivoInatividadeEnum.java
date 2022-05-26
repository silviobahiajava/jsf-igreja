package souzawebsistemascomjpa2_2.enums;

public enum MotivoInatividadeEnum {
	SAUDADESETERNAS("SAUDADES ETERNAS"),
	MUDOUDEIGREJA("MUDOU DE IGREJA"),
	MUDOUDECIDADE("MUDOU DE CIDADE"),
	DISCIPLINA("DISCIPLINA"),
	DESVIADO("DESVIADO");
	
	private MotivoInatividadeEnum(String descricao){
		this.descricao=descricao;
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
