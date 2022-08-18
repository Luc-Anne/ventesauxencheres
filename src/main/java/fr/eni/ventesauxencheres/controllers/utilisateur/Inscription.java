package fr.eni.ventesauxencheres.controllers.utilisateur;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class Inscription
 */
@WebServlet("/utilisateur/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Inscription() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		
		HttpSession session = request.getSession();

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String code_postal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String mot_de_passe = request.getParameter("password");
		int credit = 0;
		try {
			credit = Integer.parseInt(request.getParameter("credit"));
		} catch (NumberFormatException e) {
			credit = 0;
			erreurs.add("utilisateur.credit_not_int");
		}
		boolean administrateur = false;

		if (request.getParameter("status") == null) {
			administrateur = false;
		} else {
			administrateur = true;
		}
		System.out.println(administrateur);

		Utilisateur u = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,
				credit, administrateur);
		boolean entergistre = false;
		try {
			entergistre = UtilisateurManager.getInstance().inscription(u);
		} catch (BLLException e) {
			erreurs.addAll(UtilisateurManager.getInstance().invalidCause(u));
			e.printStackTrace();
		}
		for (String erreur : erreurs.getErreurs()) {
			System.out.println(erreur);
		}
		
		if (entergistre) {
			session.setAttribute("messageInscription", "Inscription réussie ");
			// faire une redirection
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");

			if (rd != null) {
				rd.forward(request, response);
			}
		} else {
			session.setAttribute("messageInscription", "Inscription Echoué");
//			request.setAttribute("messageConnexion", "Email ou mot de passe incorrect.");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/inscription.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		}

	}

}
