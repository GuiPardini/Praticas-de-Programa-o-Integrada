package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pais;


public class PaisDAO {
	public int criar(Pais pais) {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais.getId();
	}
	public void atualizar(Pais pais) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(4, pais.getId());
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		public void excluir(int id) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
		stm.setInt(1, id);
		stm.execute();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		public Pais carregar(int id) {
		Pais pais = new Pais();
		pais.setId(id);
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE id = ? ";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
		stm.setInt(1, pais.getId());
		try (ResultSet rs = stm.executeQuery();) {
		if (rs.next()) {
		pais.setNome(rs.getString("nome"));
		pais.setPopulacao(rs.getLong("populacao"));
		pais.setArea(rs.getDouble("area"));
		} else {
		pais.setId(-1);
		pais.setNome(null);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		} catch (SQLException e1) {
		System.out.print(e1.getStackTrace());
		}
		return pais;
		}
		
		public Pais maiorPopulacao(int id) {
			Pais pais = new Pais();
			pais.setId(id);
			String sqlSelect = "select nome from pais p ORDER BY populacao desc";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
			pais.setNome(rs.getString("nome"));
			System.out.println(pais.getNome()+" � o pa�s com a maior popula��o");
			} 
			
			} catch (SQLException e) {
			e.printStackTrace();
			}
			} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			} return pais;
			}
		
		public Pais menorArea(int id) {
			Pais pais = new Pais();
			pais.setId(id);
			String sqlSelect = "select nome from pais p ORDER BY area asc";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
			pais.setNome(rs.getString("nome"));
			System.out.println(pais.getNome()+" � o pa�s com a menor area");
			} 
			
			} catch (SQLException e) {
			e.printStackTrace();
			}
			} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			} return pais;
			}

		public ArrayList<Pais> listarTodos() {
			ArrayList<Pais> paises = new ArrayList<>();
			String sqlSelect = "SELECT id, nome, populacao, area FROM pais";
			Pais pais;

			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlSelect);
					ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						pais = new Pais();
						pais.setId(rs.getInt("id"));
						pais.setNome(rs.getString("nome"));
						pais.setPopulacao(rs.getLong("populacao"));
						pais.setArea(rs.getDouble("area"));
						paises.add(pais);
					} 
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
			return paises;
		}
}
