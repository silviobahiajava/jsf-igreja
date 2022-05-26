package meucursoJPA.model.ministerioinfantil;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.financeiro.Lancamento;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.485-0300")
@StaticMetamodel(OfertaDasCriancas.class)
public class OfertaDasCriancas_ extends GenericDomain_ {
	public static volatile SingularAttribute<OfertaDasCriancas, Date> dataOfertaCrianca;
	public static volatile SingularAttribute<OfertaDasCriancas, Lancamento> lancamento;
	public static volatile SingularAttribute<OfertaDasCriancas, String> descricao;
	public static volatile SingularAttribute<OfertaDasCriancas, Grupo> grupo;
	public static volatile SingularAttribute<OfertaDasCriancas, BigDecimal> valorOfertaCrianca;
}
