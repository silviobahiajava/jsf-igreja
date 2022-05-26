package br.com.testejsf.utils;

import java.sql.Connection;

public class GeradorDeTabelas {
 public static Connection conexao;
	public static void main(String[] args) {
		conexao=CadastroJDBc.conectarMysql();
		if(conexao!=null) {
			Utils.mostrarMensagemSwing("tabelas criadas com suceso");
		}else {
			Utils.mostrarMensagemSwing("erro ao gerar as tabelas");
		}

	}

}
