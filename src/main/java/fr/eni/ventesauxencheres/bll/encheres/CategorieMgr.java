package fr.eni.ventesauxencheres.bll.encheres;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.encheres.Categorie;
import fr.eni.ventesauxencheres.dal.dao.FactoryDAO;
import fr.eni.ventesauxencheres.dal.dao.encheres.CategorieDAO;
import fr.eni.ventesauxencheres.exceptions.BLLException;
import fr.eni.ventesauxencheres.exceptions.DALException;

public class CategorieMgr {
	// TODO
	private static CategorieMgr categorieManager;

	private CategorieDAO categorieDAO;

	private CategorieMgr() {
		categorieDAO = FactoryDAO.getCategorieDAO();
	}

	public static CategorieMgr getInstance() {
		if (categorieManager == null) {
			categorieManager = new CategorieMgr();
		}
		return categorieManager;
	}

	// Règles métiers

	// Validation
	public boolean isValide(Categorie categorie) {
		return invalidCause(categorie).size() == 0 ? true : false;
	}

	public List<String> invalidCause(Categorie categorie)  {
		List<String> invalidCause = new ArrayList<>();
		// TODO Remplir des critères de validation
		return invalidCause;
	}

	// Méthodes métier basiques
	public Categorie save(Categorie u) throws BLLException {
		try {
			if (this.isValide(u)) {
				return categorieDAO.insert(u);
			} else {
				throw new BLLException("categorie invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public Categorie getById(int id) throws BLLException {
		try {
			return categorieDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public List<Categorie> getAll() throws BLLException {
		try {
			return categorieDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public void modify(Categorie categorie)throws BLLException{
		try {
			if (this.isValide(categorie)) {
				categorieDAO.update(categorie);
			} else {
				throw new BLLException("categorie invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public void delete(int id) throws BLLException {
		try {
			categorieDAO.delete(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

}
