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
 * Servlet implementation class profil
 */
@WebServlet("/utilisateur/profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/profil.jsp");
		if (rd != null) {
		rd.forward(request, response);
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//(pseudo, nom,prenom, email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenomNew = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String code_postal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String mot_de_passe = request.getParameter("password");
		String credit = request.getParameter("credit");	
		System.out.println(pseudo);
		System.out.println(prenomNew);
		System.out.println(rue);

			//Récupérer les informations de la session
			HttpSession session = request.getSession();
			Utilisateur user=(Utilisateur) session.getAttribute("utilisateurConnecte");
			System.out.println(user);			
			session.setAttribute("prenom", prenomNew);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/profil.jsp");
			if (rd != null) {
			rd.forward(request, response);
			}			

	}

}
