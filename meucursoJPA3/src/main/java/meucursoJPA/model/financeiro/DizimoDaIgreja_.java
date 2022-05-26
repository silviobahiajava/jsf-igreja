package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;
import meucursoJPA.model.secretaria.Membro;

@Generated(value="Dali", date="2022-05-19T18:35:23.376-0300")
@StaticMetamodel(DizimoDaIgreja.class)
public class DizimoDaIgreja_ extends GenericDomain_ {
	public static volatile SingularAttribute<DizimoDaIgreja, Date> dataDizimoIgreja;
	public static volatile SingularAttribute<DizimoDaIgreja, String> descricao;
	public static volatile SingularAttribute<DizimoDaIgreja, String> tipodizimo;
	public static volatile SingularAttribute<DizimoDaIgreja, Membro> dizimistaIgreja;
	public static volatile SingularAttribute<DizimoDaIgreja, BigDecimal> valorDizimoIgreja;
	public static volatile SingularAttribute<DizimoDaIgreja, Lancamento> lancamento;
	public static volatile SingularAttribute<DizimoDaIgreja, Grupo> grupo;
}
