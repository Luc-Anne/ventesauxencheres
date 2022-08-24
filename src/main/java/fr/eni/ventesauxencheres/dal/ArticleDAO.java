package fr.eni.ventesauxencheres.dal;

import java.util.List;

import fr.eni.ventesauxencheres.bo.Article;

public interface ArticleDAO extends DAO<Article> {
	public List<Article> selectAll() throws DALException ;
	public List<Article> selectOpenedBids() throws DALException;
	public List<Article> selectMyBids() throws DALException;
	public List<Article> selectMyWonBids() throws DALException;
	public List<Article> selectMyCurrentSales() throws DALException;
	public List<Article> selectUnstartedSales() throws DALException;
	public List<Article> selectClosedSales() throws DALException;	
}
