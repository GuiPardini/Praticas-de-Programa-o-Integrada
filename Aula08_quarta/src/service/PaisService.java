package service;

import model.Pais;

import java.util.ArrayList;

import dao.PaisDAO;


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
	
	public Pais carregarMax(int id) {
		return dao.carregarMax(id);
	}
	
	public Pais carregarMin(int id) {
		return dao.carregarMin(id);
	}
	
	public ArrayList<Pais> carregar3() {
		return dao.carregar3();
	}
	
	public ArrayList<Pais> listarTodos() {
		return dao.listarTodos();
	}
	
	public ArrayList<Pais> listarTodos(String chave) {
		return dao.listarTodos(chave);
	}

}
