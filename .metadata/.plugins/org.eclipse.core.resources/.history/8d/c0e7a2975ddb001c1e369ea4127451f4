package cadastrocursos.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;



@Entity
@Table(name="aluno")
public class Aluno {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    @ManyToMany(mappedBy = "alunos")
    private List<Curso> cursos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
    
    
}
