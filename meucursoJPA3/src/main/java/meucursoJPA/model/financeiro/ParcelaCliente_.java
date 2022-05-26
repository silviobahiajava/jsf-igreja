package meucursoJPA.model.financeiro;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.441-0300")
@StaticMetamodel(ParcelaCliente.class)
public class ParcelaCliente_ extends GenericDomain_ {
	public static volatile SingularAttribute<ParcelaCliente, Date> dataVencimentoDaParcela;
	public static volatile SingularAttribute<ParcelaCliente, Integer> numerodaparcela;
	public static volatile SingularAttribute<ParcelaCliente, Date> dataPagamentoDaParcela;
	public static volatile SingularAttribute<ParcelaCliente, Double> valorDaParcela;
	public static volatile SingularAttribute<ParcelaCliente, PagamentoCliente> pagamento;
}
