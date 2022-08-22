package fr.eni.ventesauxencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Retrait;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;
import fr.eni.ventesauxencheres.dal.RetraitDAO;

public class RetraitManager {
	
	private static RetraitManager retraitManager;

	private RetraitDAO retraitDAO;
	
	private RetraitManager() {
		retraitDAO = FactoryDAO.getRetraitDAO();
	}
	
	public static RetraitManager getInstance() {
		if (retraitManager == null) {
			retraitManager = new RetraitManager();
		}
		return retraitManager;
	}
	
	// Règles métiers
	
	// Validation
	public boolean isValide(Retrait retrait) {
		return invalidCause(retrait).size() == 0 ? true : false;
	}
	
	public List<String> invalidCause(Retrait retrait)  {
		// TODO vérifier que la longueur des chaines de caractères soient bien compatibles avec les contraintes sql
		// TODO vérifier qu'il n'y a pas d'espace à l'intérieur des pseudos
		List<String> invalidCause = new ArrayList<>();
		// TODO Remplir des critères de validation
		return invalidCause;
	}
	
	// Méthodes métier basiques
	public Retrait save(Retrait retrait) throws BLLException {
		try {
			if (this.isValide(retrait)) {
				return retraitDAO.insert(retrait);
			} else {
				throw new BLLException("retrait invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}
	
	public Retrait get(int id) throws BLLException {
		try {
			return retraitDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}
	
	public List<Retrait> getAll() throws BLLException {
		try {
			return retraitDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}
	
	public void modify(Retrait retrait)throws BLLException{
		try {
			if (this.isValide(retrait)) {
				retraitDAO.update(retrait);
			} else {
				throw new BLLException("retrait invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}
	
	public void delete(int id) throws BLLException {
		try {
			retraitDAO.delete(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}
	
}
