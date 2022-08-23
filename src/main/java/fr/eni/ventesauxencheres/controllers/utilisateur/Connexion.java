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

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/connexion.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("motDePasse");
		
		try {
			Utilisateur utilisateur = UtilisateurManager.getInstance().connexion(email, password);
			if (utilisateur != null) {
				HttpSession session = request.getSession();
				session.setAttribute("utilisateurConnecte", utilisateur);
				// TODO rediriger vers /admin quand la servlet sera créé
				if (utilisateur.isAdministrateur()) {
					response.sendRedirect(Url.ADMIN_TABLEAUDEBORD.getUrl());
				} else {
					response.sendRedirect(Url.HOME.getUrl());
				}
			} else {
				request.setAttribute("echecConnexion", "Email ou mot de passe incorrect.");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/connexion.jsp");
				if (rd != null) {
					rd.forward(request, response);
				}
			}
		} catch (BLLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

}