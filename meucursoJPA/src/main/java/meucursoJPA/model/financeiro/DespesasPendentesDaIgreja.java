package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enums.SitucaoDaConta;
import enums.TipoDespesa;
import meucursoJPA.model.GenericDomain;
import meucursoJPA.model.secretaria.Grupo;

import javax.persistence.*;

@Entity
public class DespesasPendentesDaIgreja extends GenericDomain{
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Grupo grupo;
	@ManyToOne
	private Lancamento lancamento;
	
	@OneToOne
	private DespesaDaIgreja despesaDaIgreja;
	
	private String descricaoDespesaPendenteDaIgreja;
	@Column(precision=10,scale=2)
	private BigDecimal valorDespesaPendenteDaIgeja;
	
	@Temporal(TemporalType.DATE)
	private Date dataPagamentoDespesaPendenteDaIgreja;
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimentoDespesaPendenteDaIgreja;
	
	
	@Enumerated(EnumType.STRING)
	private SitucaoDaConta situacaoDaDespesaPendenteEnum;
	
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

	public TipoDespesa getTipoDespesaEnum() {
		return tipoDespesaEnum;
	}

	public void setTipoDespesaEnum(TipoDespesa tipoDespesaEnum) {
		this.tipoDespesaEnum = tipoDespesaEnum;
	}

	public String getDescricaoDespesaPendenteDaIgreja() {
		return descricaoDespesaPendenteDaIgreja;
	}

	public void setDescricaoDespesaPendenteDaIgreja(String descricaoDespesaPendenteDaIgreja) {
		this.descricaoDespesaPendenteDaIgreja = descricaoDespesaPendenteDaIgreja;
	}

	public BigDecimal getValorDespesaPendenteDaIgeja() {
		return valorDespesaPendenteDaIgeja;
	}

	public void setValorDespesaPendenteDaIgeja(BigDecimal valorDespesaPendenteDaIgeja) {
		this.valorDespesaPendenteDaIgeja = valorDespesaPendenteDaIgeja;
	}

	public Date getDataPagamentoDespesaPendenteDaIgreja() {
		return dataPagamentoDespesaPendenteDaIgreja;
	}

	public void setDataPagamentoDespesaPendenteDaIgreja(Date dataPagamentoDespesaPendenteDaIgreja) {
		this.dataPagamentoDespesaPendenteDaIgreja = dataPagamentoDespesaPendenteDaIgreja;
	}

	public Date getDataVencimentoDespesaPendenteDaIgreja() {
		return dataVencimentoDespesaPendenteDaIgreja;
	}

	public void setDataVencimentoDespesaPendenteDaIgreja(Date dataVencimentoDespesaPendenteDaIgreja) {
		this.dataVencimentoDespesaPendenteDaIgreja = dataVencimentoDespesaPendenteDaIgreja;
	}

	public SitucaoDaConta getSituacaoDaDespesaPendenteEnum() {
		return situacaoDaDespesaPendenteEnum;
	}

	public void setSituacaoDaDespesaPendenteEnum(SitucaoDaConta situacaoDaDespesaPendenteEnum) {
		this.situacaoDaDespesaPendenteEnum = situacaoDaDespesaPendenteEnum;
	}

	public DespesaDaIgreja getDespesaDaIgreja() {
		return despesaDaIgreja;
	}

	public void setDespesaDaIgreja(DespesaDaIgreja despesaDaIgreja) {
		this.despesaDaIgreja = despesaDaIgreja;
	}
	
	
	
}
