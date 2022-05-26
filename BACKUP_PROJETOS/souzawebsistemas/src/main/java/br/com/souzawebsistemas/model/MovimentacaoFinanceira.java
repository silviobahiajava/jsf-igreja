package br.com.souzawebsistemas.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.souzawebsistemas.enums.*;

@Entity
@Table(name="movimentacao_financeira")
public class MovimentacaoFinanceira extends GenericDomain{
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date dataMovimentacao;
	
	@ManyToOne
	private CategoriaDeMovimentacao categoriaMovimentacao;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private BalancoFinanceiro balanco;
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	
	private String descricao;
	private BigDecimal valor;
	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}
	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
	public CategoriaDeMovimentacao getCategoriaMovimentacao() {
		return categoriaMovimentacao;
	}
	public void setCategoriaMovimentacao(
			CategoriaDeMovimentacao categoriaMovimentacao) {
		this.categoriaMovimentacao = categoriaMovimentacao;
	}
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	

}
