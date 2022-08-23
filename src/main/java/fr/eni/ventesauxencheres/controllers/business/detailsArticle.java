package fr.eni.ventesauxencheres.controllers.business;

import java.io.IOException;

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
		// Bloqué la page à un utilisateur non connecté
		if (request.getSession().getAttribute("utilisateurConnecte") == null) {
			response.sendError(403);
			return;
		}
		Utilisateur utilisateurConnecte = (Utilisateur)request.getSession().getAttribute("utilisateurConnecte");
		
		// Récupération des paramètres
		int no_article = 0;
		try {
			no_article = Integer.parseInt(request.getParameter("no_article"));
		} catch (NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			response.sendError(500);
			return;
		}
		
		// Récupération et traitement des données
		Article article = null;
		try {
			article = ArticleManager.getInstance().get(no_article);
		} catch (BLLException e) {
			e.printStackTrace();
			response.sendError(404);
			return;
		}
		
		Enchere enchere = null;
		Utilisateur encherisseur = null;
		try {
			enchere = EnchereManager.getInstance().getByArticle(article);
			if (enchere != null) {
				if (article.getEtatVente() == "VD" || article.getEtatVente() == "RT") {
					encherisseur = enchere.getEncherisseur();
				}
			}
		} catch (BLLException e) {
			e.printStackTrace();
			response.sendError(404);
			return;
		}
		
		// Bloqué l'accès à la page suivant l'état de la vente et la relation entre l'article et l'utilisateur connecté
		String typeAffichage = "";
		Utilisateur vendeur = article.getVendeur();
		if (
			article.getEtatVente() == "CR" ||
			(article.getEtatVente() == "VD" && !utilisateurConnecte.equals(encherisseur)) ||
			(article.getEtatVente() == "RT" && !utilisateurConnecte.equals(encherisseur))
		) {
			response.sendError(404);
			return;
		} else if (
			(article.getEtatVente() == "EC" && !utilisateurConnecte.equals(vendeur))
		){
			typeAffichage = "typeEncherir";
		} else if (
			(article.getEtatVente() == "VD" && utilisateurConnecte.equals(vendeur))
		){
			typeAffichage = "typeRetrait";
		} else if (
			(article.getEtatVente() == "EC" && utilisateurConnecte.equals(vendeur)) ||
			(article.getEtatVente() == "VD" && utilisateurConnecte.equals(encherisseur)) ||
			(article.getEtatVente() == "RT" && utilisateurConnecte.equals(vendeur)) ||
			(article.getEtatVente() == "RT" && utilisateurConnecte.equals(encherisseur))
		){
			typeAffichage = "typeBack";
		}

		// Affichage de la vue
		request.setAttribute("article", article);
		request.setAttribute("enchere", enchere);
		request.setAttribute("typeAffichage", typeAffichage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/business/detailsArticle.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
