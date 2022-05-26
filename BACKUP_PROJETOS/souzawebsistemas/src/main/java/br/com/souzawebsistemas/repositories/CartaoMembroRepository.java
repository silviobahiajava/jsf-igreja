package br.com.souzawebsistemas.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.souzawebsistemas.model.*;

public interface CartaoMembroRepository extends JpaRepository<Membro,Long>{
	default Page<Membro> findMembroByNameCodigoPage(String nome,Long codigo, Pageable pageable) {
	Membro membro = new Membro();
	membro.setNome(nome);
	membro.setCodigo(codigo);
	
	
	ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
		      .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
		      .withMatcher("codigo", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
	
	Example<Membro> example = Example.of(membro, ignoringExampleMatcher);
	
	Page<Membro> membros = findAll(example, pageable);
	
	return membros;
	
}

default Page<Membro> findMembroByNamePage(String nome, Pageable pageable) {
	
	Membro membro = new Membro();
	membro.setNome(nome);
	
	
	ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
		      .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
	
	Example<Membro> example = Example.of(membro, ignoringExampleMatcher);
	
	Page<Membro> membros = findAll(example, pageable);
	
	return membros;
	
}

}
