package br.com.souzawebsistemas.enums;

public enum MotivoExclusaodeMembrosEnum {
	SAUDOSALEMBRANCA("SAUDOSA LEMBRANÇA"), 
	MUDANCADEIGREJA("MUDANÇA DE IGREJA"),
	MUNDANCADECIDADE("MUDANÇA DE CIDADE"), 
	DISCIPLINA("DISCIPLINA"),
	DESVIADO("DESVIADO"),
	OUTROSMOTIVOS("OUTROS MOTIVOS"), 
	;

	private String descricao;

	private MotivoExclusaodeMembrosEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
