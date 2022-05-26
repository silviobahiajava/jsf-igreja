package br.com.souzawebsistemas.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="dizimo")
public class Dizimo extends GenericDomain{
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date dataDizimo;
	@OneToOne
	private Membro dizimista;
	
	@Column(precision = 6, scale = 2)
	private BigDecimal valorDizimo;
	
	@ManyToOne
	private Lancamento lancamento;
	
	public Date getDataDizimo() {
		return dataDizimo;
	}
	public void setDataDizimo(Date dataDizimo) {
		this.dataDizimo = dataDizimo;
	}
	public Membro getDizimista() {
		return dizimista;
	}
	public void setDizimista(Membro dizimista) {
		this.dizimista = dizimista;
	}
	public BigDecimal getValorDizimo() {
		return valorDizimo;
	}
	public void setValorDizimo(BigDecimal valorDizimo) {
		this.valorDizimo = valorDizimo;
	}
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	
	
	
}
