package br.com.souzawebsistemas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.souzawebsistemas.model.Cliente;
import br.com.souzawebsistemas.model.Telefone;

@Repository
@Transactional
public interface TelefoneRepository extends JpaRepository<Telefone,Long>{
	
	Iterable<Telefone>findByCliente(Cliente cliente);
}
