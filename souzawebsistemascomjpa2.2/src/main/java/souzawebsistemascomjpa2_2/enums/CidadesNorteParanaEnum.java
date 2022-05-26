package souzawebsistemascomjpa2_2.enums;

public enum CidadesNorteParanaEnum {
	
	
	APUCARANA("APUCARNA"),
	ARAPONGAS("ARAPONGAS"),
	CAMBE("CAMBÉ"),
	LONDRINA("LONDRINA"),
	ROLANDIA("ROLÂNDIA");
	
	
	
	
	
	
	
	private String descricao;
	
	private CidadesNorteParanaEnum( String descricao){
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	


	
	 
}






