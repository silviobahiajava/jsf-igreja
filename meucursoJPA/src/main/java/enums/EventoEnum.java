package enums;

public enum EventoEnum {
	CONGRESSODOCIRCULODEORACAO
	("CONGRESSO DO CÍRCULO DE ORAÇÃO"),
	
	 CONGRESSODOGRUPODEVAROES
	 ("CONGRESSO DO GRUPO DE VARÕES"),
	 
	 CONGRESSODAMOCIDADE
	 ("CONGRESSO DA MOCIDADE"),
	 
	 CONGRESSODEADOLESCENTES
	 ("CONGRESSO DE ADOLESCENTES"),
	 
	 CONGRESSODOGRUPODECOREOGRAFIA
	 ("CONGRESSODAMOCIDADE"),
	 
	 ESCOLABIBLICADEFERIAS
	 ("ESCOLA BÍBLICA DE FÉRIAS");
	
	private String descricao;
	private EventoEnum(String descricao){
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	 
	 
	 
	 
}
