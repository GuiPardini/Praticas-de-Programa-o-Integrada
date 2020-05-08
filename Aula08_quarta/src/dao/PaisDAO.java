package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pais;

public class PaisDAO {
	public int criar(Pais pais) {
		String sqlInsert = "INSERT INTO pais(Nome, Populacao, Area) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
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
		String sqlUpdate = "UPDATE Pais SET Nome=?, Populacao=?, Area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.setInt(4, pais.getId());
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
		String sqlSelect = "SELECT Nome, Populacao, Area FROM pais WHERE pais.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("Nome"));
					pais.setPopulacao(rs.getLong("Populacao"));
					pais.setArea(rs.getDouble("Area"));
				} else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(0);
					pais.setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	
	
	public Pais carregarMax(int id) {
		Pais pais = new Pais();
		String sqlSelect = "select Nome,Populacao,Area from pais where Populacao = (select max(populacao) from pais);";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("Nome"));
					pais.setPopulacao(rs.getLong("Populacao"));
					pais.setArea(rs.getDouble("Area"));
				} else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(0);
					pais.setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	
	public Pais carregarMin(int id) {
		Pais pais = new Pais();
		String sqlSelect = "select Nome,Populacao,Area from pais where Area = (select min(area) from pais);";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("Nome"));
					pais.setPopulacao(rs.getLong("Populacao"));
					pais.setArea(rs.getDouble("Area"));
				} else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(0);
					pais.setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		} 
		return pais;
	}
	
	public ArrayList<Pais> carregar3() {
		ArrayList<Pais> paises = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area FROM pais limit 3";
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
	
	public ArrayList<Pais>listarTodos() {
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

	public ArrayList<Pais> listarTodos(String chave) {
		Pais pais;
		ArrayList<Pais> paises = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area FROM pais where upper(nome) like ?";
						// usando o try with resources do Java 7, quefecha o queabriu
						try (Connection conn = ConnectionFactory.obtemConexao();
								PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
							stm.setString(1, "%" + chave.toUpperCase() + "%");
							try (ResultSet rs = stm.executeQuery();) {
								while (rs.next()) {
									pais = new Pais();
									pais.setId(rs.getInt("id"));
									pais.setNome(rs.getString("nome"));
									pais.setPopulacao(rs.getLong("populacao"));
									pais.setArea(rs.getDouble("area"));
									paises.add(pais);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} catch (SQLException e1) {
							System.out.print(e1.getStackTrace());
						}
		return paises;
	}

}

