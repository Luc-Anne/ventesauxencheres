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
	
	// Règles métiers
	public static final int DEFAULT_CREDIT = 100;
	
	// Validation
	public boolean isValide(Utilisateur utilisateur) {
		return invalidCause(utilisateur).size() == 0 ? true : false;
	}
	
	public List<String> invalidCause(Utilisateur utilisateur)  {
		// TODO vérifier que la longueur des chaines de caractères soient bien compatibles avec les contraintes sql
		// TODO vérifier qu'il n'y a pas d'espace à l'intérieur des pseudos
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
	
	// Méthodes métier basiques
	public Utilisateur save(Utilisateur u) throws BLLException {
		try {
			if (this.isValide(u)) {
				return utilisateurDAO.insert(u);
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
	
	// Méthodes métier specifiques
	public Utilisateur connexion(String email, String password) throws BLLException {
		try {
			return utilisateurDAO.selectUtilisateurConnecte(email, password);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}
	
}
