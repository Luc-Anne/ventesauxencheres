package fr.eni.ventesauxencheres.controllers.navigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ventesauxencheres.bll.ArticleManager;
import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bll.UtilisateurManager;
import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Utilisateur;


@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Article> articlesList = new ArrayList<Article>();
		try {
			articlesList = ArticleManager.getInstance().getAll();
			//Mettre la liste d'article dans la requete car besoin uniquement dans la home
			request.setAttribute("articlesList",articlesList);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String motCle = request.getParameter("motCle");
		String libelle = request.getParameter("categorie");	
		switch (libelle) {
		case "Toutes":
			libelle ="";
			break;
		case "Informatique":
			libelle ="Informatique";
			break;
		case "Ameublement":
			libelle ="Ameublement";
			break;
		case "Vêtement":
			libelle ="Vêtement";
			break;	
		case "Sport & Loisir":
			libelle ="Sport & Loisir";
			break;				
		}
		//récupérer la valeur du bouton radio coché et la renvoyer dans la requête
		String typeEncheres = request.getParameter("typeEncheres");
		request.setAttribute("typeEncheres", typeEncheres);		
		//récupérer les paramètres des checkbox et les renvoyer
		String encheresStatut = request.getParameter("encheres");
		request.setAttribute("encheresStatut", encheresStatut);
		String ventesStatut = request.getParameter("ventes");
		request.setAttribute("ventesStatut", ventesStatut);
		//Alimenter la variable typeQuery selon les filtres
		String typeQuery="";
		if (typeEncheres.equals("achats")) {
			switch (encheresStatut) {
			case "ouvertes":
				typeQuery="OpenedBids";
				break;
			case "encours":
				typeQuery="MyBids";
				break;
			case "remportees":
				typeQuery="MyWonBids";
				break;
			}
		} else if (typeEncheres.equals("ventes")) {
			switch (ventesStatut) {
			case "venteencours":
				typeQuery="MyCurrentSales";
				break;
			case "nondebutees":
				typeQuery="MyUnstartedSales";
				break;
			case "terminees":
				typeQuery="MyClosedSales";
				break;
			}			
		}
		try {
			//Appeler une session pour récupérer l'utilisateur connecté
			HttpSession session = request.getSession();
			//Créer une nouvelle instance utilisateur pour contenir l'utilisateur connecté en appelant l'attribut de la session
			Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
			//Récupérer l'id de l'utilisateur connecté
			int idUtilisateurConnecte = utilisateurConnecte.getNoUtilisateur();

			// Test ajout progressif
			List<Article> enchereListeHome = ArticleManager.getInstance().showListeHome(typeQuery, idUtilisateurConnecte, motCle,libelle);
			request.setAttribute("enchereListeHome", enchereListeHome);
			
			//Methodes à supprimer si factorisation opérationnelle
			/*
			 * List<Article>
			 * encheresOuvertesListe=ArticleManager.getInstance().showOpenedBids(
			 * idUtilisateurConnecte); List<Article>
			 * MesEncheresListe=ArticleManager.getInstance().showMyBids(
			 * idUtilisateurConnecte); List<Article>
			 * MesEncheresGagneesListe=ArticleManager.getInstance().showMyWonBids(
			 * idUtilisateurConnecte); List<Article>
			 * MesVentesEncoursListe=ArticleManager.getInstance().showMyCurrentSales(
			 * idUtilisateurConnecte); List<Article>
			 * MesVentesNonDebuteesListe=ArticleManager.getInstance().showMyUnstartedSales(
			 * idUtilisateurConnecte); List<Article>
			 * MesVentesTermineesListe=ArticleManager.getInstance().showMyClosedSales(
			 * idUtilisateurConnecte); //Envoyer la liste récupérée
			 * request.setAttribute("encheresOuvertesListe", encheresOuvertesListe);
			 * request.setAttribute("MesEncheresListe", MesEncheresListe);
			 * request.setAttribute("MesEncheresGagneesListe", MesEncheresGagneesListe);
			 * request.setAttribute("MesVentesEncoursListe", MesVentesEncoursListe);
			 * request.setAttribute("MesVentesNonDebuteesListe", MesVentesNonDebuteesListe);
			 * request.setAttribute("MesVentesTermineesListe", MesVentesTermineesListe);
			 */	
			
			/*
			 * try { List<Article> articles =
			 * ArticleManager.getInstance().selectByMotCleAndByLibelle(motCle); for (Article
			 * article : articles) {
			 * System.out.println("On est dans la home servlet : "+article.toString()); }
			 * request.setAttribute("motCle", motCle); } catch (BLLException e) {
			 * e.printStackTrace(); }
			 */
		} catch (BLLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}		
	}
}
