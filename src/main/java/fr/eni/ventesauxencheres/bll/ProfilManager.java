package fr.eni.ventesauxencheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.utilisateur.Profil;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;
import fr.eni.ventesauxencheres.dal.ProfilDAO;

public class ProfilManager {

	private static ProfilManager profilManager;

	private ProfilDAO profilDAO;

	private ProfilManager() {
		this.profilDAO = FactoryDAO.getProfilDAO();
	}

	public static ProfilManager getInstance() {
		if (profilManager == null) {
			profilManager = new ProfilManager();
		}
		return profilManager;
	}

	// Règles métiers

	// Validation
	public boolean isValide(Profil profil) {
		return invalidCause(profil).size() == 0 ? true : false;
	}

	public List<String> invalidCause(Profil profil)  {
		List<String> invalidCause = new ArrayList<>();

		// pseudo
		if (profil.getPseudo() == null ||
			"".equals(profil.getPseudo())) {
			invalidCause.add("profil.pseudo_vide");
		} else {
			if (profil.getPseudo().length() > 50) {
				invalidCause.add("profil.pseudo_tropLong");
			}
			if (profil.getPseudo().contains(" ")) {
				invalidCause.add("profil.pseudo_avecEspace");
			}
		}
		// courriel
		if (profil.getCourriel() == null ||
			"".equals(profil.getCourriel())) {
			invalidCause.add("profil.courriel_vide");
		} else {
			if (profil.getCourriel().length() > 50) {
				invalidCause.add("profil.courriel_tropLong");
			}
			if (! profil.getCourriel().contains("@")) {
				invalidCause.add("profil.courriel_manque@");
			}
		}

		return invalidCause;
	}

	public boolean isValideMotDePasse(String MotDePasse) {
		return invalidCauseMotDePasse(MotDePasse).size() == 0 ? true : false;
	}

	public List<String> invalidCauseMotDePasse(String motDePasse)  {
		List<String> invalidCause = new ArrayList<>();

		if (motDePasse == null ||
			"".equals(motDePasse)) {
			invalidCause.add("client.motDePasse_vide");
		} else {
			if (motDePasse.length() > 30) {
				invalidCause.add("client.motDePasse_tropLong");
			}
			if (motDePasse.length() < 8) {
				// Un mot de passe est d'au moins 8 caractères
				invalidCause.add("client.motDePasse_faible");
			}
		}

		return invalidCause;
	}

	public String typeOfProfil(String email, String motDePasse) throws BLLException {
		try {
			byte[] hashedMotDePasse = hashMotDePasse(motDePasse);
			return profilDAO.typeOfProfil(email, hashedMotDePasse);
		} catch (DALException e) {
			throw new BLLException("ERREUR MANAGER", e);
		}
	}

	public byte[] hashMotDePasse(String motDePasse) throws BLLException {
		byte[] digest;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(motDePasse.getBytes());
			digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new BLLException("Problème lors du hashage du mot de passe.", e);
		}
		return digest;
	}

}
