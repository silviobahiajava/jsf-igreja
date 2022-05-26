package br.com.souzawebsistemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.souzawebsistemas.model.Cliente;
import br.com.souzawebsistemas.model.Telefone;
import br.com.souzawebsistemas.repositories.ClienteRepository;
import br.com.souzawebsistemas.repositories.TelefoneRepository;

@Service
public class TelefoneService {
	@Autowired
  private TelefoneRepository rep;
	
	 public void cadastrar(Telefone telefone) {
		 rep.save(telefone);
	 }
	 
	 public Optional<Telefone> buscarPorCodigo(Long codigo) {
		 return rep.findById(codigo);
	 }
	 
	 public List<Telefone>buscarTodos(){
		 return rep.findAll();
	 }
	 
	 public void excluir(Telefone telefone) {
		// rep.deleteById(codigo);
		 rep.delete(telefone);
	 }
	 public void excluirPorCodigo(Long codigo) {
		 rep.deleteById(codigo);
	 }
	 
	 public Telefone buscar(Long codigo) {
		 return rep.getById(codigo);
	 }
	 
	 public void atualizar(Telefone telefone) {
		  rep.saveAndFlush(telefone);
	 }
	 
	public Iterable<Telefone>buscarTelefonesPorCliente(Cliente cliente){
		return rep.findByCliente(cliente);
	}
	public void salvarTelefones(List<Telefone>telefones) {
		rep.saveAll(telefones);
	}
}
