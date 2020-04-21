package Service;

import java.util.ArrayList;

import DAO.PaisDAO;
import model.Pais;


public class PaisService {
	PaisDAO dao = new PaisDAO();
	
	public int criar(Pais pais) {
		return dao.criar(pais);
	}
	
	public void atualizar(Pais pais){
		dao.atualizar(pais);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Pais carregar(int id){
		return dao.carregar(id);
	}

	public Pais maiorPopulacao(int id){
		return dao.maiorPopulacao(id);
	}
	
	public Pais menorArea(int id){
		return dao.menorArea(id);
	}
	
	public ArrayList<Pais> listarTodos() {
		return dao.listarTodos();
		
	}
}
