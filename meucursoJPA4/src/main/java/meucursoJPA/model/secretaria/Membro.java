package meucursoJPA.model.secretaria;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the membro database table.
 * 
 */
@Entity
@NamedQuery(name="Membro.findAll", query="SELECT m FROM Membro m")
public class Membro implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String cpf;
	private String nomecompleto;
	private String rg;
	private String telefone;

	public Membro() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNomecompleto() {
		return this.nomecompleto;
	}

	public void setNomecompleto(String nomecompleto) {
		this.nomecompleto = nomecompleto;
	}


	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}