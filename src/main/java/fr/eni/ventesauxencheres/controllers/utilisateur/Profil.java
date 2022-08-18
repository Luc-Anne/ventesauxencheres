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
		String pseudoAModifier = request.getParameter("pseudo");
		String nomAModifier = request.getParameter("nom");
		String prenomAModifier = request.getParameter("prenom");
		String emailAModifier = request.getParameter("email");
		String telephoneAModifier = request.getParameter("telephone");
		String rueAModifier = request.getParameter("rue");
		String CpAModifier = request.getParameter("code_postal");
		String villeAModifier = request.getParameter("ville");
		String mdpAModifier = request.getParameter("password");
		String creditAModifier = request.getParameter("credit");	
		System.out.println("pseudo non modifié : "+pseudoAModifier);
		System.out.println("nom modifié : "+nomAModifier);		
		System.out.println("Prenom non modifié : "+prenomAModifier);
			//Récupérer les informations de la session
			HttpSession session = request.getSession();
			//Creer un utilisateur avec les attributs de l'utilisateur connecté à la session à l'aide de l'objet utilisateurConnecte
			Utilisateur userSession=(Utilisateur) session.getAttribute("utilisateurConnecte");
			//Créer une nouvelle instance d'utilisateur, surlequel appliquer les modifications
			Utilisateur user=new Utilisateur(userSession.getNoUtilisateur(),pseudoAModifier,nomAModifier,prenomAModifier,emailAModifier,telephoneAModifier,rueAModifier,CpAModifier,villeAModifier,mdpAModifier);
			System.out.println("Info user de la BDD avant traitement : "+user);			
			  try { 
				  	UtilisateurManager.getInstance().modifier(user);
				  	session.setAttribute("utilisateurConnecte", user);
					} 
			  catch (BLLException e) { 
				  e.printStackTrace(); 
				  }		 
			System.out.println(nomAModifier);
			System.out.println("Info user de la BDD après traitement : "+user);
			System.out.println(nomAModifier);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/profil.jsp");
			if (rd != null) {
			rd.forward(request, response);
			}			

	}

}
