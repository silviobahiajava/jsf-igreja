package meucursoJPA.model.escoladominical;



import javax.persistence.*;

import meucursoJPA.model.GenericDomain;
@Entity
@Table(name="alunos_presente_escola_dominical")//item
public class AlunosPresentesNaEscolaDominical  extends GenericDomain{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@ManyToOne
	private AlunosMatriculadosEscolaDominical matriculado;//produto
	
	@ManyToOne
	private AulaEscolaDominical aula;//venda

	public AlunosMatriculadosEscolaDominical getMatriculado() {
		return matriculado;
	}

	public void setMatriculado(AlunosMatriculadosEscolaDominical matriculado) {
		this.matriculado = matriculado;
	}

	public AulaEscolaDominical getAula() {
		return aula;
	}

	public void setAula(AulaEscolaDominical aula) {
		this.aula = aula;
	}
	
	
	
	
	
	
}
