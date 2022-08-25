package fr.eni.ventesauxencheres.controllers.ventes;

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

import fr.eni.ventesauxencheres.bll.ArticleManager;
import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bll.CategorieManager;
import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Categorie;
import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.controllers.Url;


@WebServlet("/nouvelleVente/add")
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/article/nouvelleVente.jsp");
		if (rd != null) {rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Article article = new Article();
		Categorie categorie = new Categorie();
//		Retrait lieuDeRetrait = new Retrait();
		
		String nom = request.getParameter("article");
		String description = request.getParameter("description");
		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
		
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		int categorieId =Integer.parseInt(request.getParameter("categorie"));
		try {
			categorie = CategorieManager.getInstance().getById(categorieId);
		} catch (BLLException e2) {
			e2.printStackTrace();
		}
		
		String dateDebut = request.getParameter("dateDebutEnchere");
		String Debut[] = dateDebut.split("T");        
	    LocalDateTime dateDebutEnchere = LocalDateTime.of(LocalDate.parse(Debut[0]), LocalTime.parse(Debut[1]));
	    String dateFin = request.getParameter("dateFinEnchere");
		String Fin[] = dateFin.split("T");
	    LocalDateTime dateFintEnchere = LocalDateTime.of(LocalDate.parse(Fin[0]), LocalTime.parse(Fin[1]));
	    
//	    
//		dateDebutEnchere = LocalDateTime.parse(request.getParameter("dateDebutEnchere"));
//		dateFintEnchere = LocalDateTime.parse(request.getParameter("dateFinEnchere"));
		
		
//		System.out.println(dateDebutEnchere + "servlet");
		

//		lieuDeRetrait = new Retrait();
//		lieuDeRetrait.setRue(rue);
//		lieuDeRetrait.setCodePostal(codePostale);
//		lieuDeRetrait.setCodePostal(ville);
		
//		try {
//			RetraitManager.getInstance().save(lieuDeRetrait);
//		} catch (BLLException e1) {
//			e1.printStackTrace();
//		} 

		article.setNomArticle(nom);
		article.setDescription(description);
		article.setDateDebutEncheres(dateDebutEnchere);
		article.setDateFinEncheres(dateFintEnchere);
		article.setMiseAPrix(miseAPrix);
		article.setCategorieArticle(categorie);
		article.setVendeur(utilisateur);
		article.setEtatVente("CR");
//		article.setLieuDeRetrait(lieuDeRetrait);
	
		//article.setLieuDeRetrait(lieuDeRetrait);

		try {
			ArticleManager.getInstance().save(article);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("messageGlobal", "Votre article a bien été ajouté");
		response.sendRedirect(Url.HOME.getUrl());
	}

}
