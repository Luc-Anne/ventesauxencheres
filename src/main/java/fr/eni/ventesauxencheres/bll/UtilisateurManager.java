package fr.eni.ventesauxencheres.bll;

import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;
import fr.eni.ventesauxencheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private static UtilisateurManager utilisateurManager;

	private UtilisateurDAO utilisateurDAO;
	
	private UtilisateurManager() {
		utilisateurDAO = FactoryDAO.getUtilisateurDAO();
	}
	
	public static UtilisateurManager getInstance() {
		if (utilisateurManager == null) {
			utilisateurManager = new UtilisateurManager();
		}
		return utilisateurManager;
	}
	
	public Utilisateur connexion(String email, String password) throws BLLException {
		try {
			return utilisateurDAO.connexion(email, password);
		} catch (DALException e) {
			throw new BLLException("ERREUR MANAGER", e);
		}
	}
	
	public boolean inscription(Utilisateur u) throws BLLException {
		try {
			return utilisateurDAO.inscription(u);
		} catch (Exception e) {
			throw new BLLException("ERREUR MANAGER class Utilisateur manager", e);
		}
	}
	
	public boolean deleteById(int id) throws BLLException {
		try {
			return utilisateurDAO.deleteById(id);
		} catch (DALException e) {
			throw new BLLException("ERREUR MANAGER class Utilisateur manager /suppression", e);
		}
	}

	public Utilisateur modifier(Utilisateur utilisateur)throws BLLException{
		try {
			utilisateur= utilisateurDAO.update(utilisateur);					
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateur;		
	}
	

}
