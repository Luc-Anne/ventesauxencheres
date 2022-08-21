package fr.eni.ventesauxencheres.controllers.administrateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Template
 */
@WebServlet("/admin/utilisateur")
public class GestionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Afficher le profil admin de l'utilisateur dans un formulaire de modification */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 1. Récupérer les paramètres
		   2. Vérifier que les paramètres sont conformes au cas spécifiques de la Servlet (les cas généraux seront gérés grâce aux validation dans les BLL)
		   3. Suivant les paramètres, les attributs disponibles dans les servlet et les données provenant d'appels à la BLL,
		      Renvoyer une vue spécifique à l'utilisateur, ce que soit en passant par une autre servlet ou bien par un appel une jsp.
		      Au cours de ce cheminement vers la vue, sauvegarder éventuellement des données dans des attributs ou bien grâve à des appels à la BLL
		*/
		
		/* Suivant l'action demandée, modification ou suppression ou désactivation, recueillir les informations nécessaires et traiter la demande */
		/* Afficher soit /admin si tout c'est bien passé, soit de nouveau le même formulaire avec les valeurs rentrées (qui seront indiqués comme erreur grâce à la gestion des erreurs) */
		
	}

}
