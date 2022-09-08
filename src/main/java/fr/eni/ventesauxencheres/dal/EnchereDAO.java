package fr.eni.ventesauxencheres.dal;

import fr.eni.ventesauxencheres.bo.encheres.Article;
import fr.eni.ventesauxencheres.bo.encheres.Enchere;

public interface EnchereDAO extends DAO<Enchere> {
	Enchere selectByArticle(Article article) throws DALException;
	public void remplacerEnchere(Enchere enchere) throws DALException;
}
