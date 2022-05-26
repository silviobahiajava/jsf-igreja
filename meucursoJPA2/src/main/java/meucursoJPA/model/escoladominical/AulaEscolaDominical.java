package meucursoJPA.model.escoladominical;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import meucursoJPA.model.GenericDomain;
import meucursoJPA.model.financeiro.Lancamento;
import meucursoJPA.model.secretaria.Grupo;
@Entity
@Table(name="aula_escola_dominical")
public class AulaEscolaDominical extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAula;
	@ManyToOne
	private ClasseEscolaDominical classe;
	
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="aula")
//	private List<AlunosPresentesNaEscolaDominical>alunosPresentes;
//	
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="aula")
//	private List<AlunosAusentesDaEscolaDominical>alunosAusentes;
	
	private int totalAlunosPresentes;
	private int totalAlunosAusentes;
	private int totalBiblias;
	private int totalRevistas;
	private int totalMatriculado;
	@Column(name="total_Ofertas",precision=7,scale=2)
	private BigDecimal totalOfertas;
	
	@ManyToOne
	private Lancamento lancamento;
	
	@ManyToOne
	private Grupo grupo;
	
	/*@Column(name="total_Ofertas",precision=7,scale=2)
	@OneToOne(cascade=CascadeType.ALL)
	private OfertaDaEscolaDominical ofertaDaEscolaDominical;
	*/
	public Date getDataAula() {
		return dataAula;
	}
	public void setDataAula(Date dataAula) {
		this.dataAula = dataAula;
	}
	
	
	
//	public List<AlunosPresentesNaEscolaDominical> getAlunosPresentes() {
//		return alunosPresentes;
//	}
//	public void setAlunosPresentes(
//			List<AlunosPresentesNaEscolaDominical> alunosPresentes) {
//		this.alunosPresentes = alunosPresentes;
//	}
//	public List<AlunosAusentesDaEscolaDominical> getAlunosAusentes() {
//		return alunosAusentes;
//	}
//	public void setAlunosAusentes(
//			List<AlunosAusentesDaEscolaDominical> alunosAusentes) {
//		this.alunosAusentes = alunosAusentes;
//	}
	public int getTotalAlunosPresentes() {
		return totalAlunosPresentes;
	}
	public void setTotalAlunosPresentes(int totalAlunosPresentes) {
		this.totalAlunosPresentes = totalAlunosPresentes;
	}
	public int getTotalAlunosAusentes() {
		return totalAlunosAusentes;
	}
	public void setTotalAlunosAusentes(int totalAlunosAusentes) {
		this.totalAlunosAusentes = totalAlunosAusentes;
	}
	public int getTotalBiblias() {
		return totalBiblias;
	}
	public void setTotalBiblias(int totalBiblias) {
		this.totalBiblias = totalBiblias;
	}
	public int getTotalRevistas() {
		return totalRevistas;
	}
	public void setTotalRevistas(int totalRevistas) {
		this.totalRevistas = totalRevistas;
	}
	public BigDecimal getTotalOfertas() {
		return totalOfertas;
	}
	public void setTotalOfertas(BigDecimal totalOfertas) {
		this.totalOfertas = totalOfertas;
	}
	public ClasseEscolaDominical getClasse() {
		return classe;
	}
	public void setClasse(ClasseEscolaDominical classe) {
		this.classe = classe;
	}
	public int getTotalMatriculado() {
		return totalMatriculado;
	}
	public void setTotalMatriculado(int totalMatriculado) {
		this.totalMatriculado = totalMatriculado;
	}
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	
	
	
	
	
}
