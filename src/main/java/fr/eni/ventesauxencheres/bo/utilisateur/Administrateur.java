package fr.eni.ventesauxencheres.bo.utilisateur;

import java.time.LocalDateTime;
import java.util.List;

public class Administrateur extends Profil {
	// Attributes
	private int noAdministrateur;
	// Association attributes
	private List<Droit> droits;

	// Constructors
	public Administrateur() {}

	public Administrateur(int noProfil, String pseudo, String courriel, LocalDateTime dateEnregistrement,
			int noAdministrateur) {
		super(noProfil, pseudo, courriel, dateEnregistrement);
		this.noAdministrateur = noAdministrateur;
	}

	public Administrateur(String pseudo, String courriel) {
		super(pseudo, courriel);
	}

	// Getters and Setters
	public int getNoAdministrateur() {
		return noAdministrateur;
	}

	public void setNoAdministrateur(int noAdministrateur) {
		this.noAdministrateur = noAdministrateur;
	}

	public List<Droit> getDroits() {
		return droits;
	}

	public void setDroits(List<Droit> droits) {
		this.droits = droits;
	}

	// Other methods

}
