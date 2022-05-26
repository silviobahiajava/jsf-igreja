package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CadastroJDBc {
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/modeloigreja2";
	private static  final String lOGIN="root";
	private static final String SENHA="123456";
	public CadastroJDBc() {
		
	}
	public static Connection conectarMysql() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			 con=DriverManager.getConnection(URL, lOGIN, SENHA);
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return con;
	}
	
	
	
}
