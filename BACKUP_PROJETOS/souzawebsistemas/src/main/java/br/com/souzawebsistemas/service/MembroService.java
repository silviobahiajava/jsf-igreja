package br.com.souzawebsistemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.souzawebsistemas.model.Membro;
import br.com.souzawebsistemas.repositories.MembroRepository;

@Service
public class MembroService {
	@Autowired
  private MembroRepository rep;
	
	 public void cadastrar(Membro obj) {
		 rep.save(obj);
	 }
	 
	 public Optional<Membro> buscarPorCodigo(Long codigo) {
		 return rep.findById(codigo);
	 }
	 
	 public List<Membro>buscarTodos(){
		 return rep.findAll();
	 }
	 
	 public void excluir(Membro obj) {
		// rep.deleteById(codigo);
		 rep.delete(obj);
	 }
	 public void excluirPorCodigo(Long codigo) {
		 rep.deleteById(codigo);
	 }
	 
	 public Membro buscar(Long codigo) {
		 return rep.getById(codigo);
	 }
	 
	 public void atualizar(Membro obj) {
		  rep.saveAndFlush(obj);
	 }
}
