package enums;

public enum EstadoCivil {
	SOLTEIRO("SOLTEIRO"),CASADO("CASADO"),DIVORCIADO("DIVORCIADO"),VIUVO("VIUVO");
private String descricao;
private EstadoCivil(String desc){
	this.descricao=desc;
	
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}


}
