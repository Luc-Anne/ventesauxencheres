package fr.eni.ventesauxencheres.controllers.encheres;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ventesauxencheres.bll.encheres.ArticleMgr;
import fr.eni.ventesauxencheres.bll.encheres.CategorieMgr;
import fr.eni.ventesauxencheres.bo.encheres.Article;
import fr.eni.ventesauxencheres.bo.encheres.Categorie;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.controllers.util.Url;
import fr.eni.ventesauxencheres.exceptions.BLLException;


@WebServlet("/encheres/ajouter")
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/article/nouvelleVente.jsp");
		if (rd != null) {rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		HttpSession session = request.getSession();
		Article article = new Article();
		Categorie categorie = new Categorie();
		// Retrait lieuDeRetrait = new Retrait();

		String nom = request.getParameter("article");
		String description = request.getParameter("description");

		Client utilisateur = (Client) session.getAttribute("utilisateurConnecte");

		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		int categorieId =Integer.parseInt(request.getParameter("categorie"));
		try {
			categorie = CategorieMgr.getInstance().getById(categorieId);
		} catch (BLLException e2) {
			e2.printStackTrace();
		}

		String dateDebut = request.getParameter("dateDebutEnchere");
		String Debut[] = dateDebut.split("T");
	    LocalDateTime dateDebutEnchere = LocalDateTime.of(LocalDate.parse(Debut[0]), LocalTime.parse(Debut[1]));
	    String dateFin = request.getParameter("dateFinEnchere");
		String Fin[] = dateFin.split("T");
	    LocalDateTime dateFintEnchere = LocalDateTime.of(LocalDate.parse(Fin[0]), LocalTime.parse(Fin[1]));

	    // TODO Use constructor
		article.setNomArticle(nom);
		article.setDescription(description);
		article.setDateDebutEncheres(dateDebutEnchere);
		article.setDateFinEncheres(dateFintEnchere);
		article.setMiseAPrix(miseAPrix);
		article.setCategorieArticle(categorie);
		article.setVendeur(utilisateur);
		article.setEtatVente("CR");

		try {
			ArticleMgr.getInstance().save(article);
		} catch (BLLException e) {
			e.printStackTrace();
		}

		session.setAttribute("messageGlobal", "Votre article a bien été ajouté");
		response.sendRedirect(Url.HOME.getUrl());
	}

}
