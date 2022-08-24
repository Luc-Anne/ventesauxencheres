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

import fr.eni.ventesauxencheres.bll.ArticleManager;
import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bo.Article;


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
		//récupérer la valeur du bouton radio coché et la renvoyer dans la requête
		String typeEncheres = request.getParameter("type-encheres");
		request.setAttribute("typeEncheres", typeEncheres);		
		//récupérer les paramètres des checkbox et les renvoyer
		String encheresStatut = request.getParameter("encheres");
		request.setAttribute("encheresStatut", encheresStatut);
		String ventesStatut = request.getParameter("ventes");
		request.setAttribute("ventesStatut", ventesStatut);
		//Appeler la liste des achats avec encheres ouvertes
		try {
			List<Article> encheresOuvertesListe=ArticleManager.getInstance().showOpenedBids();
			List<Article> MesEncheresListe=ArticleManager.getInstance().showMyBids();
			List<Article> MesEncheresGagneesListe=ArticleManager.getInstance().showMyWonBids();
			List<Article> MesVentesEncoursListe=ArticleManager.getInstance().showMyCurrentSales();
			List<Article> MesVentesNonDebuteesListe=ArticleManager.getInstance().showMyUnstartedSales();
			List<Article> MesVentesTermineesListe=ArticleManager.getInstance().showMyClosedSales();
			//Envoyer la liste récupérée
			request.setAttribute("encheresOuvertesListe", encheresOuvertesListe);
			request.setAttribute("MesEncheresListe", MesEncheresListe);
			request.setAttribute("MesEncheresGagneesListe", MesEncheresGagneesListe);
			request.setAttribute("MesVentesEncoursListe", MesVentesEncoursListe);
			request.setAttribute("MesVentesNonDebuteesListe", MesVentesNonDebuteesListe);
			request.setAttribute("MesVentesTermineesListe", MesVentesTermineesListe);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}		
	}

}
