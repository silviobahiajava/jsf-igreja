package meucursoJPA.model.ministerioinfantil;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Filiacao;

@Generated(value="Dali", date="2022-05-19T18:35:23.474-0300")
@StaticMetamodel(Crianca.class)
public class Crianca_ extends GenericDomain_ {
	public static volatile SingularAttribute<Crianca, String> nome;
	public static volatile SingularAttribute<Crianca, Filiacao> filiacao;
	public static volatile SingularAttribute<Crianca, String> endereco;
	public static volatile SingularAttribute<Crianca, String> numeroEndereco;
	public static volatile SingularAttribute<Crianca, String> bairro;
	public static volatile SingularAttribute<Crianca, String> idade;
	public static volatile SingularAttribute<Crianca, Date> dataNascimento;
}
