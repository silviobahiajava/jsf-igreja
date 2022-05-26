package meucursoJPA.model.ministerioinfantil;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.financeiro.Lancamento;
import meucursoJPA.model.secretaria.Grupo;
import meucursoJPA.model.secretaria.Membro;

@Generated(value="Dali", date="2022-05-19T18:35:23.470-0300")
@StaticMetamodel(CampanhaDoPeCriancas.class)
public class CampanhaDoPeCriancas_ extends GenericDomain_ {
	public static volatile SingularAttribute<CampanhaDoPeCriancas, String> descricao;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, BigDecimal> taxaContribuicao;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, Integer> numeroParcelasPagas;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, BigDecimal> valorRecebido;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, BigDecimal> valorPraPagar;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, BigDecimal> trocoPraDevolver;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, OfertaDasCriancas> trocoParaOferta;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, String> objetivoCampanha;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, Grupo> grupo;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, Date> dataPagamento;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, Membro> contribuinte;
	public static volatile SingularAttribute<CampanhaDoPeCriancas, Lancamento> lancamento;
}
