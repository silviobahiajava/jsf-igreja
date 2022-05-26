package meucursoJPA.model.secretaria;

import enums.Profissao;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.500-0300")
@StaticMetamodel(EmpresaCurriculo.class)
public class EmpresaCurriculo_ extends GenericDomain_ {
	public static volatile SingularAttribute<EmpresaCurriculo, String> antipenultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, Date> dataAdmissaoAntipenultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, Date> dataDemissaoAntipenultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, Profissao> profissaoAntipenultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, String> funcoesUltimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, String> penultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, Date> dataAdmissaoPenultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, Date> dataDemissaoPenultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, Profissao> profissaoPenultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, String> funcoesPenultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, String> ultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, Date> dataAdmissaoultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, Date> dataDemissaoultimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, Profissao> profissaoUltimaEmpresa;
	public static volatile SingularAttribute<EmpresaCurriculo, String> funcoesAntiPenultimaEmpresa;
}
