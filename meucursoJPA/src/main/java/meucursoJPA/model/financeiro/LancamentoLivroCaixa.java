package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enums.TipoDespesa;
import enums.TipoMovimentacao;
import enums.TipoReceita;
import meucursoJPA.model.GenericDomain;
import meucursoJPA.model.secretaria.Grupo;

@Entity
@Table(name="lancamento_livrocaixa")
public class LancamentoLivroCaixa extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	
	@Temporal(TemporalType.DATE)
	private Date dataDoLancamento;
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	
	@Enumerated(EnumType.STRING)
	private TipoDespesa tipoDespesa;
	
	private ContaAPagar contaPraPagar;
	
	@Enumerated(EnumType.STRING)
	private TipoReceita tipoReceita;
	
	private ContasPagas contasPagas;
	
	@ManyToOne
	private Grupo grupo;
	
	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal saldoAnterior;
	
	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal receita;
	
	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal despesa;
	
	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal saldo;
	
	private String historico;

	public Date getDataDoLancamento() {
		return dataDoLancamento;
	}

	public void setDataDoLancamento(Date dataDoLancamento) {
		this.dataDoLancamento = dataDoLancamento;
	}

	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public BigDecimal getReceita() {
		return receita;
	}

	public void setReceita(BigDecimal receita) {
		this.receita = receita;
	}

	public BigDecimal getDespesa() {
		return despesa;
	}

	public void setDespesa(BigDecimal despesa) {
		this.despesa = despesa;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public ContaAPagar getContaPraPagar() {
		return contaPraPagar;
	}

	public void setContaPraPagar(ContaAPagar contaPraPagar) {
		this.contaPraPagar = contaPraPagar;
	}

	public ContasPagas getContasPagas() {
		return contasPagas;
	}

	public void setContasPagas(ContasPagas contasPagas) {
		this.contasPagas = contasPagas;
	}
	
	
	

}
