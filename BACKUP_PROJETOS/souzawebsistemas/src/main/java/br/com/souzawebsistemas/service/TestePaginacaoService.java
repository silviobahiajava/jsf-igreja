package br.com.souzawebsistemas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.souzawebsistemas.model.TestePaginacao;
import br.com.souzawebsistemas.repositories.TestePaginacaoRepository;

@Service
public class TestePaginacaoService {
	@Autowired
  private TestePaginacaoRepository rep;
	
	 public void cadastrar(TestePaginacao cliente) {
		 rep.save(cliente);
	 }
	 
	 public Optional<TestePaginacao> buscarPorCodigo(Long codigo) {
		 return rep.findById(codigo);
	 }
	 
	
	 public void excluir(TestePaginacao cliente) {
		// rep.deleteById(codigo);
		 rep.delete(cliente);
	 }
	 public void excluirPorCodigo(Long codigo) {
		 rep.deleteById(codigo);
	 }
	 
	 public Optional<TestePaginacao> buscar(Long codigo) {
		 return rep.findById(codigo);
	 }
	 
	 public void atualizar(TestePaginacao tp) {
		  rep.save(tp);
	 }
	 public Page<TestePaginacao> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
			Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();
			
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
			return this.rep.findAll(pageable);
		}

}
