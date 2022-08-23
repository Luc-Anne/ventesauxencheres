package fr.eni.ventesauxencheres.dal;

import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Enchere;

public interface EnchereDAO  extends DAO<Enchere> {
	Enchere selectByArticle(Article article) throws DALException;
}
