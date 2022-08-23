package fr.eni.ventesauxencheres.controllers.navigation;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bll.ArticleManager;
import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bo.Article;


@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer un attribut à travers un sendRedirect
		request.setAttribute("messageGlobal", request.getSession().getAttribute("messageGlobal"));
		request.getSession().removeAttribute("messageGlobal");
		
		List<Article> articleList;
		try {
			articleList = ArticleManager.getInstance().getAll();
			//Mettre la liste d'article dans la requete car besoin uniquement dans la home
			request.setAttribute("articleList",articleList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On arrive ici par exemple depuis connexion pour afficher la page d'accueil
		// Du coup, on doit seulement afficher la page dans ce cas de figure
		// On ne peut donc faire aucun traitement ici autre que appeler le doGet
		this.doGet(request, response);
	}

}
