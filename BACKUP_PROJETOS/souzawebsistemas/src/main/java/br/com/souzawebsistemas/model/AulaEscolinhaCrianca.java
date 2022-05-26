package br.com.souzawebsistemas.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="aula_escolinha_crianca")
public class AulaEscolinhaCrianca extends GenericDomain{
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAula;
	//@ManyToOne
//	private ClasseEscolaDominical classe;
	
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="aula")
//	private List<AlunosPresentesNaEscolaDominical>alunosPresentes;
//	
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="aula")
//	private List<AlunosAusentesDaEscolaDominical>alunosAusentes;
	
	private int totalCriancasPresentes;
	private int totalCriancasAusentes;
	//private int totalBiblias;
	//private int totalRevistas;
	private int totalMatriculado;
	//@Column(name="total_Ofertas",precision=7,scale=2)
	//private BigDecimal totalOfertas;
	public Date getDataAula() {
		return dataAula;
	}
	public void setDataAula(Date dataAula) {
		this.dataAula = dataAula;
	}
	public int getTotalCriancasPresentes() {
		return totalCriancasPresentes;
	}
	public void setTotalCriancasPresentes(int totalCriancasPresentes) {
		this.totalCriancasPresentes = totalCriancasPresentes;
	}
	public int getTotalCriancasAusentes() {
		return totalCriancasAusentes;
	}
	public void setTotalCriancasAusentes(int totalCriancasAusentes) {
		this.totalCriancasAusentes = totalCriancasAusentes;
	}
	public int getTotalMatriculado() {
		return totalMatriculado;
	}
	public void setTotalMatriculado(int totalMatriculado) {
		this.totalMatriculado = totalMatriculado;
	}
	
	
}
