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
import fr.eni.ventesauxencheres.controllers.Errors;

/**
 * Servlet implementation class profil
 */
@WebServlet("/moncompte/profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/profil.jsp");
		if (rd != null) {
		rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Errors erreurs = new Errors();
		request.setAttribute("erreurs", erreurs);
		
		String pseudoAModifier = request.getParameter("pseudo");
		String nomAModifier = request.getParameter("nom");
		String prenomAModifier = request.getParameter("prenom");
		String emailAModifier = request.getParameter("email");
		String telephoneAModifier = request.getParameter("telephone");
		String rueAModifier = request.getParameter("rue");
		String CpAModifier = request.getParameter("code_postal");
		String villeAModifier = request.getParameter("ville");
		String mdpAModifier = request.getParameter("password");
		//Récupérer les informations de la session
		HttpSession session = request.getSession();
		//Creer un utilisateur avec les attributs de l'utilisateur connecté à la session à l'aide de l'objet utilisateurConnecte
		Utilisateur userSession=(Utilisateur) session.getAttribute("utilisateurConnecte");
		//Créer une nouvelle instance d'utilisateur, surlequel appliquer les modifications
		Utilisateur user=new Utilisateur(
				userSession.getNoUtilisateur(),
				pseudoAModifier,nomAModifier,
				prenomAModifier,
				emailAModifier,
				telephoneAModifier,
				rueAModifier,
				CpAModifier,
				villeAModifier,
				mdpAModifier,
				userSession.getCredit(),
				userSession.isAdministrateur()
		);
		try {
			UtilisateurManager.getInstance().modify(user);
			session.setAttribute("utilisateurConnecte", user);
		} catch (BLLException e) {
			erreurs.addAll(UtilisateurManager.getInstance().invalidCause(user));
			CharSequence erreurAChercher = "utilisateurs_pseudo_uq";
			if (e.getCause() != null && e.getCause().getCause() != null) {
				if (e.getCause().getCause().getMessage().contains(erreurAChercher)) {
					erreurs.add("utilisateur.pseudo_deja_pris");
				}
			}
			erreurAChercher = "utilisateurs_email_uq";
			if (e.getCause() != null && e.getCause().getCause() != null) {
				if (e.getCause().getCause().getMessage().contains(erreurAChercher)) {
					erreurs.add("utilisateur.email_deja_pris");
				}
			}
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/profil.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}
	
}
