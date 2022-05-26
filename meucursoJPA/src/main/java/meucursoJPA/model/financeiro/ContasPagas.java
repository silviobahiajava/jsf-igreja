package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import meucursoJPA.model.GenericDomain;
import meucursoJPA.model.secretaria.Grupo;
@Entity
@Table(name="contas_pagas")
public class ContasPagas extends GenericDomain{
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	private BigDecimal valorPagoParcial;
	private boolean temDesconto;
	private BigDecimal valorPagoComDesconto;
	private boolean temJuros;
	private BigDecimal valorPagoComJuro;
	
	/*@ManyToOne
	private Pagamento pagamento;
	
	@ManyToOne
	private ContasParaPagar contasParaPagar;*/
	
	@ManyToOne
	private Grupo grupo;
	
	@ManyToOne
	private Lancamento lancamento;
	
	
	
	
	
	public BigDecimal getValorPagoParcial() {
		return valorPagoParcial;
	}
	public void setValorPagoParcial(BigDecimal valorPagoParcial) {
		this.valorPagoParcial = valorPagoParcial;
	}
	public boolean isTemDesconto() {
		return temDesconto;
	}
	public void setTemDesconto(boolean temDesconto) {
		this.temDesconto = temDesconto;
	}
	public BigDecimal getValorPagoComDesconto() {
		return valorPagoComDesconto;
	}
	public void setValorPagoComDesconto(BigDecimal valorPagoComDesconto) {
		this.valorPagoComDesconto = valorPagoComDesconto;
	}
	public boolean isTemJuros() {
		return temJuros;
	}
	public void setTemJuros(boolean temJuros) {
		this.temJuros = temJuros;
	}
	public BigDecimal getValorPagoComJuro() {
		return valorPagoComJuro;
	}
	public void setValorPagoComJuro(BigDecimal valorPagoComJuro) {
		this.valorPagoComJuro = valorPagoComJuro;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
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
	
	
}
