package souzawebsistemascomjpa2_2.enums;

public enum TipoMovimentacao {
	RECEITA("RECEITA OU ENTRADA"),
	DESPESA("DESPESAS OU SAÍDA");
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
