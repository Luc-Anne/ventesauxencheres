package fr.eni.ventesauxencheres.dal.dao;

import fr.eni.ventesauxencheres.dal.dao.encheres.ArticleDAO;
import fr.eni.ventesauxencheres.dal.dao.encheres.CategorieDAO;
import fr.eni.ventesauxencheres.dal.dao.encheres.EnchereDAO;
import fr.eni.ventesauxencheres.dal.dao.utilisateur.ClientDAO;
import fr.eni.ventesauxencheres.dal.dao.utilisateur.ProfilDAO;
import fr.eni.ventesauxencheres.dal.jdbc.encheres.ArticleDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.encheres.CategorieDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.encheres.EnchereDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.utilisateur.ClientDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.jdbc.utilisateur.ProfilDAOJdbcImpl;

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
