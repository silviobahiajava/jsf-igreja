package br.com.souzawebsistemas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.souzawebsistemas.model.Produto;


public interface FuncionarioRepository extends JpaRepository<Produto,Long>{

}
