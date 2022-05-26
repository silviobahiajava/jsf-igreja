package meucursoJPA.model.administrativo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.245-0300")
@StaticMetamodel(Usuario.class)
public class Usuario_ extends GenericDomain_ {
	public static volatile SingularAttribute<Usuario, String> login;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, String> cpf;
	public static volatile SingularAttribute<Usuario, Grupo> grupo;
}
