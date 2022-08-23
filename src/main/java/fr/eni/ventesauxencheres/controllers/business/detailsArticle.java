package fr.eni.ventesauxencheres.controllers.business;

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
import fr.eni.ventesauxencheres.bll.EnchereManager;
import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Enchere;
import fr.eni.ventesauxencheres.bo.Utilisateur;

/**
 * Servlet implementation class Template
 */
@WebServlet("/encheres/article")
public class detailsArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération des paramètres
		int no_article = 0;
		try {
			no_article = Integer.parseInt(request.getParameter("no_article"));
		} catch (NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			response.sendError(500);
		}
		
		// Récupération et traitement des données
		Article article = null;
		try {
			article = ArticleManager.getInstance().get(no_article);
		} catch (BLLException e) {
			e.printStackTrace();
			response.sendError(404);
		}
		if (article.getEtatVente() == "CR") {
			response.sendError(404);
		} else {
			List<Enchere> encheres = null;
			Utilisateur acheteur = null;
			try {
				encheres = EnchereManager.getInstance().getByObject(article);
				if (encheres != null) {
					if (article.getEtatVente() == "VD" || article.getEtatVente() == "RT") {
						acheteur = encheres.get(0).getEncherisseur();
					}
				}
			} catch (BLLException e) {
				e.printStackTrace();
				response.sendError(404);
			}
			
			if (article.getEtatVente() == "VD" || article.getEtatVente() == "RT") {
				if(article.getVendeur() != (Utilisateur)request.getSession().getAttribute("UtilisateurConnecte")) {
					// Fonction equals ?
					response.sendError(403);
				}
				if (acheteur != null && acheteur != (Utilisateur)request.getSession().getAttribute("UtilisateurConnecte")) {
					response.sendError(403);
				}
			}
			
			// Affichage de la vue
			request.setAttribute("article", article);
			request.setAttribute("encheres", encheres);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/business/detailsArticle.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
