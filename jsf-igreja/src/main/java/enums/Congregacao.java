package enums;

public enum Congregacao {
	SEDE("SEDE"),FRANCISQUINE("FRANCISQUINE"),MONTECARLOS("MONTE CARLOS");
private String descricao;
private Congregacao(String desc){
	this.descricao=desc;
	
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}

}
