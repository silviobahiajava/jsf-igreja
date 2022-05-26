package enums;

public enum TipoCompraEBDEnum {
	REVISTA("REVISTAS"),
	LIVROS("LIVROS"),
	MATERIALESCOLAR("MATERIAL ESCOLAR"),
	BRINDES("BRINDES"),
	LEMBRANCAS("LEMBRANÇAS");
	private String descricao;
	private TipoCompraEBDEnum(String descricao){
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
