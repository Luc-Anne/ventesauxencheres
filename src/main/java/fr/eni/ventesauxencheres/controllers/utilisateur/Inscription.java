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
import fr.eni.ventesauxencheres.controllers.Errors;
import fr.eni.ventesauxencheres.controllers.Url;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/inscription.jsp");

		if (rd != null) {
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Errors erreurs = new Errors();
		request.setAttribute("erreurs", erreurs);

		String mot_de_passe = request.getParameter("password");
		String mot_de_passeConfirmation = request.getParameter("passwordConfirmation");
		Utilisateur utilisateur = new Utilisateur(
			request.getParameter("pseudo").trim(),
			request.getParameter("nom"),
			request.getParameter("prenom"),
			request.getParameter("email").trim(),
			request.getParameter("telephone"),
			request.getParameter("rue"),
			request.getParameter("code_postal"),
			request.getParameter("ville"),
			mot_de_passe,
			UtilisateurManager.DEFAULT_CREDIT,
			false
		);
		
		if(!mot_de_passe.equals(mot_de_passeConfirmation)) {
			request.getSession().setAttribute("messageGlobal", "Les deux mots de passe ne correspondent pas");
			reafficherInscriptionQuandErreur(request, response, utilisateur, erreurs);
			return;
		}

		try {
			utilisateur = UtilisateurManager.getInstance().save(utilisateur);
			request.getSession().setAttribute("utilisateurConnecte", utilisateur);
			request.getSession().setAttribute("messageGlobal", "Bienvenue sur le site !");
			response.sendRedirect(Url.HOME.getUrl());
			return;
		} catch (BLLException e) {
			erreurs.addAll(UtilisateurManager.getInstance().invalidCause(utilisateur));
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
			reafficherInscriptionQuandErreur(request, response, utilisateur, erreurs);
			return;
		}
		
	}
	
	private void reafficherInscriptionQuandErreur(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateur, Errors erreurs)
			throws ServletException, IOException {
		erreurs.addAll(UtilisateurManager.getInstance().invalidCause(utilisateur));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/inscription.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

}
