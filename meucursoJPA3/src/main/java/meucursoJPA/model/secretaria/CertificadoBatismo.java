package meucursoJPA.model.secretaria;

import java.io.Serializable;
import java.util.Date;

public class CertificadoBatismo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nomeBatizando;
	private String nomeBatizador;
	private Date dataBatismo;
	private String localBatismo;
	private String cidadeBatismo;
	private String estadoBatismo;
	
	private String diaDeApresentacao;
	private String mesDeApresentacao;
	private String anoDeApresentacao;
	public String getNomeBatizando() {
		return nomeBatizando;
	}
	public void setNomeBatizando(String nomeBatizando) {
		this.nomeBatizando = nomeBatizando;
	}
	public String getNomeBatizador() {
		return nomeBatizador;
	}
	public void setNomeBatizador(String nomeBatizador) {
		this.nomeBatizador = nomeBatizador;
	}
	public Date getDataBatismo() {
		return dataBatismo;
	}
	public void setDataBatismo(Date dataBatismo) {
		this.dataBatismo = dataBatismo;
	}
	public String getLocalBatismo() {
		return localBatismo;
	}
	public void setLocalBatismo(String localBatismo) {
		this.localBatismo = localBatismo;
	}
	public String getCidadeBatismo() {
		return cidadeBatismo;
	}
	public void setCidadeBatismo(String cidadeBatismo) {
		this.cidadeBatismo = cidadeBatismo;
	}
	public String getEstadoBatismo() {
		return estadoBatismo;
	}
	public void setEstadoBatismo(String estadoBatismo) {
		this.estadoBatismo = estadoBatismo;
	}
	public String getDiaDeApresentacao() {
		return diaDeApresentacao;
	}
	public void setDiaDeApresentacao(String diaDeApresentacao) {
		this.diaDeApresentacao = diaDeApresentacao;
	}
	public String getMesDeApresentacao() {
		return mesDeApresentacao;
	}
	public void setMesDeApresentacao(String mesDeApresentacao) {
		this.mesDeApresentacao = mesDeApresentacao;
	}
	public String getAnoDeApresentacao() {
		return anoDeApresentacao;
	}
	public void setAnoDeApresentacao(String anoDeApresentacao) {
		this.anoDeApresentacao = anoDeApresentacao;
	}
	
	
	
	
	
}
