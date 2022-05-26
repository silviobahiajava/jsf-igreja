package testes.teste_igreja;

import javax.swing.JOptionPane;

public class GeradorDeMensagens {
	public static void mostrarMensagemSucesso(String msg) {
	JOptionPane.showMessageDialog(null, msg);
	}
	
	public static void mostrarMensagemAlerta(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		}
	
	public static void mostrarMensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		}
}
