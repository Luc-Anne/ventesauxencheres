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

import fr.eni.ventesauxencheres.bll.encheres.ArticleMgr;
import fr.eni.ventesauxencheres.bo.encheres.Article;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.controllers.util.Url;
import fr.eni.ventesauxencheres.exceptions.BLLException;


@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Article> articlesList = new ArrayList<>();
		try {
			articlesList = ArticleMgr.getInstance().getAll();
			//Mettre la liste d'article dans la requete car besoin uniquement dans la home
			request.setAttribute("articlesList", articlesList);

			RequestDispatcher rd = request.getRequestDispatcher(Url.HOME.getJsp());
			if (rd != null) {
				rd.forward(request, response);
			}
		} catch (BLLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

	@Override
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
		if (encheresStatut == null) {
			encheresStatut = "";
		}
		request.setAttribute("encheresStatut", encheresStatut);
		String ventesStatut = request.getParameter("ventes");
		if (ventesStatut == null) {
			ventesStatut = "";
		}
		request.setAttribute("ventesStatut", ventesStatut);
		//Alimenter la variable typeQuery selon les filtres
		String typeQuery="";
		if ("achats".equals(typeEncheres)) {
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
		} else if ("ventes".equals(typeEncheres)) {
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
			Client utilisateurConnecte = (Client) session.getAttribute("clientConnecte");
			//Récupérer l'id de l'utilisateur connecté
			int idUtilisateurConnecte = utilisateurConnecte.getNoUtilisateur();

			// Test ajout progressif
			List<Article> enchereListeHome = ArticleMgr.getInstance().showListeHome(typeQuery, idUtilisateurConnecte, motCle,libelle);
			request.setAttribute("enchereListeHome", enchereListeHome);

		} catch (BLLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher(Url.HOME.getJsp());
		if (rd != null) {
			rd.forward(request, response);
		}
	}

}
