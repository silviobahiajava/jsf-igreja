package meucursoJPA.model.secretaria;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.administrativo.Usuario;

@Generated(value="Dali", date="2022-05-19T18:35:23.533-0300")
@StaticMetamodel(Grupo.class)
public class Grupo_ extends GenericDomain_ {
	public static volatile SingularAttribute<Grupo, String> nome;
	public static volatile SingularAttribute<Grupo, Usuario> usuario;
}
