package fr.eni.ventesauxencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.utilisateur.Profil;

public class ProfilManager {

	private static ProfilManager profilManager;

	private ProfilManager() {}

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
		// email
		if (profil.getCourriel() == null ||
			"".equals(profil.getCourriel())) {
			invalidCause.add("profil.email_vide");
		} else {
			if (profil.getCourriel().length() > 50) {
				invalidCause.add("profil.email_tropLong");
			}
			if (! profil.getCourriel().contains("@")) {
				invalidCause.add("profil.email_manque@");
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

}
