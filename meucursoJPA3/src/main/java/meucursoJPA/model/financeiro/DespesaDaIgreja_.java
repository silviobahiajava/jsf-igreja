package meucursoJPA.model.financeiro;

import enums.SitucaoDaConta;
import enums.TipoDespesa;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.351-0300")
@StaticMetamodel(DespesaDaIgreja.class)
public class DespesaDaIgreja_ extends GenericDomain_ {
	public static volatile SingularAttribute<DespesaDaIgreja, Grupo> grupo;
	public static volatile SingularAttribute<DespesaDaIgreja, Lancamento> lancamento;
	public static volatile SingularAttribute<DespesaDaIgreja, String> descricaoDespesaDaIgreja;
	public static volatile SingularAttribute<DespesaDaIgreja, BigDecimal> valorDespescaDaIgreja;
	public static volatile SingularAttribute<DespesaDaIgreja, Date> dataPagamentoDespesaDaIgreja;
	public static volatile SingularAttribute<DespesaDaIgreja, Date> dataVencimentoDespesaDaIgreja;
	public static volatile SingularAttribute<DespesaDaIgreja, SitucaoDaConta> situacaoDaDespesaEnum;
	public static volatile SingularAttribute<DespesaDaIgreja, TipoDespesa> tipoDespesaEnum;
}
