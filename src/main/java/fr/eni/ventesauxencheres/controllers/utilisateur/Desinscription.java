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

@WebServlet("/moncompte/desinscription")
public class Desinscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Utilisateur uc = (Utilisateur) session.getAttribute("utilisateurConnecte");
			UtilisateurManager.getInstance().delete(uc.getNoUtilisateur());
			session.invalidate();
			// TODO Prévoir un message qui s'affichera juste en dessous de la navbar pour confirmer la suppression du compte
			// TODO Créer une div de communication globale pour ce genre de cas
			request.setAttribute("sucessDelete", "Suppression de l'utilisateur réussie");
			response.sendRedirect(request.getContextPath() + "/home");
		} catch (BLLException e) {
			// TODO Prévoir un message qui s'affichera juste en dessous de la navbar pour dire que ça n'a pas fonctionné
			request.setAttribute("sucessDelete", "Suppression de l'utilisateur échoué");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/moncompte/profil");
			if (rd != null) {
				rd.forward(request, response);
			}
		}
	}

}
