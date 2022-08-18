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

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/utilisateur/connexion")
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
		
		// On doit récupérer la fonctionnalité connexion
		Utilisateur u;
		try {
			u = UtilisateurManager.getInstance().connexion(email, password);
			// On va devoir transporter les paramètres dans l'utilisateur
			// Paramétrage de la session lors de sa création ici
			HttpSession session = request.getSession();
			session.setAttribute("utilisateurConnecte", u);
			if (u != null) {
				request.setAttribute("messageConnexion", "Connexion réussie !");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
				if (rd != null) {
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("messageConnexion", "Email ou mot de passe incorrect.");
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