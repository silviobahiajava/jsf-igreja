package br.com.souzawebsistemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.souzawebsistemas.model.Cliente;
import br.com.souzawebsistemas.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
  private ClienteRepository rep;
	
	 public void cadastrar(Cliente cliente) {
		 rep.save(cliente);
	 }
	 
	 public Optional<Cliente> buscarPorCodigo(Long codigo) {
		 return rep.findById(codigo);
	 }
	 
	 public List<Cliente>buscarTodos(){
		 return rep.findAll();
	 }
	 
	 public void excluir(Cliente cliente) {
		// rep.deleteById(codigo);
		 rep.delete(cliente);
	 }
	 public void excluirPorCodigo(Long codigo) {
		 rep.deleteById(codigo);
	 }
	 
	 public Cliente buscar(Long codigo) {
		 return rep.getById(codigo);
	 }
	 
	 public void atualizar(Cliente cliente) {
		  rep.saveAndFlush(cliente);
	 }
}
