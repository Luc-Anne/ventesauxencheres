package fr.eni.ventesauxencheres.bll;

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
	
}
