package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Paises;
import service.PaisesService;

/**
 * Servlet implementation class ManterClienteController
 */
@WebServlet("/ManterPaises.do")
public class ManterPaisesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pAcao = request.getParameter("acao");
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pPopulacao =request.getParameter("populacao");
		String pArea = request.getParameter("area");
		
		int id = -1;
		long populacao = -1;
		double area = -1;
		
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {
		}
		
		
		try {
			populacao = Long.parseLong(pPopulacao);
            area = Double.parseDouble(pArea);
		}catch (NumberFormatException e) {
			
		}

		Paises paises = new Paises();		
		paises.setId(id);
		paises.setNome(pNome);
	    paises.setPopulacao(populacao);
		paises.setArea(area);

		PaisesService cs = new PaisesService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		if (pAcao.equals("Criar")) {
			
			cs.criar(paises);
			ArrayList<Paises> lista = new ArrayList<>();
			lista.add(paises);
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPaises.jsp");
			
		} else if (pAcao.equals("Excluir")) {
			
			cs.excluir(paises.getId());
			ArrayList<Paises> lista = (ArrayList<Paises>) session.getAttribute("lista");
			lista.remove(busca(paises, lista));
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPaises.jsp");
			
		} else if (pAcao.equals("Alterar")) {
			
			cs.atualizar(paises);
			ArrayList<Paises> lista = (ArrayList<Paises>) session.getAttribute("lista");
			int pos = busca(paises, lista);
			lista.remove(pos);
			lista.add(pos, paises);
			session.setAttribute("lista", lista);
			request.setAttribute("paises", paises);
			view = request.getRequestDispatcher("VisualizarPaises.jsp");
			
		} else if (pAcao.equals("Visualizar")) {
			
			paises = cs.carregar(paises.getId());	
			request.setAttribute("paises", paises);
			view = request.getRequestDispatcher("VisualizarPaises.jsp");
			
		} else if (pAcao.equals("Editar")) {
				
			paises = cs.carregar(paises.getId());
			request.setAttribute("paises", paises);
			view = request.getRequestDispatcher("AlterarPaises.jsp");
		}

		view.forward(request, response);

	}

	public int busca(Paises paises, ArrayList<Paises> lista) {
		Paises to;
		for (int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if (to.getId() == paises.getId()) {
				return i;
			}
		}
		return -1;
	}

}