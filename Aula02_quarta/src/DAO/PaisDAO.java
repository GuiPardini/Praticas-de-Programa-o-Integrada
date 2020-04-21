package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Pais;


public class PaisDAO {
	public void criar(Pais p) {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, p.getNome());
			stm.setLong(2, p.getPopulacao());
			stm.setDouble(3, p.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					p.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void atualizar(Pais p) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(4, p.getId());
			stm.setString(1, p.getNome());
			stm.setLong(2, p.getPopulacao());
			stm.setDouble(3, p.getArea());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		public void excluir(Pais p) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
		stm.setInt(1, p.getId());
		stm.execute();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		public Pais carregar(int id) {
		Pais p = new Pais();
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE id = ? ";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
		stm.setInt(1, p.getId());
		try (ResultSet rs = stm.executeQuery();) {
		if (rs.next()) {
		p.setNome(rs.getString("nome"));
		p.setPopulacao(rs.getLong("populacao"));
		p.setArea(rs.getDouble("area"));
		System.out.println("Nome: " +p.getNome()+" População: "+p.getPopulacao()+" Area: "+p.getArea());
		} else {
		p.setId(-1);
		p.setNome(null);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		} catch (SQLException e1) {
		System.out.print(e1.getStackTrace());
		}
		return p;
		}
		
		public Pais maiorPopulacao(int id) {
			Pais p = new Pais();
			String sqlSelect = "select nome from pais p ORDER BY populacao desc";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
			p.setNome(rs.getString("nome"));
			System.out.println(p.getNome()+" é o país com a maior população");
			} 
			
			} catch (SQLException e) {
			e.printStackTrace();
			}
			} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			} return p;
			}
		
		public Pais menorArea(int id) {
			Pais p = new Pais();
			String sqlSelect = "select nome from pais p ORDER BY area asc";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
			p.setNome(rs.getString("nome"));
			System.out.println(p.getNome()+" é o país com a menor area");
			} 
			
			} catch (SQLException e) {
			e.printStackTrace();
			}
			} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			} return p;
			}

}
