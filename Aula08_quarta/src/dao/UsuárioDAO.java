package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usu�rio;

public class Usu�rioDAO {

	public Usu�rioDAO() {
		
	}
	
	public Usu�rio carregar(int id) {
		Usu�rio usu�rio = new Usu�rio();
		usu�rio.setId(id);
		String sqlSelect = "SELECT senha FROM user WHERE user.idUser = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, usu�rio.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usu�rio.setSenha(rs.getString("senha"));
				} else {
					usu�rio.setId(-1);
					usu�rio.setSenha(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return usu�rio;
	}
	
}
