package br.com.souzawebsistemas.repositories;



//import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.souzawebsistemas.model.TesteData;
import br.com.souzawebsistemas.model.TestePaginacao;

@Repository
@Transactional
public interface TestePaginacaoRepository extends PagingAndSortingRepository<TestePaginacao,Long>{
	/*@Query("SELECT td FROM TesteData td WHERE ORDER BY td.nome ASC").
	List<TesteData> findAllActiveUsers();
	public static PaginacaoUtil<TesteData>buscaPaginada(){
	 return null;
	}*/
	
	
	
}
