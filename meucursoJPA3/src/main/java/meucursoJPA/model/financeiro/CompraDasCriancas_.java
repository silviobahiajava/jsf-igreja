package meucursoJPA.model.financeiro;

import enums.TipoCompra;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.305-0300")
@StaticMetamodel(CompraDasCriancas.class)
public class CompraDasCriancas_ extends GenericDomain_ {
	public static volatile SingularAttribute<CompraDasCriancas, Date> dataCompraCrianca;
	public static volatile SingularAttribute<CompraDasCriancas, BigDecimal> valorCompraCrianca;
	public static volatile SingularAttribute<CompraDasCriancas, TipoCompra> tipodecompra;
	public static volatile SingularAttribute<CompraDasCriancas, String> descricao;
	public static volatile SingularAttribute<CompraDasCriancas, String> numeroNota;
	public static volatile SingularAttribute<CompraDasCriancas, Grupo> grupo;
	public static volatile SingularAttribute<CompraDasCriancas, Lancamento> lancamento;
}
