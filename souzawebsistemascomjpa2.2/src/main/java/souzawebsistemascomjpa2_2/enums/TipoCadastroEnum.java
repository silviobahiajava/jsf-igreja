package souzawebsistemascomjpa2_2.enums;

public enum TipoCadastroEnum {

	ATIVO("ATIVO"),
	INATIVO("INATIVO");
	
	private String descricao;
	private TipoCadastroEnum(String descricao){
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	
}
