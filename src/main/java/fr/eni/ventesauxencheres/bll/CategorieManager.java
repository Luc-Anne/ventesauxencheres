package fr.eni.ventesauxencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Categorie;
import fr.eni.ventesauxencheres.dal.CategorieDAO;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;

public class CategorieManager {
	
	private static CategorieManager categorieManager;

	private CategorieDAO categorieDAO;
	
	private CategorieManager() {
		categorieDAO = FactoryDAO.getCategorieDAO();
	}
	
	public static CategorieManager getInstance() {
		if (categorieManager == null) {
			categorieManager = new CategorieManager();
		}
		return categorieManager;
	}
	
	// Règles métiers
	
	// Validation
	public boolean isValide(Categorie categorie) {
		return invalidCause(categorie).size() == 0 ? true : false;
	}
	
	public List<String> invalidCause(Categorie categorie)  {
		// TODO vérifier que la longueur des chaines de caractères soient bien compatibles avec les contraintes sql
		// TODO vérifier qu'il n'y a pas d'espace à l'intérieur des pseudos
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
	
	public Categorie get(int id) throws BLLException {
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
