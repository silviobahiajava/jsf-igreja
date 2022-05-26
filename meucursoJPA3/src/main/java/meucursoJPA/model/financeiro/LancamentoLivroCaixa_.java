package meucursoJPA.model.financeiro;

import enums.TipoDespesa;
import enums.TipoMovimentacao;
import enums.TipoReceita;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.410-0300")
@StaticMetamodel(LancamentoLivroCaixa.class)
public class LancamentoLivroCaixa_ extends GenericDomain_ {
	public static volatile SingularAttribute<LancamentoLivroCaixa, Date> dataDoLancamento;
	public static volatile SingularAttribute<LancamentoLivroCaixa, TipoMovimentacao> tipoMovimentacao;
	public static volatile SingularAttribute<LancamentoLivroCaixa, TipoDespesa> tipoDespesa;
	public static volatile SingularAttribute<LancamentoLivroCaixa, ContaAPagar> contaPraPagar;
	public static volatile SingularAttribute<LancamentoLivroCaixa, TipoReceita> tipoReceita;
	public static volatile SingularAttribute<LancamentoLivroCaixa, ContasPagas> contasPagas;
	public static volatile SingularAttribute<LancamentoLivroCaixa, Grupo> grupo;
	public static volatile SingularAttribute<LancamentoLivroCaixa, BigDecimal> saldoAnterior;
	public static volatile SingularAttribute<LancamentoLivroCaixa, BigDecimal> receita;
	public static volatile SingularAttribute<LancamentoLivroCaixa, BigDecimal> despesa;
	public static volatile SingularAttribute<LancamentoLivroCaixa, BigDecimal> saldo;
	public static volatile SingularAttribute<LancamentoLivroCaixa, String> historico;
}
