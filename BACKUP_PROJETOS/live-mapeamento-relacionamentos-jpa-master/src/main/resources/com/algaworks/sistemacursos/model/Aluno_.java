package com.algaworks.sistemacursos.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-05-20T01:38:02.373-0300")
@StaticMetamodel(Aluno.class)
public class Aluno_ {
	public static volatile SingularAttribute<Aluno, Integer> id;
	public static volatile SingularAttribute<Aluno, String> nome;
	public static volatile ListAttribute<Aluno, Curso> cursos;
}
