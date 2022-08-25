package fr.eni.ventesauxencheres.controllers.business;

import java.io.IOException;
import java.time.LocalDateTime;

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
import fr.eni.ventesauxencheres.controllers.Url;

/**
 * Servlet implementation class Template
 */
@WebServlet("/encheres/article")
public class detailsArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///////////////////////////////////////////////////////////////////////
		// IDEM                                                              //
		///////////////////////////////////////////////////////////////////////
		// Bloquer la page à un utilisateur non connecté
		if (request.getSession().getAttribute("utilisateurConnecte") == null) {
			response.sendError(403); return;
		}
		Utilisateur utilisateurConnecte = (Utilisateur)request.getSession().getAttribute("utilisateurConnecte");
		
		// Récupération des paramètres
		int no_article = 0;
		try {
			no_article = Integer.parseInt(request.getParameter("no_article"));
		} catch (NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			response.sendError(500); return;
		}
		
		// Récupération et traitement des données
		Article article = null;
		try {
			article = ArticleManager.getInstance().get(no_article);
		} catch (BLLException e) {
			e.printStackTrace();
			response.sendError(404); return;
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
			response.sendError(404); return;
		}
		
		Utilisateur vendeur = article.getVendeur();
		
		///////////////////////////////////////////////////////////////////////
		
		// Bloqué l'accès à la page suivant l'état de la vente et la relation entre l'article et l'utilisateur connecté
		String typeAffichage = "";
		if (
			(article.getEtatVente().equals("VD") && utilisateurConnecte.equals(vendeur) && encherisseur == null)
			){
			typeAffichage = "typePerdu";
		} else if (
			(article.getEtatVente().equals("VD") && utilisateurConnecte.equals(vendeur))
		){
			typeAffichage = "typeRetrait";
		} else if (
			(article.getEtatVente().equals("VD") && utilisateurConnecte.equals(encherisseur))
		){
			typeAffichage = "typeGagne";
		} else if (
			(article.getEtatVente().equals("EC") && utilisateurConnecte.equals(vendeur)) ||
			(article.getEtatVente().equals("RT") && utilisateurConnecte.equals(vendeur)) ||
			(article.getEtatVente().equals("RT") && utilisateurConnecte.equals(encherisseur))
		){
			typeAffichage = "typeBack";
		}else if (
			article.getEtatVente().equals("CR") ||
			(article.getEtatVente().equals("VD") && !utilisateurConnecte.equals(encherisseur)) ||
			(article.getEtatVente().equals("RT") && !utilisateurConnecte.equals(encherisseur))
		) {
			response.sendError(403);
			return;
		} else if (
			(article.getEtatVente().equals("EC") && !utilisateurConnecte.equals(vendeur))
		){
			typeAffichage = "typeEncherir";
		}  else {
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
		///////////////////////////////////////////////////////////////////////
		// IDEM                                                              //
		///////////////////////////////////////////////////////////////////////
		// Bloquer la page à un utilisateur non connecté
		if (request.getSession().getAttribute("utilisateurConnecte") == null) {
			response.sendError(403); return;
		}
		Utilisateur utilisateurConnecte = (Utilisateur)request.getSession().getAttribute("utilisateurConnecte");
		
		// Récupération des paramètres
		int no_article = 0;
		try {
			no_article = Integer.parseInt(request.getParameter("no_article"));
		} catch (NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			response.sendError(500); return;
		}

		// Récupération et traitement des données
		Article article = null;
		try {
			article = ArticleManager.getInstance().get(no_article);
		} catch (BLLException e) {
			e.printStackTrace();
			response.sendError(404); return;
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
			response.sendError(404); return;
		}
		
		Utilisateur vendeur = article.getVendeur();

		///////////////////////////////////////////////////////////////////////
		if (request.getParameter("action") != null && "encherir".equals(request.getParameter("action")) &&
			(article.getEtatVente().equals("EC") && !utilisateurConnecte.equals(vendeur))
		){
			// typeEncherir
			int mise = 0;
			try {
				mise = Integer.parseInt(request.getParameter("mise"));
			} catch (NumberFormatException e) {
				response.sendError(500); return;
			}
			
			if (utilisateurConnecte.getCredit() < mise) {
				request.getSession().setAttribute("messageGlobal", "Vous n'avez pas assez de crédit pour faire cette enchère");
				this.doGet(request, response); return;
			}
			
			try {
				if(utilisateurConnecte.getCredit() < mise) {
					request.getSession().setAttribute("messageGlobal", "Vous ne disposez pas des crédits suffisants pour enchérir de " + mise + " crédits");
					this.doGet(request, response);
				}
				if (enchere == null) {
					// Ajouter l'enchère
					EnchereManager.getInstance().save(new Enchere(
							LocalDateTime.now(),
							mise,
							utilisateurConnecte,
							article
						)
					);
				} else {
					// Remplacer l'enchère
					EnchereManager.getInstance().remplacerEncherisseur(new Enchere(
							LocalDateTime.now(),
							mise,
							utilisateurConnecte,
							article
						)
					);
				}
				request.getSession().setAttribute("messageGlobal", "Votre enchère de " + mise + " a été prise en compte pour l'article " + article.getNomArticle());
				this.doGet(request, response);
			} catch(BLLException e) {
				e.printStackTrace();
				response.sendError(500); return;
			}
			
		} else if (request.getParameter("action") != null && "retrait".equals(request.getParameter("action")) &&
			(article.getEtatVente().equals("VD") && utilisateurConnecte.equals(vendeur))
		){
			// typeRetrait
			try {
				article.setEtatVente("RT");
				ArticleManager.getInstance().modify(article);
				request.getSession().setAttribute("messageGlobal", "Votre déclaration de retrait de l'article " + article.getNomArticle() + " a bien été prise en compte");
				this.doGet(request, response);
			} catch (BLLException e) {
				e.printStackTrace();
				response.sendError(500); return;
			}
		} else if (request.getParameter("action") != null && "back".equals(request.getParameter("action"))) {
			// Passer un attribut à travers un sendRedirect
			response.sendRedirect(Url.HOME.getUrl()); return;
		} else {
			this.doGet(request, response);
		}

	}

}
