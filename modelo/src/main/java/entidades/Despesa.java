package entidades;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enums.TipoDespesa;
import enums.TipoReceita;
@Entity
@Table(name="despesa")
public class Despesa extends GenericDomain{
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date dataLancamento;
	
	private BigDecimal valorDaDespesa;
	private String descricaoDespesa;
	@Enumerated(EnumType.STRING)
	private TipoDespesa tipoDeDespesa;
	
	@ManyToOne
	private Grupo grupo;
	
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public BigDecimal getValorDaDespesa() {
		return valorDaDespesa;
	}
	public void setValorDaDespesa(BigDecimal valorDaDespesa) {
		this.valorDaDespesa = valorDaDespesa;
	}
	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}
	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}
	public TipoDespesa getTipoDeDespesa() {
		return tipoDeDespesa;
	}
	public void setTipoDeDespesa(TipoDespesa tipoDeDespesa) {
		this.tipoDeDespesa = tipoDeDespesa;
	}
	
	

}
