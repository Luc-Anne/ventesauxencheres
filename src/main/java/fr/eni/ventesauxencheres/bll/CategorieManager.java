package fr.eni.ventesauxencheres.bll;

import fr.eni.ventesauxencheres.dal.CategorieDAO;
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
	
}
