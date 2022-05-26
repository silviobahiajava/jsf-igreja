package enums;

public enum SitucaoDaConta {
	PAGO("PAGO"), 
	APAGAR("A PAGAR"), 
	PAGOCOMATRASO("PAGO COM ATRASO"),
	RECEBIDO("RECEBIDO"), 
	ARECEBER("A RECEBER"), 
	RECEBIDOCOMATRASO("RECEBIDO COM ATRASO");

	private String descricao;

	private SitucaoDaConta(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
