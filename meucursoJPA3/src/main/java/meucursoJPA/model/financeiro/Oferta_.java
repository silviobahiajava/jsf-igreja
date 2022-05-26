package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.422-0300")
@StaticMetamodel(Oferta.class)
public class Oferta_ extends GenericDomain_ {
	public static volatile SingularAttribute<Oferta, Date> dataOferta;
	public static volatile SingularAttribute<Oferta, Lancamento> lancamento;
	public static volatile SingularAttribute<Oferta, LancamentoLivroCaixa> lancamentoLivroCaixa;
	public static volatile SingularAttribute<Oferta, Grupo> grupo;
	public static volatile SingularAttribute<Oferta, BigDecimal> valorOferta;
}
