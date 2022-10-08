package fr.eni.ventesauxencheres.bll.dependencies;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.dependencies.Adresse;

public class AdresseMgr {

	private static AdresseMgr adresseManager;

	private AdresseMgr() {}

	public static AdresseMgr getInstance() {
		if (adresseManager == null) {
			adresseManager = new AdresseMgr();
		}
		return adresseManager;
	}

	// Règles métiers
	public static final String DEFAULT_PAYS = "FRANCE";

	// Validation
	public boolean isValide(Adresse adresse) {
		return invalidCause(adresse).size() == 0 ? true : false;
	}

	public List<String> invalidCause(Adresse adresse)  {
		List<String> invalidCause = new ArrayList<>();

		// rue
		if (adresse.getRue() == null ||
			"".equals(adresse.getRue())) {
			invalidCause.add("adresse.rue_vide");
		} else {
			if (adresse.getRue().length() > 100) {
				invalidCause.add("adresse.rue_tropLong");
			}
		}
		// codePostal
		if (adresse.getCodePostal() == null ||
			"".equals(adresse.getCodePostal())) {
			invalidCause.add("adresse.codePostal_vide");
		} else {
			if (adresse.getCodePostal().length() > 15) {
				invalidCause.add("adresse.codePostal_tropLong");
			}
		}
		// ville
		if (adresse.getVille() == null ||
			"".equals(adresse.getVille())) {
			invalidCause.add("adresse.ville_vide");
		} else {
			if (adresse.getVille().length() > 50) {
				invalidCause.add("adresse.ville_tropLong");
			}
		}
		// pays
		if (adresse.getPays() == null ||
			"".equals(adresse.getPays())) {
			invalidCause.add("adresse.pays_vide");
		} else {
			if (adresse.getPays().length() > 50) {
				invalidCause.add("adresse.pays_tropLong");
			}
		}

		return invalidCause;
	}

}
