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

@Generated(value="Dali", date="2022-05-19T18:35:23.368-0300")
@StaticMetamodel(DespesasPendentesDaIgreja.class)
public class DespesasPendentesDaIgreja_ extends GenericDomain_ {
	public static volatile SingularAttribute<DespesasPendentesDaIgreja, Grupo> grupo;
	public static volatile SingularAttribute<DespesasPendentesDaIgreja, Lancamento> lancamento;
	public static volatile SingularAttribute<DespesasPendentesDaIgreja, DespesaDaIgreja> despesaDaIgreja;
	public static volatile SingularAttribute<DespesasPendentesDaIgreja, String> descricaoDespesaPendenteDaIgreja;
	public static volatile SingularAttribute<DespesasPendentesDaIgreja, BigDecimal> valorDespesaPendenteDaIgeja;
	public static volatile SingularAttribute<DespesasPendentesDaIgreja, Date> dataPagamentoDespesaPendenteDaIgreja;
	public static volatile SingularAttribute<DespesasPendentesDaIgreja, Date> dataVencimentoDespesaPendenteDaIgreja;
	public static volatile SingularAttribute<DespesasPendentesDaIgreja, SitucaoDaConta> situacaoDaDespesaPendenteEnum;
	public static volatile SingularAttribute<DespesasPendentesDaIgreja, TipoDespesa> tipoDespesaEnum;
}
