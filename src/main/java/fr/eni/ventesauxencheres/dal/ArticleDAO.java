package fr.eni.ventesauxencheres.dal;

import java.util.List;

import fr.eni.ventesauxencheres.bo.Article;

public interface ArticleDAO extends DAO<Article> {
	public List<Article> selectAll() throws DALException ;
	public List<Article> selectOpenedBids(int idUtilisateurConnecte) throws DALException;
	public List<Article> selectMyBids(int idUtilisateurConnecte) throws DALException;
	public List<Article> selectMyWonBids(int idUtilisateurConnecte) throws DALException;
	public List<Article> selectMyCurrentSales(int idUtilisateurConnecte) throws DALException;
	public List<Article> selectUnstartedSales(int idUtilisateurConnecte) throws DALException;
	public List<Article> selectClosedSales(int idUtilisateurConnecte) throws DALException;	
	public List<Article> selectByMotCleAndByLibelle(String motCle)throws DALException;
	
	public List<Article> selectListHome(String typeQuery, int idUtilisateurConnecte, String motCle)throws DALException;

}
