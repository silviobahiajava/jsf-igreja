package br.com.souzawebsistemas.repositories;

/*import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.souzawebsistemas.model.EntradaProduto;

@Repository
@Transactional
public interface EntradaProdutoRepository extends JpaRepository<EntradaProduto,Long>{
	/*default Page<Cliente> findClienteByNameEmailPage(String nomeCliente, String emailCliente, Pageable pageable) {
		
		Cliente cliente = new Cliente();
		cliente.setNomeCliente(nomeCliente);
		cliente.setEmailCliente(emailCliente);
		
		ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
			      .withMatcher("nomeCliente", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
			      .withMatcher("emailCliente", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Example<Cliente> example = Example.of(cliente, ignoringExampleMatcher);
		
		Page<Cliente> clientes = findAll(example, pageable);
		
		return clientes;
		
	}
	
	default Page<Cliente> findClienteByNamePage(String nomeCliente, Pageable pageable) {
		
		Cliente cliente = new Cliente();
		cliente.setNomeCliente(nomeCliente);
		
		ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
			      .withMatcher("nomeCliente", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Example<Cliente> example = Example.of(cliente, ignoringExampleMatcher);
		
		Page<Cliente> clientes = findAll(example, pageable);
		
		return clientes;
		
	}
		*/
	
}
