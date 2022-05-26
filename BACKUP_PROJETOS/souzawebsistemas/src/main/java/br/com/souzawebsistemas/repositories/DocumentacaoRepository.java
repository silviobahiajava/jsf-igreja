package br.com.souzawebsistemas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.souzawebsistemas.model.Documentacao;
import br.com.souzawebsistemas.model.Membro;

public interface DocumentacaoRepository extends JpaRepository<Documentacao,Long>{
	Iterable<Documentacao> findByMembro(Membro membro);
	Documentacao findByRg(String rg);
}
