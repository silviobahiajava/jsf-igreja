package br.com.jsf_igreja.secretaria.repository;

import br.com.jsf_igreja.secretaria.model.*;
import jakarta.persistence.EntityManager;
import testes.teste_igreja.GeradorDeMensagens;
import utils.ConexaoBanco;

public class MembroRepository {

	public static void salvar(Membro membro) {
		EntityManager em=ConexaoBanco.conectar();
		String msgSucesso="cadastro feito com sucesso";
		String msgErro="erro ao tentar cadastrar";
		try {
		em.getTransaction().begin();
		em.persist(membro);
		em.getTransaction().commit();
		GeradorDeMensagens.mostrarMensagemSucesso(msgSucesso);
		}catch(Exception e) {
			GeradorDeMensagens.mostrarMensagemErro(msgErro);
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
}
