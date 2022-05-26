package br.com.souzawebsistemas.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="entrada_produto")
public class EntradaProduto extends GenericDomain{
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Funcionario funcionario;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataEntrada ;
	private String observacao;
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	
}
