package br.com.souzawebsistemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.souzawebsistemas.model.Evento;
import br.com.souzawebsistemas.repositories.EventoRepository;

@Service
public class EventoService {
	@Autowired
	  private EventoRepository rep;
		
		 public void cadastrar(Evento evento) {
			 rep.save(evento);
		 }
		 
		 public Optional<Evento> buscarPorCodigo(Long codigo) {
			 return rep.findById(codigo);
		 }
		 
		 public List<Evento>buscarTodos(){
			 return rep.findAll();
		 }
		 
		 public void excluir(Evento evento) {
			// rep.deleteById(codigo);
			 rep.delete(evento);
		 }
		 public void excluirPorCodigo(Long codigo) {
			 rep.deleteById(codigo);
		 }
		 
		 public Evento buscar(Long codigo) {
			 return rep.getById(codigo);
		 }
		 
		 public void atualizar(Evento evento) {
			  rep.saveAndFlush(evento);
		 }
}
