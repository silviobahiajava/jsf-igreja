package meucursoJPA.model.financeiro;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.294-0300")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends GenericDomain_ {
	public static volatile SingularAttribute<Cliente, String> nomeCliente;
	public static volatile SingularAttribute<Cliente, String> cpfCliente;
	public static volatile SingularAttribute<Cliente, String> emailCliente;
	public static volatile SingularAttribute<Cliente, String> logradouroCliente;
	public static volatile SingularAttribute<Cliente, String> numeroLogradouroCliente;
	public static volatile SingularAttribute<Cliente, String> bairroCliente;
	public static volatile SingularAttribute<Cliente, String> cidadeCliente;
	public static volatile SingularAttribute<Cliente, String> cep;
	public static volatile SingularAttribute<Cliente, Date> dataCadastro;
}
