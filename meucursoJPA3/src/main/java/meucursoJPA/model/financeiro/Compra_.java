package meucursoJPA.model.financeiro;

import enums.TipoCompra;
import enums.TipoPagamento;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.299-0300")
@StaticMetamodel(Compra.class)
public class Compra_ extends GenericDomain_ {
	public static volatile SingularAttribute<Compra, Date> dataCompra;
	public static volatile SingularAttribute<Compra, String> numeroPedido;
	public static volatile SingularAttribute<Compra, String> descricao;
	public static volatile SingularAttribute<Compra, String> observacao;
	public static volatile SingularAttribute<Compra, BigDecimal> precodacompra;
	public static volatile SingularAttribute<Compra, LancamentoLivroCaixa> lancamentoLivroCaixa;
	public static volatile SingularAttribute<Compra, DataLancamento> dataLancamentoCompra;
	public static volatile SingularAttribute<Compra, Grupo> grupo;
	public static volatile SingularAttribute<Compra, Lancamento> lancamento;
	public static volatile SingularAttribute<Compra, Loja> loja;
	public static volatile SingularAttribute<Compra, TipoPagamento> tipoPagamento;
	public static volatile SingularAttribute<Compra, TipoCompra> tipoCompra;
	public static volatile SingularAttribute<Compra, ContaAPagar> contaAPagar;
	public static volatile SingularAttribute<Compra, ContasPagas> contasPagas;
}
