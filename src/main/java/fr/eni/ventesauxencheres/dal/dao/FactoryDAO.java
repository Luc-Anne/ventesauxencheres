package fr.eni.ventesauxencheres.dal.dao;

import fr.eni.ventesauxencheres.dal.bo.encheres.ArticleDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.bo.encheres.CategorieDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.bo.encheres.EnchereDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.bo.utilisateur.ClientDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.bo.utilisateur.ProfilDAOJdbcImpl;
import fr.eni.ventesauxencheres.dal.dao.encheres.ArticleDAO;
import fr.eni.ventesauxencheres.dal.dao.encheres.CategorieDAO;
import fr.eni.ventesauxencheres.dal.dao.encheres.EnchereDAO;
import fr.eni.ventesauxencheres.dal.dao.utilisateur.ClientDAO;
import fr.eni.ventesauxencheres.dal.dao.utilisateur.ProfilDAO;

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
