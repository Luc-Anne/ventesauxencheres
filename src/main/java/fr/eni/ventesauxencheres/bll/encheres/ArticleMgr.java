package fr.eni.ventesauxencheres.bll.encheres;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.encheres.Article;
import fr.eni.ventesauxencheres.bo.encheres.Enchere;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.dal.dao.FactoryDAO;
import fr.eni.ventesauxencheres.dal.dao.encheres.ArticleDAO;
import fr.eni.ventesauxencheres.exceptions.BLLException;
import fr.eni.ventesauxencheres.exceptions.DALException;

public class ArticleMgr {
	// TODO
	private static ArticleMgr articleManager;

	private ArticleDAO articleDAO;

	private ArticleMgr() {
		articleDAO = FactoryDAO.getArticleDAO();
	}

	public static ArticleMgr getInstance() {
		if (articleManager == null) {
			articleManager = new ArticleMgr();
		}
		return articleManager;
	}

	// Règles métiers

	// Validation
	public boolean isValide(Article article) {
		return invalidCause(article).size() == 0 ? true : false;
	}

	public List<String> invalidCause(Article article)  {
		List<String> invalidCause = new ArrayList<>();
		// TODO Remplir des critères de validation
		return invalidCause;
	}

	// Méthodes métier basiques
	public Article save(Article u) throws BLLException {
		try {
			if (this.isValide(u)) {
				return articleDAO.insert(u);
			} else {
				throw new BLLException("article invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public Article get(int id) throws BLLException {
		try {
			return articleDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public List<Article> getAll() throws BLLException {
		try {
			return articleDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public void modify(Article article)throws BLLException{
		try {
			if (this.isValide(article)) {
				articleDAO.update(article);
			} else {
				throw new BLLException("article invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public void delete(int id) throws BLLException {
		try {
			articleDAO.delete(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public boolean hasArticle(int id) throws BLLException {
		try {
			return articleDAO.selectByIdClient(id).size() != 0;
		} catch (DALException e) {
			throw new BLLException("ERREUR MANAGER", e);
		}
	}

	public boolean canDisplayDetails(Article article, Client utilisateurConnecte) {
		if (utilisateurConnecte == null) {
			// Bloqué les détails à un utilisateur non connecté
			return false;
		}
		Client vendeur = article.getVendeur();
		Enchere enchere = article.getEnchere();
		//enchere = EnchereManager.getInstance().getByArticle(article);
		Client encherisseur = null;
		if (enchere != null) {
			encherisseur = enchere.getEncherisseur();
		}

		if (
			(article.getEtatVente().equals("VD") && utilisateurConnecte.equals(vendeur) && encherisseur == null)
			){
			return true;
		} else if (
			(article.getEtatVente().equals("VD") && utilisateurConnecte.equals(vendeur))
		){
			return true;
		} else if (
			(article.getEtatVente().equals("VD") && utilisateurConnecte.equals(encherisseur))
		){
			return true;
		} else if (
			(article.getEtatVente().equals("EC") && utilisateurConnecte.equals(vendeur)) ||
			(article.getEtatVente().equals("RT") && utilisateurConnecte.equals(vendeur)) ||
			(article.getEtatVente().equals("RT") && utilisateurConnecte.equals(encherisseur))
		){
			return true;
		}else if (
			article.getEtatVente().equals("CR") ||
			(article.getEtatVente().equals("VD") && !utilisateurConnecte.equals(encherisseur)) ||
			(article.getEtatVente().equals("RT") && !utilisateurConnecte.equals(encherisseur))
		) {
			return false;
		} else if (
			(article.getEtatVente().equals("EC") && !utilisateurConnecte.equals(vendeur))
		){
			return true;
		}  else {
			return true;
		}
	}

	public List<Article> showListeHome(String typeQuery, int idClientConnecte, String motCle, String libelle) throws BLLException {
		List<Article> articles = null;
		try {
			articles = articleDAO.selectListHome(typeQuery, idClientConnecte, motCle, libelle);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return articles;
	}

}
