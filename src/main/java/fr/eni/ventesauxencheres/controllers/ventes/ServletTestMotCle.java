package fr.eni.ventesauxencheres.controllers.ventes;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bll.EnchereManager;
import fr.eni.ventesauxencheres.bo.Enchere;


@WebServlet("/servletTestMotCle")
public class ServletTestMotCle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String motCle = request.getParameter("motCle");
		String libelle = request.getParameter("categorie");
		
		try {
			List<Enchere> encheres = EnchereManager.getInstance().selectByMotCleAndByLibelle(motCle, libelle);
			for (Enchere enchere : encheres) {
				System.out.println(enchere.toString());
			}
			
			request.setAttribute("encheres", encheres);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	
	}

}
