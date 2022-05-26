package enums;

public enum CargoEnum {

	MEMBRO("MEMBRO"),
	COOPERADOR("COOPERADOR"),
	DIACONO("DIACONO"),
	PRESBITERO("PRESBITERO"),
	EVANGELISTA("EVANGELISTA"),
	PASTOR("PASTOR"),
	BISPO("BISPO"),
	DIACONIZA("DIACONIZA");
	private String descricao;
	private CargoEnum(String descricao){
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
