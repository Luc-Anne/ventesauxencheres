package fr.eni.ventesauxencheres.dal;

import java.util.List;

import fr.eni.ventesauxencheres.bo.Article;

public interface ArticleDAO extends DAO<Article> {
	public List<Article> selectAll() throws DALException ;
}
