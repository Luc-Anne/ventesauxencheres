package fr.eni.ventesauxencheres.controllers.utilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bll.UtilisateurManager;
import fr.eni.ventesauxencheres.bo.Utilisateur;

/**
 * Servlet implementation class profil
 */
@WebServlet("/profil/*")
public class ProfilPublic extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudoProfilDemande = request.getPathInfo().substring(1);
		Utilisateur utilisateur = null;
		try {
			utilisateur = UtilisateurManager.getInstance().getByPseudo(pseudoProfilDemande);
			request.setAttribute("utilisateur", utilisateur);
			if (utilisateur == null) {
				response.sendError(404);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/profilPublic.jsp");
				if (rd != null) {
					rd.forward(request, response);
				}
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

}