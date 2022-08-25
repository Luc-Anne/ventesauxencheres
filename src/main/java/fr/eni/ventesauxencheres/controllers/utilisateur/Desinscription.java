package fr.eni.ventesauxencheres.controllers.utilisateur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ventesauxencheres.bll.ArticleManager;
import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bll.UtilisateurManager;
import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.controllers.Url;

@WebServlet("/moncompte/desinscription")
public class Desinscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			HttpSession session = request.getSession();
			Utilisateur uc = (Utilisateur) session.getAttribute("utilisateurConnecte");
			if (uc == null) {
				response.sendError(403); return;
			} else {
				if (request.getParameter("confirmationSuppression") == null) {
					session.setAttribute("messageGlobal", "Vous n'avez pas confirmé la suppresion de votre compte");
					response.sendRedirect(Url.COMPTE_PROFIL.getUrl()); return;
				} else {
					try {
						if (ArticleManager.getInstance().hasArticle(uc.getNoUtilisateur())) {
							session.setAttribute("messageGlobal", "Vous ne pouvez pas supprimer votre compte car vous avez des articles sur le site");
							response.sendRedirect(Url.COMPTE_PROFIL.getUrl()); return;
						} else {
							UtilisateurManager.getInstance().delete(uc.getNoUtilisateur());
							session.removeAttribute("utilisateurConnecte");
							request.getSession().setAttribute("messageGlobal", "Votre compte a bien été supprimé");
							response.sendRedirect(Url.HOME.getUrl());
						}
					} catch (BLLException e) {
						response.sendError(500); return;
					}
				}
			}

	}

}
