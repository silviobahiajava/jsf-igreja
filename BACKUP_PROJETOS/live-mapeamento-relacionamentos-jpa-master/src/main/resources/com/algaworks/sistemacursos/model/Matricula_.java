package com.algaworks.sistemacursos.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-05-20T01:38:02.571-0300")
@StaticMetamodel(Matricula.class)
public class Matricula_ {
	public static volatile SingularAttribute<Matricula, Integer> id;
	public static volatile SingularAttribute<Matricula, LocalDateTime> dataMatricula;
	public static volatile SingularAttribute<Matricula, Curso> curso;
	public static volatile SingularAttribute<Matricula, Aluno> aluno;
	public static volatile SingularAttribute<Matricula, Pagamento> pagamento;
}
