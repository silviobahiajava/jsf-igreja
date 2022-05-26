package enums;

public enum TipoPagamento {
	DINHEIRO("DINHEIRO"),CHEQUE("CHEQUE"),
	BOLETO("BOLETO"),CARTAOCREDITO("CARTAO DE CREDITO"),
	CARTAODEBITO("CARTAO DE DEBITO"),PAGSEGURO("PAGSEGURO"),
	CARNE("CARNÊ"),DUPLICATA("DUPLICATA"),PROMISSORIA("PROMISSORIA");
	private String descricao;
	private TipoPagamento(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
