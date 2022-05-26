package entidades;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enums.SitucaoDaConta;
import enums.TipoDespesa;
import javax.persistence.*;
@Entity
public class DespesaDaIgreja extends GenericDomain{
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Grupo grupo;
	@ManyToOne
	private Lancamento lancamento;
	
	
	
	private String descricaoDespesaDaIgreja;
	@Column(precision=10,scale=2)
	private BigDecimal valorDespescaDaIgreja;
	
	@Temporal(TemporalType.DATE)
	private Date dataPagamentoDespesaDaIgreja;
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimentoDespesaDaIgreja;
	
	
	@Enumerated(EnumType.STRING)
	private SitucaoDaConta situacaoDaDespesaEnum;
	
	@Enumerated(EnumType.STRING)
	private TipoDespesa tipoDespesaEnum;

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public String getDescricaoDespesaDaIgreja() {
		return descricaoDespesaDaIgreja;
	}

	public void setDescricaoDespesaDaIgreja(String descricaoDespesaDaIgreja) {
		this.descricaoDespesaDaIgreja = descricaoDespesaDaIgreja;
	}

	public BigDecimal getValorDespescaDaIgreja() {
		return valorDespescaDaIgreja;
	}

	public void setValorDespescaDaIgreja(BigDecimal valorDespescaDaIgreja) {
		this.valorDespescaDaIgreja = valorDespescaDaIgreja;
	}

	public Date getDataPagamentoDespesaDaIgreja() {
		return dataPagamentoDespesaDaIgreja;
	}

	public void setDataPagamentoDespesaDaIgreja(Date dataPagamentoDespesaDaIgreja) {
		this.dataPagamentoDespesaDaIgreja = dataPagamentoDespesaDaIgreja;
	}

	public TipoDespesa getTipoDespesaEnum() {
		return tipoDespesaEnum;
	}

	public void setTipoDespesaEnum(TipoDespesa tipoDespesaEnum) {
		this.tipoDespesaEnum = tipoDespesaEnum;
	}

	public Date getDataVencimentoDespesaDaIgreja() {
		return dataVencimentoDespesaDaIgreja;
	}

	public void setDataVencimentoDespesaDaIgreja(Date dataVencimentoDespesaDaIgreja) {
		this.dataVencimentoDespesaDaIgreja = dataVencimentoDespesaDaIgreja;
	}

	public SitucaoDaConta getSituacaoDaDespesaEnum() {
		return situacaoDaDespesaEnum;
	}

	public void setSituacaoDaDespesaEnum(SitucaoDaConta situacaoDaDespesaEnum) {
		this.situacaoDaDespesaEnum = situacaoDaDespesaEnum;
	}
	
	
}
