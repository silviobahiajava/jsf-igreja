package br.com.souzawebsistemas.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="entrada_item")
public class EntradaItem extends GenericDomain{
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private EntradaProduto entrada;
	
	@ManyToOne
	private Produto produto;
	private Integer quantidade;
	private BigDecimal valorProduto;
	public EntradaProduto getEntrada() {
		return entrada;
	}
	public void setEntrada(EntradaProduto entrada) {
		this.entrada = entrada;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
	}
	
	
}
