package com.algaworks.sistemacursos.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-05-20T01:38:02.566-0300")
@StaticMetamodel(Aula.class)
public class Aula_ {
	public static volatile SingularAttribute<Aula, Integer> id;
	public static volatile SingularAttribute<Aula, String> nome;
	public static volatile SingularAttribute<Aula, Modulo> modulo;
}
