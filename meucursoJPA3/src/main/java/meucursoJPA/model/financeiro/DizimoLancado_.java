package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Membro;

@Generated(value="Dali", date="2022-05-19T18:35:23.404-0300")
@StaticMetamodel(DizimoLancado.class)
public class DizimoLancado_ extends GenericDomain_ {
	public static volatile SingularAttribute<DizimoLancado, BigDecimal> valorDizimo;
	public static volatile SingularAttribute<DizimoLancado, Date> dataLancamento;
	public static volatile SingularAttribute<DizimoLancado, Membro> dizimista;
}
