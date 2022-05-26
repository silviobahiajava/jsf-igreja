package enums;

public enum TipoCompra {
	AVISTA("A VISTA"),APRAZO("A PRAZO");
	
	private String descricao;
	private TipoCompra(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
