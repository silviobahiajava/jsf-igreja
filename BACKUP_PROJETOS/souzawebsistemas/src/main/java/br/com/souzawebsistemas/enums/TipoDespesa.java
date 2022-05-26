package br.com.souzawebsistemas.enums;

public enum TipoDespesa {
	IMOVEISEEDIFICIOS("IM�VEIS E EIDF�CIOS"),
	MOVEISEUTENSILIOS("M�VEIS E UTENS�LIOS"),
	ALUGUEL("ALUGUEL"),
	
	OBRAMISSIONARIA("OBRA MISSION�RIA"),
	OBRIGACOESSOCIAIS("OBRIGA��ES SOCIAIS"),
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
