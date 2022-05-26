package meucursoJPA.model.financeiro;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.283-0300")
@StaticMetamodel(BoletoClienteProcesso.class)
public class BoletoClienteProcesso_ extends GenericDomain_ {
	public static volatile SingularAttribute<BoletoClienteProcesso, Integer> numeroConta;
	public static volatile SingularAttribute<BoletoClienteProcesso, String> digitoNumeroConta;
	public static volatile SingularAttribute<BoletoClienteProcesso, Integer> carteira;
	public static volatile SingularAttribute<BoletoClienteProcesso, Integer> agencia;
	public static volatile SingularAttribute<BoletoClienteProcesso, String> digitoAgencia;
	public static volatile SingularAttribute<BoletoClienteProcesso, String> numeroDocumento;
	public static volatile SingularAttribute<BoletoClienteProcesso, String> nossoNumero;
	public static volatile SingularAttribute<BoletoClienteProcesso, String> digitoNossoNumero;
}
