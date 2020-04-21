package Service;

import model.Pais;
import DAO.PaisDAO;


public class PaisService {
	PaisDAO dao = new PaisDAO();
	
	public void criar(Pais p) {
		dao.criar(p);
	}
	
	public void atualizar(Pais p){
		dao.atualizar(p);
	}
	
	public void excluir(Pais p){
		dao.excluir(p);
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
}
