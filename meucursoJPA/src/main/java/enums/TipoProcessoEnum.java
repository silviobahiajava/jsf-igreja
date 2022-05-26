package enums;

public enum TipoProcessoEnum {
	  JUDICIAL("JUDICIAL"),EXTRA_JUDICIAL("EXTRA JUDICIAL");
	  private String descricao;
	  private TipoProcessoEnum(String descricao){
		  this.descricao=descricao;
	  }
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	  
}
