package service;

import java.util.ArrayList;

import dao.PaisesDao;
import model.Login;
import model.Paises;

public class PaisesService {

	PaisesDao dao = new PaisesDao();

	public int criar(Paises paises) {
		return dao.criar(paises);
	}

	public void atualizar(Paises paises) {
		dao.atualizar(paises);
	}

	public void excluir(int id) {
		dao.excluir(id);
	}

	public Paises carregar(int id) {
		return dao.carregar(id);
	}

	public void menorArea(Paises paises) {
		dao.menorArea(paises);
	}

	public long maiorPopulacao(long populacao) {
		return dao.maiorPopulacao(populacao);
	}

	public Paises[] tresPaises() {
		return dao.tresPaises();

	}

	public ArrayList<Paises> listarPaises() {
		return dao.listarTodos();

	}

	public ArrayList<Paises> listarPaises(String chave) {
		return dao.listarTodos(chave);

	}
	
	public Login logar(String user) {
		return dao.logar(user);
		
	}

}
