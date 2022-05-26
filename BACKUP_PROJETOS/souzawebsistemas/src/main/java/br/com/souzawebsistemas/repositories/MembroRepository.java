package br.com.souzawebsistemas.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.souzawebsistemas.model.Membro;

public interface MembroRepository extends JpaRepository<Membro,Long>{
	Membro findByCodigo(long codigo);
	
	
	
	default Page<Membro> findMembroByNameCodigoPage(String nome,String naturalidade, Pageable pageable) {
	Membro membro = new Membro();
	membro.setNome(nome);
	membro.setNatualidade(naturalidade);
	
	
	ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
		      .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
		      .withMatcher("naturalidade", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
	
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
