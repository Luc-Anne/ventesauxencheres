package fr.eni.ventesauxencheres.dal;

import fr.eni.ventesauxencheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class FactoryDAO {

	public static UtilisateurDAO getUtilisateurDAO() {
		UtilisateurDAO utlisDao = new UtilisateurDAOJdbcImpl();
		return utlisDao;
	}

}
