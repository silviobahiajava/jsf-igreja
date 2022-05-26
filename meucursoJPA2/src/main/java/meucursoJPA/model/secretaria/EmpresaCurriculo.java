package meucursoJPA.model.secretaria;

import java.util.Date;

import enums.Profissao;
import meucursoJPA.model.GenericDomain;

import javax.persistence.*;
@Entity
@Table(name="empresa_curriculo")
public class EmpresaCurriculo extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	private String antipenultimaEmpresa;
	private Date dataAdmissaoAntipenultimaEmpresa;
	private Date dataDemissaoAntipenultimaEmpresa;
	private Profissao profissaoAntipenultimaEmpresa;
	private String funcoesUltimaEmpresa;
	
	private String penultimaEmpresa;
	private Date dataAdmissaoPenultimaEmpresa;
	private Date dataDemissaoPenultimaEmpresa;
	private Profissao profissaoPenultimaEmpresa;
	private String funcoesPenultimaEmpresa;
	
	private String ultimaEmpresa;
	private Date dataAdmissaoultimaEmpresa;
	private Date dataDemissaoultimaEmpresa;
	private Profissao profissaoUltimaEmpresa;
	private String funcoesAntiPenultimaEmpresa;
	public String getAntipenultimaEmpresa() {
		return antipenultimaEmpresa;
	}
	public void setAntipenultimaEmpresa(String antipenultimaEmpresa) {
		this.antipenultimaEmpresa = antipenultimaEmpresa;
	}
	public Date getDataAdmissaoAntipenultimaEmpresa() {
		return dataAdmissaoAntipenultimaEmpresa;
	}
	public void setDataAdmissaoAntipenultimaEmpresa(Date dataAdmissaoAntipenultimaEmpresa) {
		this.dataAdmissaoAntipenultimaEmpresa = dataAdmissaoAntipenultimaEmpresa;
	}
	public Date getDataDemissaoAntipenultimaEmpresa() {
		return dataDemissaoAntipenultimaEmpresa;
	}
	public void setDataDemissaoAntipenultimaEmpresa(Date dataDemissaoAntipenultimaEmpresa) {
		this.dataDemissaoAntipenultimaEmpresa = dataDemissaoAntipenultimaEmpresa;
	}
	public Profissao getProfissaoAntipenultimaEmpresa() {
		return profissaoAntipenultimaEmpresa;
	}
	public void setProfissaoAntipenultimaEmpresa(Profissao profissaoAntipenultimaEmpresa) {
		this.profissaoAntipenultimaEmpresa = profissaoAntipenultimaEmpresa;
	}
	public String getPenultimaEmpresa() {
		return penultimaEmpresa;
	}
	public void setPenultimaEmpresa(String penultimaEmpresa) {
		this.penultimaEmpresa = penultimaEmpresa;
	}
	public Date getDataAdmissaoPenultimaEmpresa() {
		return dataAdmissaoPenultimaEmpresa;
	}
	public void setDataAdmissaoPenultimaEmpresa(Date dataAdmissaoPenultimaEmpresa) {
		this.dataAdmissaoPenultimaEmpresa = dataAdmissaoPenultimaEmpresa;
	}
	public Date getDataDemissaoPenultimaEmpresa() {
		return dataDemissaoPenultimaEmpresa;
	}
	public void setDataDemissaoPenultimaEmpresa(Date dataDemissaoPenultimaEmpresa) {
		this.dataDemissaoPenultimaEmpresa = dataDemissaoPenultimaEmpresa;
	}
	public Profissao getProfissaoPenultimaEmpresa() {
		return profissaoPenultimaEmpresa;
	}
	public void setProfissaoPenultimaEmpresa(Profissao profissaoPenultimaEmpresa) {
		this.profissaoPenultimaEmpresa = profissaoPenultimaEmpresa;
	}
	public String getUltimaEmpresa() {
		return ultimaEmpresa;
	}
	public void setUltimaEmpresa(String ultimaEmpresa) {
		this.ultimaEmpresa = ultimaEmpresa;
	}
	public Date getDataAdmissaoultimaEmpresa() {
		return dataAdmissaoultimaEmpresa;
	}
	public void setDataAdmissaoultimaEmpresa(Date dataAdmissaoultimaEmpresa) {
		this.dataAdmissaoultimaEmpresa = dataAdmissaoultimaEmpresa;
	}
	public Date getDataDemissaoultimaEmpresa() {
		return dataDemissaoultimaEmpresa;
	}
	public void setDataDemissaoultimaEmpresa(Date dataDemissaoultimaEmpresa) {
		this.dataDemissaoultimaEmpresa = dataDemissaoultimaEmpresa;
	}
	public Profissao getProfissaoUltimaEmpresa() {
		return profissaoUltimaEmpresa;
	}
	public void setProfissaoUltimaEmpresa(Profissao profissaoUltimaEmpresa) {
		this.profissaoUltimaEmpresa = profissaoUltimaEmpresa;
	}
	public String getFuncoesUltimaEmpresa() {
		return funcoesUltimaEmpresa;
	}
	public void setFuncoesUltimaEmpresa(String funcoesUltimaEmpresa) {
		this.funcoesUltimaEmpresa = funcoesUltimaEmpresa;
	}
	public String getFuncoesPenultimaEmpresa() {
		return funcoesPenultimaEmpresa;
	}
	public void setFuncoesPenultimaEmpresa(String funcoesPenultimaEmpresa) {
		this.funcoesPenultimaEmpresa = funcoesPenultimaEmpresa;
	}
	public String getFuncoesAntiPenultimaEmpresa() {
		return funcoesAntiPenultimaEmpresa;
	}
	public void setFuncoesAntiPenultimaEmpresa(String funcoesAntiPenultimaEmpresa) {
		this.funcoesAntiPenultimaEmpresa = funcoesAntiPenultimaEmpresa;
	}
	
}
