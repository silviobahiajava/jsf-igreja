package entidades;

import javax.persistence.*;
@Entity
@Table(name="crianca_matriculada_na_escolinha_ausente")
public class CriancaMatriculadaNaEscolinhaAusente extends GenericDomain{
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private CriancaMatriculadaNaEscolinha matriculado;//produto
	
	@ManyToOne
	private AulaEscolinhaCrianca aula;//venda

	public CriancaMatriculadaNaEscolinha getMatriculado() {
		return matriculado;
	}

	public void setMatriculado(CriancaMatriculadaNaEscolinha matriculado) {
		this.matriculado = matriculado;
	}

	public AulaEscolinhaCrianca getAula() {
		return aula;
	}

	public void setAula(AulaEscolinhaCrianca aula) {
		this.aula = aula;
	}
	
	
}
