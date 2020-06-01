package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionFactory {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Conected!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection obtemConexao() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/aulasusjt?useTimezone=true&serverTimezone=America/Sao_Paulo&user=root&password=root&useSSL=false");
	}
}
