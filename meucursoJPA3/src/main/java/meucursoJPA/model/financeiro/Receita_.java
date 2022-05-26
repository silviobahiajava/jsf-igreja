package meucursoJPA.model.financeiro;

import enums.TipoReceita;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.446-0300")
@StaticMetamodel(Receita.class)
public class Receita_ extends GenericDomain_ {
	public static volatile SingularAttribute<Receita, Date> dataLancamento;
	public static volatile SingularAttribute<Receita, BigDecimal> valorDaReceita;
	public static volatile SingularAttribute<Receita, String> descricaoDaReceita;
	public static volatile SingularAttribute<Receita, TipoReceita> tipoDeReceita;
	public static volatile SingularAttribute<Receita, Grupo> grupo;
}
