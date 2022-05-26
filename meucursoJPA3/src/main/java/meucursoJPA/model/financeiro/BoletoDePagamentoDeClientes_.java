package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.285-0300")
@StaticMetamodel(BoletoDePagamentoDeClientes.class)
public class BoletoDePagamentoDeClientes_ extends GenericDomain_ {
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> nomeCedente;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> documentoCedente;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> numeroDocumento;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> nossoNumero;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> digitoNossoNumero;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, BigDecimal> valorBoleto;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, BigDecimal> desconto;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, BigDecimal> deducao;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, BigDecimal> mora;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, BigDecimal> acrescimo;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, BigDecimal> valorcobrado;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, Date> dataDocumento;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, Date> dataVencimento;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, Date> dataGeracaoBoleto;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> localPreferidoPagamento;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> nomeSacado;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> documentoSacado;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> cepSacado;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> enderecoSacado;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> numeroSacado;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> bairroSacado;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> cidadeSacado;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> estadoSacado;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, Boolean> pessoaFisica;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, Boolean> pessoaJuridica;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> localidadeSacado;
	public static volatile SingularAttribute<BoletoDePagamentoDeClientes, String> numeroLocalidade;
}
