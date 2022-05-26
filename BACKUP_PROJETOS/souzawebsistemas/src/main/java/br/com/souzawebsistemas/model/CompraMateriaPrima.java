package br.com.souzawebsistemas.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.*;
@Entity
@Table(name="CompraMateriaPrima")
public class CompraMateriaPrima extends GenericDomain{
	private static final long serialVersionUID = 1L;
	private Date dataCompra;
	private String descricao;
	private int quantidadeComprada;
	private BigDecimal valorTotalCompra;
	
	@ManyToOne
	private Grupo grupo;
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidadeComprada() {
		return quantidadeComprada;
	}
	public void setQuantidadeComprada(int quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}
	public BigDecimal getValorTotalCompra() {
		return valorTotalCompra;
	}
	public void setValorTotalCompra(BigDecimal valorTotalCompra) {
		this.valorTotalCompra = valorTotalCompra;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	
	
}
