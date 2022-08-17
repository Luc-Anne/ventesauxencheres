package fr.eni.ventesauxencheres.bll;

import fr.eni.ventesauxencheres.dal.EnchereDAO;
import fr.eni.ventesauxencheres.dal.FactoryDAO;

public class EnchereManager {
	
	private static EnchereManager enchereManager;

	private EnchereDAO enchereDAO;
	
	private EnchereManager() {
		enchereDAO = FactoryDAO.getEnchereDAO();
	}
	
	public static EnchereManager getInstance() {
		if (enchereManager == null) {
			enchereManager = new EnchereManager();
		}
		return enchereManager;
	}
	
}
