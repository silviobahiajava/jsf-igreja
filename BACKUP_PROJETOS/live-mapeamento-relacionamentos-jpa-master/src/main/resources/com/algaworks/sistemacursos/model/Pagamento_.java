package com.algaworks.sistemacursos.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-05-20T01:38:02.581-0300")
@StaticMetamodel(Pagamento.class)
public class Pagamento_ {
	public static volatile SingularAttribute<Pagamento, Integer> id;
	public static volatile SingularAttribute<Pagamento, BigDecimal> valor;
	public static volatile SingularAttribute<Pagamento, Matricula> matricula;
}
