package meucursoJPA.model.ministerioinfantil;

import java.util.Date;

import javax.persistence.*;

import meucursoJPA.model.GenericDomain;
import meucursoJPA.model.escoladominical.AulaEscolaDominical;
@Entity
@Table(name="crianca_matriculada_na_escolinha")
public class CriancaMatriculadaNaEscolinha extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	@OneToOne
	private Crianca crianca;
	@Temporal(TemporalType.DATE)
	private Date dataMatricula;
	//@ManyToOne
	//private ClasseEscolaDominical classe;
	
	@ManyToOne
	private AulaEscolaDominical aula;

	public Crianca getCrianca() {
		return crianca;
	}

	public void setCrianca(Crianca crianca) {
		this.crianca = crianca;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public AulaEscolaDominical getAula() {
		return aula;
	}

	public void setAula(AulaEscolaDominical aula) {
		this.aula = aula;
	}
	
	
}
