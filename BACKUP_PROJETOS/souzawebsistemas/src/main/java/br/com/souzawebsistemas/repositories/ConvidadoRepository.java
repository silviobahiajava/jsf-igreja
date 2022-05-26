package br.com.souzawebsistemas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.souzawebsistemas.model.Convidado;
import br.com.souzawebsistemas.model.Evento;

public interface ConvidadoRepository extends JpaRepository<Convidado,Long> {
	Iterable<Convidado>findByEvento(Evento evento);
	
}
