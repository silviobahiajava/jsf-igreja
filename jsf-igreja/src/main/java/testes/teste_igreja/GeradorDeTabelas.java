package testes.teste_igreja;

import javax.swing.JOptionPane;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GeradorDeTabelas {

	public static void gerarTabelas() {
		try {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jsf-igreja");
		JOptionPane.showMessageDialog(null, "tabelas geradas com sucesso");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "erro ao gerar as tabelas");
		}
	}
}
