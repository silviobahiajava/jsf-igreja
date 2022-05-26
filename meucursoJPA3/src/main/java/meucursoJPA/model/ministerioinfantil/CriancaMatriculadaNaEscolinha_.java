package meucursoJPA.model.ministerioinfantil;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.escoladominical.AulaEscolaDominical;

@Generated(value="Dali", date="2022-05-19T18:35:23.476-0300")
@StaticMetamodel(CriancaMatriculadaNaEscolinha.class)
public class CriancaMatriculadaNaEscolinha_ extends GenericDomain_ {
	public static volatile SingularAttribute<CriancaMatriculadaNaEscolinha, Crianca> crianca;
	public static volatile SingularAttribute<CriancaMatriculadaNaEscolinha, Date> dataMatricula;
	public static volatile SingularAttribute<CriancaMatriculadaNaEscolinha, AulaEscolaDominical> aula;
}
