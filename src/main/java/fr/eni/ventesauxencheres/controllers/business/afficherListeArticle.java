package fr.eni.ventesauxencheres.controllers.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ventesauxencheres.bll.ArticleManager;
import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bo.Article;

/**
 * Servlet implementation class afficherListeArticle
 */
@WebServlet("/afficherListeArticle")
public class afficherListeArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//S'appuyer sur l'articleManager pour récupérer les informations
		try {
			List<Article> articleList = ArticleManager.getInstance().getAll();
			//Mettre la liste d'article dans la session
			HttpSession session = request.getSession();
			session.setAttribute("articleList",articleList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/business/affichageListeArticles.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
