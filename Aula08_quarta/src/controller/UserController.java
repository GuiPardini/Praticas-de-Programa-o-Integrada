package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuárioDAO;

@WebServlet("/Login.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }

    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//Instancias
		String acao = request.getParameter("acao");
		String pSenha = request.getParameter("senha");
		String pUser = request.getParameter("login");
		Calendar data;
		data = Calendar.getInstance();
        int horas, minutos,segundos;
		UsuárioDAO dao  = new UsuárioDAO();
		
		//Arquivo
		String caminho = "C:\\Users\\gmpar_000\\Desktop\\log\\Logs.txt";
		String linha="",conteudo="";
		
		HttpSession session = request.getSession();
		RequestDispatcher view = null;
		String newsenha = ""+dao.carregar(1)+"";

	
		if (acao.equals("Entrar")) {
			if (pSenha.equals(newsenha)){
				
		        horas = data.get(Calendar.HOUR_OF_DAY);
		        minutos = data.get(Calendar.MINUTE);
		        segundos = data.get(Calendar.SECOND);
		        String Total = "Login Realizado pelo usuário: "+pUser+" Horário de Acesso: "+horas+":"+minutos+":"+segundos+"";
		        
		        FileReader arq = new FileReader(caminho);
			    BufferedReader lerArq = new BufferedReader(arq);
		        
			    linha = lerArq.readLine();
			    while(linha!=null) {
			    	conteudo += linha+"\n";
			    	linha = lerArq.readLine();
			    }
			    arq.close();
			    
			    FileWriter arq2 = new FileWriter(caminho);
			    PrintWriter gravarArq = new PrintWriter(arq2);
			    gravarArq.println(conteudo+"\n"+Total);
			    gravarArq.close();
			    
				RequestDispatcher dispatcher = request.getRequestDispatcher("PaginaInicial.jsp");
				dispatcher.forward(request, response);
				
			} else {
				 request.setAttribute("mensagem","Usuário ou Senha Inválido!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
		    	}
			}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
