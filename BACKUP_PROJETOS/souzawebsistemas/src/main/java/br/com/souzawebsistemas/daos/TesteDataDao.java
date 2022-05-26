package br.com.souzawebsistemas.daos;

import java.util.List;

import br.com.souzawebsistemas.model.TesteData;
import br.com.souzawebsistemas.utils.*;

public interface TesteDataDao {

    void save(TesteData td );

    void update(TesteData td);

    void delete(Long id);

    TesteData findById(Long id);

    List<TesteData> findAll();
    
    PaginacaoUtil<TesteData> buscaPaginada(int pagina, String direcao);
}
