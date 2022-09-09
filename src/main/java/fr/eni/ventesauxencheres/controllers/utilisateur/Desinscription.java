package fr.eni.ventesauxencheres.controllers.utilisateur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ventesauxencheres.bll.encheres.ArticleMgr;
import fr.eni.ventesauxencheres.bll.utilisateur.ClientMgr;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.controllers.util.Url;
import fr.eni.ventesauxencheres.exceptions.BLLException;

@WebServlet("/moncompte/desinscription")
public class Desinscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Client uc = (Client) session.getAttribute("utilisateurConnecte");
		if (uc == null) {
			response.sendError(403); return;
		} else {
			if (request.getParameter("confirmationSuppression") == null) {
				session.setAttribute("messageGlobal", "Vous n'avez pas confirmé la suppresion de votre compte");
				response.sendRedirect(Url.COMPTE_PROFIL.getUrl()); return;
			} else {
				try {
					if (ArticleMgr.getInstance().hasArticle(uc.getNoClient())) {
						session.setAttribute("messageGlobal", "Vous ne pouvez pas supprimer votre compte car vous avez des articles sur le site");
						response.sendRedirect(Url.COMPTE_PROFIL.getUrl()); return;
					} else {
						ClientMgr.getInstance().delete(uc.getNoClient());
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
