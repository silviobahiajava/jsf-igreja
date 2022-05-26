package br.com.souzawebsistemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.souzawebsistemas.model.*;
import br.com.souzawebsistemas.repositories.*;

@Service
public class EstabelecimentoService {
	@Autowired
  private EstabelecimentoRepository rep;
	
	 public void cadastrar(Estabelecimento obj) {
		 rep.save(obj);
	 }
	 
	 public Optional<Estabelecimento> buscarPorCodigo(Long codigo) {
		 return rep.findById(codigo);
	 }
	 
	 public List<Estabelecimento>buscarTodos(){
		 return rep.findAll();
	 }
	 
	 public void excluir(Estabelecimento obj) {
		// rep.deleteById(codigo);
		 rep.delete(obj);
	 }
	 public void excluirPorCodigo(Long codigo) {
		 rep.deleteById(codigo);
	 }
	 
	 public Estabelecimento buscar(Long codigo) {
		 return rep.getById(codigo);
	 }
	 
	 public void atualizar(Estabelecimento obj) {
		  rep.saveAndFlush(obj);
	 }
}
