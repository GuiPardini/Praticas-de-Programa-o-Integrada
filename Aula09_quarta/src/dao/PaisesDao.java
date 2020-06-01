package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Login;

//import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;

import model.Paises;

public class PaisesDao {
	/****************************** Metodo C R U D ***************************/

	/****************************** Metodo CREATE ***************************/

	public int criar(Paises paises) {
		String sqlInsert = "INSERT INTO aulasusjt.paises(nome, populacao, area) VALUES(?,?,?)";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, paises.getNome());
			stm.setLong(2, paises.getPopulacao());
			stm.setDouble(3, paises.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()"; // What is it?

			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); // What is it?
					ResultSet rs = stm2.executeQuery();) { // What is it?
				if (rs.next()) {
					paises.setId(rs.getInt(1));
				}
			} catch (SQLException e) { // What is it?
				e.printStackTrace(); // What is it?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paises.getId();
	}

	/****************************** Metodo READ ***************************/
	public Paises carregar(int id) {
		String sqlSelect = "SELECT nome, populacao, area From aulasusjt.paises WHERE paises.id = ?";
		Paises paises = new Paises();
		paises.setId(id);
		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, paises.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					paises.setNome(rs.getString("nome"));
					paises.setPopulacao(rs.getLong("populacao"));
					paises.setArea(rs.getDouble("area"));
				} else {
					paises.setId(0);
					paises.setNome(null);
					paises.setPopulacao(0);
					paises.setArea(0);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return paises;
	}

	/****************************** Metodo UPDATE ***************************/

	public void atualizar(Paises paises) {
		String sqlUpdate = "UPDATE aulasusjt.paises SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, paises.getNome());
			stm.setLong(2, paises.getPopulacao());
			stm.setDouble(3, paises.getArea());
			stm.setInt(4, paises.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/****************************** Metodo DELETE ***************************/

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM aulasusjt.paises WHERE id=?";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**************************** Metodo para a MENOR AREA ******************/

	public void menorArea(Paises paises) {
		String sqlMenor = "SELECT  nome, area FROM aulasusjt.paises WHERE area = (select MIN(area) FROM paises)LIMIT 1";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlMenor);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					paises.setNome(rs.getString("nome"));
					paises.setArea(rs.getDouble("area"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
	}

	public long maiorPopulacao(Long l) {
		String sqlMaior = "SELECT  nome, populacao FROM aulasusjt.paises WHERE populacao = (select Max(populacao) FROM paises)";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlMaior);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					rs.getString("nome");
					l = rs.getLong("populacao");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public Paises[] tresPaises() {

		Paises paises = null;
		Paises[] vetor = new Paises[3];
		int i = 0;

		String sqltres = "SELECT * FROM aulasusjt.paises LIMIT 3";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqltres);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String nome = rs.getString("nome");
					Long populacao = rs.getLong("populacao");
					Double area = rs.getDouble("area");
					paises = new Paises(id, nome, populacao, area);
					vetor[i++] = paises;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return vetor;
	}

	public ArrayList<Paises> listarTodos() {
		ArrayList<Paises> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area From aulasusjt.paises";
		Paises paises;

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
			while (rs.next()) {
				paises = new Paises();
				paises.setId(rs.getInt("id"));
				paises.setNome(rs.getString("nome"));
				paises.setPopulacao(rs.getLong("populacao"));
				paises.setArea(rs.getDouble("area"));
				lista.add(paises);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}

	public ArrayList<Paises> listarTodos(String chave) {
		Paises paises;
		ArrayList<Paises> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area From aulasusjt.paises where upper(nome) like ?";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					paises = new Paises();
					paises.setId(rs.getInt("id"));
					paises.setNome(rs.getString("nome"));
					paises.setPopulacao(rs.getLong("populacao"));
					paises.setArea(rs.getDouble("area"));
					lista.add(paises);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	/*Carregar Usuário para login*/
	
	public Login logar(String user) {
		String sqlSelect = "SELECT * FROM aulasusjt.usuarios where nome = ?";
		Login login = new Login();
		login.setLogin(user);
		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, login.getLogin());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					login.setId(rs.getInt("id"));
					login.setNome(rs.getString("nome"));
					login.setSenha(rs.getString("senha"));
				} else {
					login.setId(0);
					login.setLogin(null);
					login.setNome(null);
					login.setSenha(null);
					
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return login;
	}
}
