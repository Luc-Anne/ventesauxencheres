package fr.eni.ventesauxencheres.controllers.business;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Template
 */
@WebServlet("/moncompte/article")
public class gestionArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage du formulaire */
		/* Rempli avec des informations sur un article dans le cas d'une modification */
		/* Penser au bouton suppression (dans le cas d'un vente non terminée) */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 1. Récupérer les paramètres
		   2. Vérifier que les paramètres sont conformes au cas spécifiques de la Servlet (les cas généraux seront gérés grâce aux validation dans les BLL)
		   3. Suivant les paramètres, les attributs disponibles dans les servlet et les données provenant d'appels à la BLL,
		      Renvoyer une vue spécifique à l'utilisateur, ce que soit en passant par une autre servlet ou bien par un appel une jsp.
		      Au cours de ce cheminement vers la vue, sauvegarder éventuellement des données dans des attributs ou bien grâve à des appels à la BLL
		*/
		
		/* Création ou modification de l'article suivant l'action recueilli dans le getParameter */
		/* Suppresion de l'article possible dans le cas d'une vente non terminée */
		
	}

}
