package meucursoJPA.model.escoladominical;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.259-0300")
@StaticMetamodel(AlunosAusentesDaEscolaDominical.class)
public class AlunosAusentesDaEscolaDominical_ extends GenericDomain_ {
	public static volatile SingularAttribute<AlunosAusentesDaEscolaDominical, Date> dataAula;
	public static volatile SingularAttribute<AlunosAusentesDaEscolaDominical, AlunosMatriculadosEscolaDominical> matriculado;
	public static volatile SingularAttribute<AlunosAusentesDaEscolaDominical, AulaEscolaDominical> aula;
}
