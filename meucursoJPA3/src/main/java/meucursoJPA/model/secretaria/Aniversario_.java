package meucursoJPA.model.secretaria;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.ministerioinfantil.Crianca;

@Generated(value="Dali", date="2022-05-19T18:35:23.488-0300")
@StaticMetamodel(Aniversario.class)
public class Aniversario_ extends GenericDomain_ {
	public static volatile SingularAttribute<Aniversario, Date> dataAniversario;
	public static volatile SingularAttribute<Aniversario, Boolean> adulto;
	public static volatile SingularAttribute<Aniversario, Boolean> crianca;
	public static volatile SingularAttribute<Aniversario, Membro> aniversarianteAdulto;
	public static volatile SingularAttribute<Aniversario, Crianca> aniversarianteInfantil;
}
