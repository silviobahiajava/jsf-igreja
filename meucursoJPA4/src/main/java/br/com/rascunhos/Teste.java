package br.com.rascunhos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import meucursoJPA.model.secretaria.*;

public class Teste {

	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("meucursoJPA2");
		EntityManager em = emf.createEntityManager();
		Cargo cargo=new Cargo();
		cargo.setDescricao("DIACONO");
		em.getTransaction().begin();
		em.persist(cargo);
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println("cadastro feito com sucesso");

	}

}
