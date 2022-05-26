package br.com.souzawebsistemas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.souzawebsistemas.model.*;
import br.com.souzawebsistemas.repositories.PaginacaoRepository;

@Service
public class PaginacaoService {
	@Autowired
private PaginacaoRepository pr;
	public Page<Paginacao>listAll(int currentPage){
		Pageable pageable = PageRequest.of(currentPage - 1,5);
		return pr.findAll(pageable);
	}
	
	public void cadastrar(Paginacao pg) {
		pr.save(pg);
	}
	
	public List<Paginacao>buscarTodos(){
	  return (List<Paginacao>) pr.findAll();
	}
}
