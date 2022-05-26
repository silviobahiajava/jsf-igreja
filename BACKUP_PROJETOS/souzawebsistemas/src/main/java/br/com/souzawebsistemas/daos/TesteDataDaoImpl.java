package br.com.souzawebsistemas.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.souzawebsistemas.model.TesteData;
import br.com.souzawebsistemas.utils.PaginacaoUtil;

@Repository
public class TesteDataDaoImpl extends AbstractDao<TesteData, Long> implements TesteDataDao {

	public PaginacaoUtil<TesteData> buscaPaginada(int pagina, String direcao) {
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho; // 0*5=0 1*5=5 2*5=10
		List<TesteData> testes = getEntityManager()
				.createQuery("select c from Cargo c order by c.nome " + direcao, TesteData.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<TesteData>(tamanho, pagina, totalDePaginas, direcao,testes);
	}
	
	public long count() {
		return getEntityManager()
				.createQuery("select count(*) from Cargo", Long.class)
				.getSingleResult();
	}

	
}
