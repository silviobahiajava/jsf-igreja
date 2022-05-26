package meucursoJPA.model.financeiro;

import enums.TipoMovimentacao;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.419-0300")
@StaticMetamodel(MovimentacaoFinanceira.class)
public class MovimentacaoFinanceira_ extends GenericDomain_ {
	public static volatile SingularAttribute<MovimentacaoFinanceira, Date> dataMovimentacao;
	public static volatile SingularAttribute<MovimentacaoFinanceira, CategoriaDeMovimentacao> categoriaMovimentacao;
	public static volatile SingularAttribute<MovimentacaoFinanceira, BalancoFinanceiro> balanco;
	public static volatile SingularAttribute<MovimentacaoFinanceira, TipoMovimentacao> tipoMovimentacao;
	public static volatile SingularAttribute<MovimentacaoFinanceira, String> descricao;
	public static volatile SingularAttribute<MovimentacaoFinanceira, BigDecimal> valor;
}
