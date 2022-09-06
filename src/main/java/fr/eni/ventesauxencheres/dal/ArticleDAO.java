package fr.eni.ventesauxencheres.dal;

import java.util.List;

import fr.eni.ventesauxencheres.bo.Article;

public interface ArticleDAO extends DAO<Article> {
	public List<Article> selectByIdUtilisateur(int id) throws DALException;	
	public List<Article> selectListHome(String typeQuery, int idUtilisateurConnecte, String motCle, String libelle) throws DALException;
}
