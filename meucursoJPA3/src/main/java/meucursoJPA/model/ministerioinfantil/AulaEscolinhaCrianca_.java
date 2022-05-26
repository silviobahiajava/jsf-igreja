package meucursoJPA.model.ministerioinfantil;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.463-0300")
@StaticMetamodel(AulaEscolinhaCrianca.class)
public class AulaEscolinhaCrianca_ extends GenericDomain_ {
	public static volatile SingularAttribute<AulaEscolinhaCrianca, Date> dataAula;
	public static volatile SingularAttribute<AulaEscolinhaCrianca, Integer> totalCriancasPresentes;
	public static volatile SingularAttribute<AulaEscolinhaCrianca, Integer> totalCriancasAusentes;
	public static volatile SingularAttribute<AulaEscolinhaCrianca, Integer> totalMatriculado;
}
