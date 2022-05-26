package meucursoJPA.model.escoladominical;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Membro;

@Generated(value="Dali", date="2022-05-19T18:35:23.262-0300")
@StaticMetamodel(AlunosMatriculadosEscolaDominical.class)
public class AlunosMatriculadosEscolaDominical_ extends GenericDomain_ {
	public static volatile SingularAttribute<AlunosMatriculadosEscolaDominical, Membro> membro;
	public static volatile SingularAttribute<AlunosMatriculadosEscolaDominical, Date> dataMatricula;
	public static volatile SingularAttribute<AlunosMatriculadosEscolaDominical, ClasseEscolaDominical> classe;
	public static volatile SingularAttribute<AlunosMatriculadosEscolaDominical, AulaEscolaDominical> aula;
}
