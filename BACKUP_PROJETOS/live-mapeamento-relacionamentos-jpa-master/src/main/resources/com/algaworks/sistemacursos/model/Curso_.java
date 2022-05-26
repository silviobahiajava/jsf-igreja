package com.algaworks.sistemacursos.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-05-20T01:38:02.568-0300")
@StaticMetamodel(Curso.class)
public class Curso_ {
	public static volatile SingularAttribute<Curso, Integer> id;
	public static volatile SingularAttribute<Curso, String> nome;
	public static volatile ListAttribute<Curso, Modulo> modulos;
	public static volatile ListAttribute<Curso, Aluno> alunos;
	public static volatile ListAttribute<Curso, Matricula> matriculas;
}
