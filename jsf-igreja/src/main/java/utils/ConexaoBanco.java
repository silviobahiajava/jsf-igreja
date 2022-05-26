package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConexaoBanco {

	 public static  EntityManager conectar() {
		   EntityManagerFactory emf = Persistence
	                .createEntityManagerFactory("jsf-igreja");
	        EntityManager em = emf.createEntityManager();
	     //   JOptionPane.showMessageDialog(null,"tabelas criadas com sucesso");
	        return em;
	 }
}
