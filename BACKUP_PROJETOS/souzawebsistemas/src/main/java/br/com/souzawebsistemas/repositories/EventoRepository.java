package br.com.souzawebsistemas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.souzawebsistemas.model.Evento;

public interface EventoRepository extends JpaRepository<Evento,Long>{

}
