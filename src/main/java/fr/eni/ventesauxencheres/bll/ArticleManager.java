package fr.eni.ventesauxencheres.bll;

import fr.eni.ventesauxencheres.dal.ArticleDAO;
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
	
}
