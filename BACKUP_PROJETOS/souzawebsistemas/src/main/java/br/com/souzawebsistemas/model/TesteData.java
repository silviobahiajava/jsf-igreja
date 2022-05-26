package br.com.souzawebsistemas.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class TesteData extends GenericDomain{

	private static final long serialVersionUID = 1L;
private String nome;
@DateTimeFormat(pattern = "dd/MM/yyyy")
private Date dataTeste;

private java.sql.Date dataSql;

private LocalDate dataLocal;
private String dataStr;

@DateTimeFormat(pattern="yyyy-MM-dd")
private LocalDate data;

@DateTimeFormat(pattern = "yyyy-MM-dd")
@Temporal(TemporalType.DATE)
private Date dataCadastro;
@Lob
private byte[]curriculo;

private String nomeImagem;


public String getNomeImagem() {
	return nomeImagem;
}
public void setNomeImagem(String nomeImagem) {
	this.nomeImagem = nomeImagem;
}
public byte[] getCurriculo() {
	return curriculo;
}
public void setCurriculo(byte[] curriculo) {
	this.curriculo = curriculo;
}
public Date getDataCadastro() {
	return dataCadastro;
}
public void setDataCadastro(Date dataCadastro) {
	this.dataCadastro = dataCadastro;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}


public Date getDataTeste() {
	return dataTeste;
}
public void setDataTeste(Date dataTeste) {
	this.dataTeste = dataTeste;
}
public LocalDate getDataLocal() {
	return dataLocal;
}
public void setDataLocal(LocalDate dataLocal) {
	this.dataLocal = dataLocal;
}



public String getDataStr() {
	return dataStr;
}
public void setDataStr(String dataStr) {
	this.dataStr = dataStr;
}
public java.sql.Date getDataSql() {
	return dataSql;
}
public void setDataSql(java.sql.Date dataSql) {
	this.dataSql = dataSql;
}
public LocalDate getData() {
	return data;
}
public void setData(LocalDate data) {
	this.data = data;
}


}
