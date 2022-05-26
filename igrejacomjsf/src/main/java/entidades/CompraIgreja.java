package entidades;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name="compra_igreja")
public class CompraIgreja extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	private Date dataCompraIgreja;
	private boolean compraAvistaIgreja;
	private boolean compraAprazoIgreja;
	private String tipoCompra;
	private BigDecimal valorCompraIgreja;
	private String descricao;
	private String numeroNota;
	   @ManyToOne
		private Grupo grupo;
		
		@ManyToOne
		private Lancamento lancamento;
		
	
	public Date getDataCompraIgreja() {
		return dataCompraIgreja;
	}
	public void setDataCompraIgreja(Date dataCompraIgreja) {
		this.dataCompraIgreja = dataCompraIgreja;
	}
	public boolean isCompraAvistaIgreja() {
		return compraAvistaIgreja;
	}
	public void setCompraAvistaIgreja(boolean compraAvistaIgreja) {
		this.compraAvistaIgreja = compraAvistaIgreja;
	}
	public boolean isCompraAprazoIgreja() {
		return compraAprazoIgreja;
	}
	public void setCompraAprazoIgreja(boolean compraAprazoIgreja) {
		this.compraAprazoIgreja = compraAprazoIgreja;
	}
	public BigDecimal getValorCompraIgreja() {
		return valorCompraIgreja;
	}
	public void setValorCompraIgreja(BigDecimal valorCompraIgreja) {
		this.valorCompraIgreja = valorCompraIgreja;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}
	public String getTipoCompra() {
		return tipoCompra;
	}
	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
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
