package fr.eni.ventesauxencheres.dal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestConnectionProvider
 */
@WebServlet("/test/TestConnectionProvider")
public class TestConnectionProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String message = "";
		
		// Test Method 1
		pw.append("\nMethode getConnection \n");
		// Test 1 ###################################################
		pw.append("\nTest 1 : Connexion à la base de données\n");
		message = "";
		
		try {
			ConnectionProvider.getConnection_VAE();
			message += "réussi";
		} catch (SQLException e) {
			e.printStackTrace();
			message += "échec :" + e.getMessage();
		}
       
		pw.append(message);
		// Test 2 ###################################################
		pw.append("\nTest 2 : \n");
		message = "";
	
		pw.append(message);
		// Test Method 2
		pw.append("\nMethode \n");
		// Test 3 ###################################################
		pw.append("\nTest 3 : \n");
		message = "";
	
		pw.append(message);
		// Test 4 ###################################################
		pw.append("\nTest 4 : \n");
		message = "";
	
		pw.append(message);
		// Display test results #####################################
		pw.flush();
	}
}


