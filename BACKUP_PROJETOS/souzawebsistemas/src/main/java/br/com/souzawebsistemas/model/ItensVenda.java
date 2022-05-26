package br.com.souzawebsistemas.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name="itens_venda")
public class ItensVenda extends GenericDomain{
	private static final long serialVersionUID = 1L;
	private BigDecimal precoParcial;
	@ManyToOne
	private Produto produto;
	@ManyToOne
	private Vendas vendas;
	
	private int quantidade;
	
	public BigDecimal getPrecoParcial() {
		return precoParcial;
	}
	public void setPrecoParcial(BigDecimal precoParcial) {
		this.precoParcial = precoParcial;
	}
	
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Vendas getVendas() {
		return vendas;
	}
	public void setVendas(Vendas vendas) {
		this.vendas = vendas;
	}
	
	
}
