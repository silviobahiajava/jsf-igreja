package br.com.souzawebsistemas.enums;

public enum TipoMovimentacao {
	RECEITA("RECEITA OU ENTRADA"),
	DESPESA("DESPESAS OU SA�DA");
	private String descricao;
	private TipoMovimentacao(String descricao){
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
