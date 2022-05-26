package br.com.souzawebsistemas.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.souzawebsistemas.model.*;

@Repository
@Transactional
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento,Long>{
	default Page<Estabelecimento> findClienteByNameEmailPage(String nomeFantasia, String razaoSocial, Pageable pageable) {
		
		Estabelecimento est = new Estabelecimento();
		est.setNomeFantasia(nomeFantasia);
	   est.setRazaoSocial(razaoSocial);
		
		
		ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
			      .withMatcher("nomeFantasia", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
			      .withMatcher("razaoSocial", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Example<Estabelecimento> example = Example.of(est, ignoringExampleMatcher);
		
		Page<Estabelecimento> estabelecimentos = findAll(example, pageable);
		
		return estabelecimentos;
		
	}
	
	default Page<Estabelecimento> findClienteByNamePage(String nomeFantasia, Pageable pageable) {
		
		Estabelecimento est = new Estabelecimento();
		est.setNomeFantasia(nomeFantasia);
		
		ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
			      .withMatcher("nomeFantasia", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Example<Estabelecimento> example = Example.of(est, ignoringExampleMatcher);
		
		Page<Estabelecimento> estabelecimentos = findAll(example, pageable);
		
		return estabelecimentos;
		
	}
		
	
}
