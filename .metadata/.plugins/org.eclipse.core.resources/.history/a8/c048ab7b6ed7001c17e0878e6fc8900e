package entidades;

import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="matriculados")//produto
public class AlunosMatriculadosEscolaDominical extends GenericDomain{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private Membro membro;
	@Temporal(TemporalType.DATE)
	private Date dataMatricula;
	@ManyToOne
	private ClasseEscolaDominical classe;
	
	@ManyToOne
	private AulaEscolaDominical aula;
	
	public Membro getMembro() {
		return membro;
	}
	public void setMembro(Membro membro) {
		this.membro = membro;
	}
	public Date getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	public ClasseEscolaDominical getClasse() {
		return classe;
	}
	public void setClasse(ClasseEscolaDominical classe) {
		this.classe = classe;
	}
	
	public String toString(){
		return membro.getNome();
	}
	
}
