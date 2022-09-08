package fr.eni.ventesauxencheres.dal;

import fr.eni.ventesauxencheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.ClientDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.ProfilDAOJdbcImpl;

public class FactoryDAO {

	public static ProfilDAO getProfilDAO() {
		return new ProfilDAOJdbcImpl();
	}

	public static ClientDAO getClientDAO() {
		return new ClientDAOJdbcImpl();
	}

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}

	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}



}
