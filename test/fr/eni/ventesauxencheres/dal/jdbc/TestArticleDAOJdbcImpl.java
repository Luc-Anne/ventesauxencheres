package fr.eni.ventesauxencheres.dal.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;

@WebServlet("/test/TestArticleDAOJdbcImpl")
public class TestArticleDAOJdbcImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String message = "";
		
		// Test Method 1
		pw.append("\nMethode SELECT ALL Articles \n");
		// Test 1 ###################################################
		pw.append("\nTest 1 : \n");
		message = "";
		//Sélection de tous les vendeurs		
		try {
			List<Article> articlesList = FactoryDAO.getArticleDAO().selectAll();
			message+="Test DAL : requête OK";
			for(Article art : articlesList) {
				System.out.println(art);					
			}			
		} catch (DALException e) {
			e.printStackTrace();
			message+="Test DAL : requête Echec";
		}
		System.out.println("Fin du test" );

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
