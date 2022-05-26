package br.com.souzawebsistemas.model;

import java.math.BigDecimal;
import java.util.Date;


import javax.persistence.*;

@Entity
@Table(name="dizimo_da_igreja")
public class DizimoDaIgreja extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date dataDizimoIgreja;
	
	private String descricao;
	
	private String tipodizimo;
	
	@OneToOne
	private Membro dizimistaIgreja;
	
	@Column(precision = 6, scale = 2)
	private BigDecimal valorDizimoIgreja;
	
	@ManyToOne
	private Lancamento lancamento;
	
	@ManyToOne
	private Grupo grupo;

	public Date getDataDizimoIgreja() {
		return dataDizimoIgreja;
	}

	public void setDataDizimoIgreja(Date dataDizimoIgreja) {
		this.dataDizimoIgreja = dataDizimoIgreja;
	}

	public Membro getDizimistaIgreja() {
		return dizimistaIgreja;
	}

	public void setDizimistaIgreja(Membro dizimistaIgreja) {
		this.dizimistaIgreja = dizimistaIgreja;
	}

	public BigDecimal getValorDizimoIgreja() {
		return valorDizimoIgreja;
	}

	public void setValorDizimoIgreja(BigDecimal valorDizimoIgreja) {
		this.valorDizimoIgreja = valorDizimoIgreja;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipodizimo() {
		return tipodizimo;
	}

	public void setTipodizimo(String tipodizimo) {
		this.tipodizimo = tipodizimo;
	}
	
	
	
	
}
