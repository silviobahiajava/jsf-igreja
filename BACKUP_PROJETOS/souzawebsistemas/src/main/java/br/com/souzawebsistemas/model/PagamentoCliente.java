package br.com.souzawebsistemas.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.souzawebsistemas.enums.*;

@Entity
public class PagamentoCliente extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	@OneToOne
  private Cliente cliente;
  private double valorTotal;
	@Temporal(TemporalType.DATE)
	private Date dataProcesso;
	@Enumerated(EnumType.STRING)
	private TipoProcessoEnum tipoProcessoEnum;
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getDataProcesso() {
		return dataProcesso;
	}
	public void setDataProcesso(Date dataProcesso) {
		this.dataProcesso = dataProcesso;
	}
	public TipoProcessoEnum getTipoProcessoEnum() {
		return tipoProcessoEnum;
	}
	public void setTipoProcessoEnum(TipoProcessoEnum tipoProcessoEnum) {
		this.tipoProcessoEnum = tipoProcessoEnum;
	}
	
	
	
}
