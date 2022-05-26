package br.com.souzawebsistemas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.souzawebsistemas.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita,Long>{

}
