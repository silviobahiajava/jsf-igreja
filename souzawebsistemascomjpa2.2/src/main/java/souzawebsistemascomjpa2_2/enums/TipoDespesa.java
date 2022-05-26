package souzawebsistemascomjpa2_2.enums;

public enum TipoDespesa {
	IMOVEISEEDIFICIOS("IMÓVEIS E EIDFÍCIOS"),
	MOVEISEUTENSILIOS("MÓVEIS E UTENSÍLIOS"),
	ALUGUEL("ALUGUEL"),
	
	OBRAMISSIONARIA("OBRA MISSIONÁRIA"),
	OBRIGACOESSOCIAIS("OBRIGAÇÕES SOCIAIS"),
	PAGAMENTOLUZ("PAGAMENTO DE LUZ"),
	
	PAGAMENTOAGUA("PAGAMENTO DE AGUA"),
	PAGAMENTOGAS("PAGAMENTO DE GAS"),
	PAGAMENTOTELEFONE("PAGAMENTO DE TELEFONE"),
	
	ALIMENTACAO("ALIMENTACAO"),
	EVANGELIZACAO("EVANGELIZACAO"),
	DESPESASDIVERSAS("DESPESAS DIVERSAS");
	
	
	
	private String descricao;
	private TipoDespesa(String descricao){
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
