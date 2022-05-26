package entidades;

import java.util.Date;
import javax.persistence.*;
@Entity

public class ExperienciaProfissional extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	private String nomeDaEmpresa;
	private String cargo;
	private Date dataDeAdmmissao;
	private Date dataDeDemissao;
	private String funcoesRealizadas;
	@ManyToOne(cascade=CascadeType.ALL)
	private CurriculoProfissional curriculo;
	public String getNomeDaEmpresa() {
		return nomeDaEmpresa;
	}
	public void setNomeDaEmpresa(String nomeDaEmpresa) {
		this.nomeDaEmpresa = nomeDaEmpresa;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Date getDataDeAdmmissao() {
		return dataDeAdmmissao;
	}
	public void setDataDeAdmmissao(Date dataDeAdmmissao) {
		this.dataDeAdmmissao = dataDeAdmmissao;
	}
	public Date getDataDeDemissao() {
		return dataDeDemissao;
	}
	public void setDataDeDemissao(Date dataDeDemissao) {
		this.dataDeDemissao = dataDeDemissao;
	}
	public String getFuncoesRealizadas() {
		return funcoesRealizadas;
	}
	public void setFuncoesRealizadas(String funcoesRealizadas) {
		this.funcoesRealizadas = funcoesRealizadas;
	}
	public CurriculoProfissional getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(CurriculoProfissional curriculo) {
		this.curriculo = curriculo;
	}
	
	
}
