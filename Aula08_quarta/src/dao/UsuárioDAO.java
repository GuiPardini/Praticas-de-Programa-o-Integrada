package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuário;

public class UsuárioDAO {

	public UsuárioDAO() {
		
	}
	
	public Usuário carregar(int id) {
		Usuário usuário = new Usuário();
		usuário.setId(id);
		String sqlSelect = "SELECT senha FROM user WHERE user.idUser = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, usuário.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuário.setSenha(rs.getString("senha"));
				} else {
					usuário.setId(-1);
					usuário.setSenha(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return usuário;
	}
	
}
