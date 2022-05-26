package meucursoJPA.model.financeiro;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;
import meucursoJPA.model.secretaria.Grupo;

@Generated(value="Dali", date="2022-05-19T18:35:23.443-0300")
@StaticMetamodel(ProdutoFabricado.class)
public class ProdutoFabricado_ extends GenericDomain_ {
	public static volatile SingularAttribute<ProdutoFabricado, String> descricao;
	public static volatile SingularAttribute<ProdutoFabricado, BigDecimal> precoDeVenda;
	public static volatile SingularAttribute<ProdutoFabricado, Integer> quantidadeMateriaPrimaUsada;
	public static volatile SingularAttribute<ProdutoFabricado, Integer> quantidadeFabricada;
	public static volatile SingularAttribute<ProdutoFabricado, Grupo> grupo;
}
