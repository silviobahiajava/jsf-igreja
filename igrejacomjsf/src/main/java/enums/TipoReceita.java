package enums;

public enum TipoReceita {
	  DIZIMOS("DIZIMOS"),OFERTAS("OFERTAS"),CAMPANHA("CAMPANHA"),CONTRIBUICAO("CONTRIBUICAO"),VENDAS("VENDAS");
	  private String descricao;
	  private TipoReceita(String descricao){
		  this.descricao=descricao;
	  }
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	  
}
