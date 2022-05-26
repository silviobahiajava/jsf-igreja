package souzawebsistemascomjpa2_2.enums;

public enum DiaEnum {
	UM("01"),
	DOIS("02"),
	TRES("03"),
	QUATRO("04"),
	CINCO("05"),
	SEIS("06"),
	SETE("07"),
	OITO("08"),
	NOVE("09"),
	DEZ("10"),
	ONZE("11"),
	DOZE("12"),
	TRESE("13"),
	QUATORZE("14"),
	QUNZE("15"),
	DEZESSEIS("16"),
	DEZESSETE("17"),
	DEZOITO("18"),
	DEZENOVE("19"),
	VINTE("20"),
	VINTEUM("21"),
	VINTEDOIS("22"),
	VINTETRES("23"),
	VINTEQUATRO("24"),
	VINTECINCO("25"),
	VINTESEIS("26"),
	VINTESETE("27"),
	VINTEOITO("28"),
	VINTENOVE("29"),
	TRINTA("30"),
	TRINTAUM("31");
	
	private String descricao;
	private DiaEnum(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
