package fr.eni.ventesauxencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Retrait;
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
	
	// Règles métiers
	public static final int DEFAULT_CREDIT = 100;
	
	// Validation
	public boolean isValide(Utilisateur utilisateur) {
		return invalidCause(utilisateur).size() == 0 ? true : false;
	}
	
	public List<String> invalidCause(Utilisateur utilisateur)  {
		List<String> invalidCause = new ArrayList<>();
		
		// pseudo
		if (utilisateur.getPseudo() == null ||
			utilisateur.getPseudo() == "") {
			invalidCause.add("utilisateur.pseudo_vide");
		} else {
			if (utilisateur.getPseudo().length() > 30) {
				invalidCause.add("utilisateur.pseudo_tropLong");
			}
			if (utilisateur.getPseudo().contains(" ")) {
				invalidCause.add("utilisateur.pseudo_avecEspace");
			}
		}
		// nom
		if (utilisateur.getNom() == null ||
			utilisateur.getNom() == "") {
			invalidCause.add("utilisateur.nom_vide");
		} else {
			if (utilisateur.getNom().length() > 30) {
				invalidCause.add("utilisateur.nom_tropLong");
			}
		}
		// prenom
		if (utilisateur.getPrenom() == null ||
			utilisateur.getPrenom() == "") {
			invalidCause.add("utilisateur.prenom_vide");
		} else {
			if (utilisateur.getPrenom().length() > 30) {
				invalidCause.add("utilisateur.prenom_tropLong");
			}
		}
		// email
		if (utilisateur.getEmail() == null ||
			utilisateur.getEmail() == "") {
			invalidCause.add("utilisateur.email_vide");
		} else {
			if (utilisateur.getEmail().length() > 20) {
				invalidCause.add("utilisateur.email_tropLong");
			}
			if (! utilisateur.getEmail().contains("@")) {
				invalidCause.add("utilisateur.email_manque@");
			}
		}
		// telephone
		if (utilisateur.getNom().length() > 15) {
			invalidCause.add("utilisateur.telephone_tropLong");
		}
		// rue
		if (utilisateur.getRue() == null ||
			utilisateur.getRue() == "") {
			invalidCause.add("utilisateur.rue_vide");
		} else {
			if (utilisateur.getRue().length() > 30) {
				invalidCause.add("utilisateur.rue_tropLong");
			}
		}
		// codePostal
		if (utilisateur.getCodePostal() == null ||
			utilisateur.getCodePostal() == "") {
			invalidCause.add("utilisateur.codePostal_vide");
		} else {
			if (utilisateur.getCodePostal().length() > 10) {
				invalidCause.add("utilisateur.codePostal_tropLong");
			}
		}
		// ville
		if (utilisateur.getVille() == null ||
			utilisateur.getVille() == "") {
			invalidCause.add("utilisateur.ville_vide");
		} else {
			if (utilisateur.getVille().length() > 30) {
				invalidCause.add("utilisateur.ville_tropLong");
			}			
		}
		// motDePasse
		if (utilisateur.getMotDePasse() == null ||
			utilisateur.getMotDePasse() == "") {
			invalidCause.add("utilisateur.motDePasse_vide");
		} else {
			if (utilisateur.getMotDePasse().length() > 30) {
				invalidCause.add("utilisateur.motDePasse_tropLong");
			}
			if (utilisateur.getMotDePasse().length() < 8) {
				// Un mot de passe est d'au moins 8 caractères
				invalidCause.add("utilisateur.motDePasse_faible");
			}
		}
		
		// credit
		if (utilisateur.getCredit() < 0) {
			invalidCause.add("utilisateur.credit_negatif");
		}
		// administrateur
		// Aucun test car boolean
		
		return invalidCause;
	}
	
	// Méthodes métier basiques
	public Utilisateur save(Utilisateur utilisateur) throws BLLException {
		try {
			if (this.isValide(utilisateur)) {
				return utilisateurDAO.insert(utilisateur);
			} else {
				throw new BLLException("utilisateur invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}
	
	public Utilisateur get(int id) throws BLLException {
		try {
			return utilisateurDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}
	
	public List<Utilisateur> getAll() throws BLLException {
		try {
			return utilisateurDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}
	
	public void modify(Utilisateur utilisateur)throws BLLException{
		try {
			if (this.isValide(utilisateur)) {
				utilisateurDAO.update(utilisateur);
			} else {
				throw new BLLException("utilisateur invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}
	
	public void delete(int id) throws BLLException {
		try {
			utilisateurDAO.delete(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	// Spécifiques
	public Utilisateur connexion(String email, String password) throws BLLException {
		try {
			return utilisateurDAO.connexion(email, password);
		} catch (DALException e) {
			throw new BLLException("ERREUR MANAGER", e);
		}
	}

	public Utilisateur getByPseudo(String pseudo) throws BLLException {
		try {
			return utilisateurDAO.selectByPseudo(pseudo);
		} catch (DALException e) {
			throw new BLLException("ERREUR MANAGER", e);
		}
	}

	

}
