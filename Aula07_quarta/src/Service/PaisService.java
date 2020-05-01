package Service;
import java.util.ArrayList;
import Model.Pais;
import DAO.PaisDAO;
public class PaisService {
PaisDAO DAO;
public PaisService(){
DAO = new PaisDAO();
}
public ArrayList<Pais> listarPaises(){
return DAO.listarPaises();
}
public ArrayList<Pais> listarPaises(String chave){
return DAO.listarPaises(chave);
}
public int criar(Pais pais) {
	return DAO.criar(pais);
}

public void atualizar(Pais pais){
	DAO.atualizar(pais);
}

public void excluir(int id){
	DAO.excluir(id);
}

public Pais carregar(int id){
	return DAO.carregar(id);
}
}