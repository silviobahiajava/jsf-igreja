package souzawebsistemascomjpa2_2.enums;

public enum EventoEnum {
	CONGRESSODOCIRCULODEORACAO
	("CONGRESSO DO C�?RCULO DE ORAÇÃO"),
	
	 CONGRESSODOGRUPODEVAROES
	 ("CONGRESSO DO GRUPO DE VARÕES"),
	 
	 CONGRESSODAMOCIDADE
	 ("CONGRESSO DA MOCIDADE"),
	 
	 CONGRESSODEADOLESCENTES
	 ("CONGRESSO DE ADOLESCENTES"),
	 
	 CONGRESSODOGRUPODECOREOGRAFIA
	 ("CONGRESSODAMOCIDADE"),
	 
	 ESCOLABIBLICADEFERIAS
	 ("ESCOLA B�?BLICA DE FÉRIAS");
	
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
