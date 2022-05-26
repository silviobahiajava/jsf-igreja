package enums;

public enum TipoUsuario {
	/*TESOUREIROIGREJA,
	TESOUREIROVAROES,
	TESOUREIROSENHORAS,
	TESOUREIROORQUESTRA,
	TESOUREIROMOCIDADE,
	TESOUREIROCRIANCAS,
	TESOUREIROMISSAO,
	SECRETARIOIGREJA,
	SECRETARIOCRIANCAS,
	SECRETARIOESCOLADOMINICAL,
	LIDERVAROES,
	LIDERSENHORAS,
	LIDERMOCIDADE,
	COORDENADORESCOLADOMINICAL;
	public String toString(){
		switch(this){
		case TESOUREIROIGREJA:
				return "Tesoureiro Geral da Igreja";
		
		case  TESOUREIROVAROES:
			return "Tesoureiro do Grupo de Varoes";
			
		case TESOUREIROSENHORAS:
			return "Tesoureiro do Circulo de Oracao";
		case TESOUREIROORQUESTRA:
			return "Tesoureiro da Orquestra";
		case TESOUREIROMOCIDADE:
			return "Tesoureiro da Mocidade";
		case TESOUREIROCRIANCAS:
			return "Tesoureiro do Departamento Infantil";
		case TESOUREIROMISSAO:
			return "Tesoureiro do Departamento de Missao";
			
			
		case SECRETARIOIGREJA:
			return "Secretario Geral da Igreja";
		case SECRETARIOCRIANCAS:
			return "Secretaro do Departamento Infantil";
		case SECRETARIOESCOLADOMINICAL:
			return "Secretario da Escola Dominical";
		case LIDERVAROES:
			return "Lider do Grupo de Varoes";
		case LIDERSENHORAS:
			return "Lider do Circulo de Oracao";
		case LIDERMOCIDADE:
			return "Lider da Mocidade";
			
		case COORDENADORESCOLADOMINICAL:
			return "Coordenador da Escola Dominical";
			
			
		default:
			return null;
		}
	}
	*/
	
	
	VISITANTESITE("VISITANTE DO SITE"),
	MEMBROCOMUM("MEMBRO COMUM DA IGREJA"),
	TESOUREIROGERAL("TESOUREIRO(A) GERAL DA IGREJA"),
	
	TESOUREIROGRUPO("TESOUREIRO(A) DE GRUPO"),
	SECRETARIAGERAL("SECRETARIO(A) GERAL DA IGREJA"),
	SECRETARIOGRUPO("SECRETARIO(A) DE GRUPO OU DEPARTAMENTO"),
	
	LIDERGUPO("LIDER DE GRUPO"),
	VICELIDERGRUPO("VICE LIDER DE GRUPO"),
	PRIMEIROREGENTEGRUPO("PRIMEIRO REGENTE DE GRUPO"),
	SEGUNDOREGENTEGRUPO("SEGUNDO REGENTE DE GRUPO");
	private String descricao;
	private TipoUsuario(String descricao){
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
