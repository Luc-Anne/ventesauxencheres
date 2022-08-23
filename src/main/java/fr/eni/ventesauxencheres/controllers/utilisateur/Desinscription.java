package fr.eni.ventesauxencheres.controllers.utilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bll.UtilisateurManager;
import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.controllers.Url;

@WebServlet("/moncompte/desinscription")
public class Desinscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Utilisateur uc = (Utilisateur) session.getAttribute("utilisateurConnecte");
			UtilisateurManager.getInstance().delete(uc.getNoUtilisateur());
			session.removeAttribute("utilisateurConnecte");
			request.getSession().setAttribute("messageGlobal", "Vous avez bien été désinscrit");
			response.sendRedirect(Url.HOME.getUrl());
		} catch (BLLException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/moncompte/profil");
			if (rd != null) {
				rd.forward(request, response);
			}
		}
	}

}
