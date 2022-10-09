package fr.eni.ventesauxencheres.dal.dao.encheres;

import fr.eni.ventesauxencheres.bo.encheres.Article;
import fr.eni.ventesauxencheres.bo.encheres.Enchere;
import fr.eni.ventesauxencheres.exceptions.DALException;

public interface EnchereDAO{
	Enchere selectByArticle(Article article) throws DALException;
	public void remplacerEnchere(Enchere enchere) throws DALException;
}
