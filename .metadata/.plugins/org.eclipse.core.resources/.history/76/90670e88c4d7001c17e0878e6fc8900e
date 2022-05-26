package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import meucursoJPA.model.GenericDomain;
import meucursoJPA.model.secretaria.Grupo;

import javax.persistence.*;
@Entity
@Table(name="campanha")
public class Campanha extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCampanha;
	@ManyToOne
	private Lancamento lancamento;
	private String nomeCampanha;
	private String objetivoCampanha;
	
	
	@ManyToOne
	private Grupo grupo;
	
	private BigDecimal valorCampanha;

	

	public Date getDataCampanha() {
		return dataCampanha;
	}

	public void setDataCampanha(Date dataCampanha) {
		this.dataCampanha = dataCampanha;
	}

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public String getObjetivoCampanha() {
		return objetivoCampanha;
	}

	public void setObjetivoCampanha(String objetivoCampanha) {
		this.objetivoCampanha = objetivoCampanha;
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

	public BigDecimal getValorCampanha() {
		return valorCampanha;
	}

	public void setValorCampanha(BigDecimal valorCampanha) {
		this.valorCampanha = valorCampanha;
	}
	
	
	
}
