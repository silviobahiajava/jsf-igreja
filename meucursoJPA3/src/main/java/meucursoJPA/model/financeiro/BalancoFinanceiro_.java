package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.276-0300")
@StaticMetamodel(BalancoFinanceiro.class)
public class BalancoFinanceiro_ extends GenericDomain_ {
	public static volatile SingularAttribute<BalancoFinanceiro, BigDecimal> valorEntrada;
	public static volatile SingularAttribute<BalancoFinanceiro, BigDecimal> valorSaida;
	public static volatile SingularAttribute<BalancoFinanceiro, BigDecimal> resultado;
	public static volatile SingularAttribute<BalancoFinanceiro, Date> dataIncial;
	public static volatile SingularAttribute<BalancoFinanceiro, Date> dataFinal;
	public static volatile SingularAttribute<BalancoFinanceiro, BalancoGeral> balancoGeral;
}
