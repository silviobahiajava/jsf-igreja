package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.308-0300")
@StaticMetamodel(CompraDosVaroes.class)
public class CompraDosVaroes_ extends GenericDomain_ {
	public static volatile SingularAttribute<CompraDosVaroes, Date> dataCompraVarao;
	public static volatile SingularAttribute<CompraDosVaroes, Boolean> compraAvistaVarao;
	public static volatile SingularAttribute<CompraDosVaroes, Boolean> compraAprazoVarao;
	public static volatile SingularAttribute<CompraDosVaroes, String> tipoCompra;
	public static volatile SingularAttribute<CompraDosVaroes, BigDecimal> valorCompraVarao;
	public static volatile SingularAttribute<CompraDosVaroes, String> descricao;
	public static volatile SingularAttribute<CompraDosVaroes, String> numeroNota;
	public static volatile SingularAttribute<CompraDosVaroes, Grupo> grupo;
	public static volatile SingularAttribute<CompraDosVaroes, Lancamento> lancamento;
}
