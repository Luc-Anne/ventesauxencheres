package fr.eni.ventesauxencheres.dal.dao.encheres;

import java.util.List;

import fr.eni.ventesauxencheres.bo.encheres.Article;
import fr.eni.ventesauxencheres.dal.dao.DAO;
import fr.eni.ventesauxencheres.exceptions.DALException;

public interface ArticleDAO extends DAO<Article> {
	public List<Article> selectByIdUtilisateur(int id) throws DALException;
	public List<Article> selectListHome(String typeQuery, int idUtilisateurConnecte, String motCle, String libelle) throws DALException;
}
