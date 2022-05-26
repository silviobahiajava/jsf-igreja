package testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import meucursoJPA.model.secretaria.Cargo;

public class TesteCadastro {

	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("meucursoJPA");
		EntityManager em = emf.createEntityManager();
      
		Cargo cargo=new Cargo();
		cargo.setDescricao("PASTOR");
		try {
			em.getTransaction().begin();
			em.persist(cargo);
			em.getTransaction().commit();
			System.out.println("cadastro feito com sucesso");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro ao cadastrar "+e.getMessage());
		}
		finally {
			em.close();
			emf.close();
		}

	}

}
