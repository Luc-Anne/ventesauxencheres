package fr.eni.ventesauxencheres.bll;

import java.util.ArrayList;
import java.util.List;

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
			if (this.isValide(u)) {
				return utilisateurDAO.inscription(u);
			} else {
				throw new BLLException("utilisateur invalide");
			}
		} catch (DALException e) {
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
			if (this.isValide(utilisateur)) {
				utilisateur= utilisateurDAO.update(utilisateur);
			} else {
				throw new BLLException("utilisateur invalide");
			}
		} catch (DALException e) {
			throw new BLLException("utilisateur invalide", e);
		}
		return utilisateur;		
	}
	
	public boolean isValide(Utilisateur utilisateur) {
		return invalidCause(utilisateur).size() == 0 ? true : false;
	}
	
	public List<String> invalidCause(Utilisateur utilisateur)  {
		List<String> invalidCause = new ArrayList<>();
		
		// utilisateur
		if (utilisateur == null) {
			invalidCause.add("utilisateur_null");
			return invalidCause;
		}
		
		// pseudo
		if (utilisateur.getPseudo() == null ||
			utilisateur.getPseudo() == "") {
			invalidCause.add("utilisateur.pseudo_vide");
		}
		// nom
		if (utilisateur.getNom() == null ||
			utilisateur.getNom() == "") {
			invalidCause.add("utilisateur.nom_vide");
		}
		// prenom
		if (utilisateur.getPrenom() == null ||
			utilisateur.getPrenom() == "") {
			invalidCause.add("utilisateur.prenom_vide");
		}
		// email
		if (utilisateur.getEmail() == null ||
			utilisateur.getEmail() == "") {
			invalidCause.add("utilisateur.email_vide");
		}
		if (! utilisateur.getEmail().contains("@")) {
			invalidCause.add("utilisateur.email_manque@");
		}
		// telephone
		// Peut être null (ou "")
		// rue
		if (utilisateur.getRue() == null ||
			utilisateur.getRue() == "") {
			invalidCause.add("utilisateur.rue_vide");
		}
		// codePostal
		if (utilisateur.getCodePostal() == null ||
			utilisateur.getCodePostal() == "") {
			invalidCause.add("utilisateur.codePostal_vide");
		}
		// ville
		if (utilisateur.getVille() == null ||
			utilisateur.getVille() == "") {
			invalidCause.add("utilisateur.ville_vide");
		}
		// motDePasse
		if (utilisateur.getMotDePasse() == null ||
			utilisateur.getMotDePasse() == "") {
			invalidCause.add("utilisateur.motDePasse_vide");
		}
		if (utilisateur.getMotDePasse().length() < 8) {
			// Un mot de passe est d'au moins 8 caractères
			invalidCause.add("utilisateur.motDePasse_faible");
		}
		// credit
		if (utilisateur.getCredit() < 0) {
			invalidCause.add("utilisateur.credit_negatif");
		}
		// administrateur
		// Aucun test car boolean
		
		return invalidCause;
	}

}
