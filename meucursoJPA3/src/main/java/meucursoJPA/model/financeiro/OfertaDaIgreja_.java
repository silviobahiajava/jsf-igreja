package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.425-0300")
@StaticMetamodel(OfertaDaIgreja.class)
public class OfertaDaIgreja_ extends GenericDomain_ {
	public static volatile SingularAttribute<OfertaDaIgreja, Date> dataOfertaIgreja;
	public static volatile SingularAttribute<OfertaDaIgreja, Lancamento> lancamento;
	public static volatile SingularAttribute<OfertaDaIgreja, String> descricao;
	public static volatile SingularAttribute<OfertaDaIgreja, String> tipodeoferta;
	public static volatile SingularAttribute<OfertaDaIgreja, Grupo> grupo;
	public static volatile SingularAttribute<OfertaDaIgreja, BigDecimal> valorOfertaIgreja;
}
