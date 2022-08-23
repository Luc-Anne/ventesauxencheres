package fr.eni.ventesauxencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Article;
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
	
}
