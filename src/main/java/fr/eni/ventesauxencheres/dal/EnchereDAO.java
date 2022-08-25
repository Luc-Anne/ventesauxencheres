package fr.eni.ventesauxencheres.dal;

import java.util.List;

import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Enchere;

public interface EnchereDAO extends DAO<Enchere> {
	Enchere selectByArticle(Article article) throws DALException;
	public void remplacerEnchere(Enchere enchere) throws DALException;
}
