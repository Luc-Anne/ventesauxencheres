package fr.eni.ventesauxencheres.dal.jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Enchere;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;

@WebServlet("/test/TestEnchereDAOJdbcImpl")
public class TestEnchereDAOJdbcImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String message = "";
		
		// Test Method 1
		pw.append("\nMethode selectByArticle \n");
		// Test 1 ###################################################
		pw.append("\nTest 1 : \n");
		message = "";
		Article article;
		try {
			article = FactoryDAO.getArticleDAO().selectById(1);
			Enchere enchere = FactoryDAO.getEnchereDAO().selectByArticle(article);
			if (enchere != null) {
				message += "OK";
			} else {
				message += "ECHEC retour nul";
			}
		} catch (DALException e) {
			message += "ECHEC";
			e.printStackTrace();
		}

		pw.append(message);
		// Test 2 ###################################################
		pw.append("\nTest 2 : remplacerEnchere \n");
		message = "";
		/*
		Enchere enchere;
		try {
			
			enchere = FactoryDAO.getEnchereDAO().selectById(4);
			FactoryDAO.getEnchereDAO().remplacerEnchere(enchere);
			
		} catch (DALException e) {
			message += "ERREUR";
			e.printStackTrace();
		}
		*/	
		message = "Test incomplet";
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
