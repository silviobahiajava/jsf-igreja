package meucursoJPA.model.financeiro;




import java.util.Date;

import javax.persistence.*;

//import org.hibernate.validator.constraints.br.CPF;


import enums.Congregacao;
import enums.EstadoCivil;
import enums.EstadosBrasileirosEnum;
import meucursoJPA.model.GenericDomain;


@Entity
@Table(name="cliente")
public class Cliente extends GenericDomain{
	private static final long serialVersionUID = 1L;
	
	
	private String nomeCliente;
	private String cpfCliente;
	private String emailCliente;
	private String logradouroCliente;
	private String numeroLogradouroCliente;
	private String bairroCliente;
	private String cidadeCliente;
	private String cep;
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	
	@Transient
	private String caminho;
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public String getLogradouroCliente() {
		return logradouroCliente;
	}
	public void setLogradouroCliente(String logradouroCliente) {
		this.logradouroCliente = logradouroCliente;
	}
	public String getNumeroLogradouroCliente() {
		return numeroLogradouroCliente;
	}
	public void setNumeroLogradouroCliente(String numeroLogradouroCliente) {
		this.numeroLogradouroCliente = numeroLogradouroCliente;
	}
	public String getBairroCliente() {
		return bairroCliente;
	}
	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}
	public String getCidadeCliente() {
		return cidadeCliente;
	}
	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
	
	
	
}
