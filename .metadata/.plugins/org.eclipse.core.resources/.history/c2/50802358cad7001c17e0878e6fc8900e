package meucursoJPA.model.financeiro;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import meucursoJPA.model.GenericDomain;

import javax.persistence.*;
@Entity
public class ParcelaCliente extends GenericDomain{
	
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date dataVencimentoDaParcela;
	
	private int numerodaparcela;
	
	@Temporal(TemporalType.DATE)
	private Date dataPagamentoDaParcela;
	
	private double valorDaParcela;
	
	@ManyToOne
	private PagamentoCliente pagamento;

	public Date getDataVencimentoDaParcela() {
		return dataVencimentoDaParcela;
	}

	public void setDataVencimentoDaParcela(Date dataVencimentoDaParcela) {
		this.dataVencimentoDaParcela = dataVencimentoDaParcela;
	}

	public int getNumerodaparcela() {
		return numerodaparcela;
	}

	public void setNumerodaparcela(int numerodaparcela) {
		this.numerodaparcela = numerodaparcela;
	}

	public Date getDataPagamentoDaParcela() {
		return dataPagamentoDaParcela;
	}

	public void setDataPagamentoDaParcela(Date dataPagamentoDaParcela) {
		this.dataPagamentoDaParcela = dataPagamentoDaParcela;
	}

	public double getValorDaParcela() {
		return valorDaParcela;
	}

	public void setValorDaParcela(double valorDaParcela) {
		this.valorDaParcela = valorDaParcela;
	}

	public PagamentoCliente getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoCliente pagamento) {
		this.pagamento = pagamento;
	}
	
	
}
