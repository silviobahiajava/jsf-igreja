package br.com.souzawebsistemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.souzawebsistemas.model.Convidado;
import br.com.souzawebsistemas.model.Evento;
import br.com.souzawebsistemas.repositories.ConvidadoRepository;

@Service
public class ConvidadoService {
	@Autowired
	  private ConvidadoRepository rep;
		
		 public void cadastrar(Convidado convidado) {
			 rep.save(convidado);
		 }
		 
		 public Optional<Convidado> buscarPorCodigo(Long codigo) {
			 return rep.findById(codigo);
		 }
		 
		 public List<Convidado>buscarTodos(){
			 return rep.findAll();
		 }
		 
		 public void excluir(Convidado convidado) {
			// rep.deleteById(codigo);
			 rep.delete(convidado);
		 }
		 public void excluirPorCodigo(Long codigo) {
			 rep.deleteById(codigo);
		 }
		 
		 public Convidado buscar(Long codigo) {
			 return rep.getById(codigo);
		 }
		 
		 public void atualizar(Convidado convidado) {
			  rep.saveAndFlush(convidado);
		 }
		 
		 
		 public Iterable<Convidado> buscarPorEvento(Evento evento){
			 return rep.findByEvento(evento);
			
		 }
		 
}
