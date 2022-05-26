package entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="oferta_dos_varoes")

public class OfertaDosVaroes extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date dataOfertaVarao;
	@ManyToOne
	private Lancamento lancamento;
	
	
	
	@ManyToOne
	private Grupo grupo;
	
	
	@Column(precision=6,scale=2)
	private BigDecimal valorOfertaVarao;


	public Date getDataOfertaVarao() {
		return dataOfertaVarao;
	}


	public void setDataOfertaVarao(Date dataOfertaVarao) {
		this.dataOfertaVarao = dataOfertaVarao;
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


	public BigDecimal getValorOfertaVarao() {
		return valorOfertaVarao;
	}


	public void setValorOfertaVarao(BigDecimal valorOfertaVarao) {
		this.valorOfertaVarao = valorOfertaVarao;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
