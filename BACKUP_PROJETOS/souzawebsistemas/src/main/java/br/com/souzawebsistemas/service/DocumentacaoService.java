package br.com.souzawebsistemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.souzawebsistemas.model.Cliente;
import br.com.souzawebsistemas.model.Documentacao;
import br.com.souzawebsistemas.repositories.DocumentacaoRepository;

@Service
public class DocumentacaoService {
	@Autowired
	private DocumentacaoRepository dr;

	 public void cadastrar(Documentacao doc) {
		 dr.save(doc);
	 }
	 
	 public Optional<Documentacao> buscarPorCodigo(Long codigo) {
		 return dr.findById(codigo);
	 }
	 
	 public List<Documentacao>buscarTodos(){
		 return dr.findAll();
	 }
	 
	 public void excluir(Documentacao doc) {
		// rep.deleteById(codigo);
		 dr.delete(doc);
	 }
	 public void excluirPorCodigo(Long codigo) {
		 dr.deleteById(codigo);
	 }
	 
	 public Documentacao buscar(Long codigo) {
		 return dr.getById(codigo);
	 }
	 
	 public void atualizar(Documentacao doc) {
		  dr.saveAndFlush(doc);
	 }
	
}
