package fr.eni.ventesauxencheres.bll.encheres;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.encheres.Article;
import fr.eni.ventesauxencheres.bo.encheres.Enchere;
import fr.eni.ventesauxencheres.dal.dao.FactoryDAO;
import fr.eni.ventesauxencheres.dal.dao.encheres.EnchereDAO;
import fr.eni.ventesauxencheres.exceptions.BLLException;
import fr.eni.ventesauxencheres.exceptions.DALException;

public class EnchereMgr {
	// TODO
	private static EnchereMgr enchereManager;

	private EnchereDAO enchereDAO;

	private EnchereMgr() {
		enchereDAO = FactoryDAO.getEnchereDAO();
	}

	public static EnchereMgr getInstance() {
		if (enchereManager == null) {
			enchereManager = new EnchereMgr();
		}
		return enchereManager;
	}

	// Règles métiers

	// Validation
	public boolean isValide(Enchere enchere) {
		return invalidCause(enchere).size() == 0 ? true : false;
	}

	public List<String> invalidCause(Enchere enchere)  {
		List<String> invalidCause = new ArrayList<>();
		// TODO Remplir des critères de validation
		return invalidCause;
	}

	// Méthodes métier basiques
	public Enchere save(Enchere u) throws BLLException {
		try {
			if (this.isValide(u)) {
				return enchereDAO.insert(u);
			} else {
				throw new BLLException("enchere invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public Enchere get(int id) throws BLLException {
		try {
			return enchereDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public List<Enchere> getAll() throws BLLException {
		try {
			return enchereDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public Enchere getByArticle(Article article) throws BLLException {
		try {
			return enchereDAO.selectByArticle(article);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public void modify(Enchere enchere)throws BLLException{
		try {
			if (this.isValide(enchere)) {
				enchereDAO.update(enchere);
			} else {
				throw new BLLException("enchere invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public void delete(int id) throws BLLException {
		try {
			enchereDAO.delete(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public void remplacerEncherisseur(Enchere enchere) throws BLLException {
		try {
			if (this.isValide(enchere)) {
				enchereDAO.remplacerEnchere(enchere);
			} else {
				throw new BLLException("enchere invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

}
