package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import criptografia.CryptoMethod;
import model.Login;
import service.PaisesService;

/**
 * Servlet implementation class LoginPaisController
 */
@WebServlet("/loginPaises.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String arquivoLogSuccess = "Users.txt";
	public static final String arquivoLogError = "LogError.txt";
	public static final String path = "C:\\Users\\allan\\git\\ECP6AN-MCA1_PratProgInt\\Aula09\\WebContent\\log\\";
	private PrintWriter arquivo;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String pUsuario = request.getParameter("usuario");
		String pSenha = request.getParameter("senha");

		PaisesService paises = new PaisesService();
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		Login user = new Login();
		user = paises.logar(pUsuario);
		CryptoMethod c = new CryptoMethod();

		
		
		try {
			String criptografia = c.criptografa(pSenha);

			if (criptografia.equals(user.getSenha())) {

				session.setAttribute("user", user);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				abrir(path + arquivoLogSuccess);
				escrever(pUsuario);
			} else {
				abrir(path + arquivoLogError);
				escreverError(pUsuario);
				session.invalidate();
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void abrir(String nome) throws IOException {
		File outFile = new File(nome);
		FileOutputStream outFileStream;
		if (outFile.exists()) {
			outFileStream = new FileOutputStream(outFile, true);
		} else {
			outFileStream = new FileOutputStream(outFile);
		}
		arquivo = new PrintWriter(outFileStream);
	}

	public void escrever(String pUsuario) throws IOException {
		synchronized (arquivo) {
			arquivo.println(
					getDateTime() + "#Success" + " - O usuário: " + ">> " + pUsuario + " <<"+" entrou! " + "\n");
			arquivo.flush();
		}
	}

	public void escreverError(String pUsuario) throws IOException {
		synchronized (arquivo) {
			arquivo.println(getDateTime() + "#Error" + " Usuário: " + ">> " + pUsuario + " <<"
					+ " não encontrado(usuário ou senha invalida)" + "\n");
			arquivo.flush();
		}
	}

}