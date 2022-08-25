package fr.eni.ventesauxencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Enchere;
import fr.eni.ventesauxencheres.dal.ArticleDAO;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;

public class ArticleManager {
	
	private static ArticleManager articleManager;

	private ArticleDAO articleDAO;
	
	private ArticleManager() {
		articleDAO = FactoryDAO.getArticleDAO();
	}
	
	public static ArticleManager getInstance() {
		if (articleManager == null) {
			articleManager = new ArticleManager();
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
	
	public List<Article> showOpenedBids (int idUtilisateurConnecte)throws BLLException {
		try {
			return articleDAO.selectOpenedBids(idUtilisateurConnecte);
		} catch (DALException e) {
			throw new BLLException("", e);
		}		
	}
	public List<Article> showMyBids (int idUtilisateurConnecte)throws BLLException {
		try {
			return articleDAO.selectMyBids(idUtilisateurConnecte);
		} catch (DALException e) {
			throw new BLLException("", e);
		}		
	}	
	public List<Article> showMyWonBids (int idUtilisateurConnecte)throws BLLException {
		try {
			return articleDAO.selectMyWonBids(idUtilisateurConnecte);
		} catch (DALException e) {
			throw new BLLException("", e);
		}		
	}	
	public List<Article> showMyCurrentSales (int idUtilisateurConnecte)throws BLLException {
		try {
			return articleDAO.selectMyCurrentSales(idUtilisateurConnecte);
		} catch (DALException e) {
			throw new BLLException("", e);
		}		
	}
	public List<Article> showMyUnstartedSales (int idUtilisateurConnecte)throws BLLException {
		try {
			return articleDAO.selectUnstartedSales(idUtilisateurConnecte);
		} catch (DALException e) {
			throw new BLLException("", e);
		}		
	}	
	public List<Article> showMyClosedSales (int idUtilisateurConnecte)throws BLLException {
		try {
			return articleDAO.selectClosedSales(idUtilisateurConnecte);
		} catch (DALException e) {
			throw new BLLException("", e);
		}		
	}	
	
	public List<Article> selectByMotCleAndByLibelle(String motCle) throws BLLException {
		List<Article> articles = null;
		
		try {
			articles = articleDAO.selectByMotCleAndByLibelle(motCle);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	//Test factorisation methode show general	
	public List<Article> showListeHome(String typeQuery, int idUtilisateurConnecte, String motCle) throws BLLException {
		List<Article> articles = null;
		System.out.println("BLL : "+idUtilisateurConnecte);
		try {
			articles = articleDAO.selectListHome(typeQuery, idUtilisateurConnecte, motCle);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return articles;
	}	
}
