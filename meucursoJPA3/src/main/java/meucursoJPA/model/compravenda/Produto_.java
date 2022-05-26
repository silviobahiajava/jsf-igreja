package meucursoJPA.model.compravenda;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.257-0300")
@StaticMetamodel(Produto.class)
public class Produto_ extends GenericDomain_ {
	public static volatile SingularAttribute<Produto, String> descricao;
	public static volatile SingularAttribute<Produto, BigDecimal> preco;
	public static volatile SingularAttribute<Produto, Integer> quantidade;
	public static volatile SingularAttribute<Produto, BigDecimal> total;
}
