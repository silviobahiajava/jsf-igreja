package meucursoJPA.model.financeiro;

import enums.TipoProcessoEnum;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.436-0300")
@StaticMetamodel(PagamentoCliente.class)
public class PagamentoCliente_ extends GenericDomain_ {
	public static volatile SingularAttribute<PagamentoCliente, Cliente> cliente;
	public static volatile SingularAttribute<PagamentoCliente, Double> valorTotal;
	public static volatile SingularAttribute<PagamentoCliente, Date> dataProcesso;
	public static volatile SingularAttribute<PagamentoCliente, TipoProcessoEnum> tipoProcessoEnum;
}
