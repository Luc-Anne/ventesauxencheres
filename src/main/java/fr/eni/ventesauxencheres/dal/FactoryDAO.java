package fr.eni.ventesauxencheres.dal;

import fr.eni.ventesauxencheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.RetraitDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class FactoryDAO {

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

}
