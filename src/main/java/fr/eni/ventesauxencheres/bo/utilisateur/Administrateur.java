package fr.eni.ventesauxencheres.bo.utilisateur;

import java.time.LocalDateTime;
import java.util.List;

public class Administrateur extends Profil {
	// Attributes
	private int no_administrateur;
	// Association attributes
	private List<Droit> droits;

	// Constructors
	public Administrateur() {}

	public Administrateur(int no_profil, String pseudo, String courriel, Boolean actif, LocalDateTime dateEnregistrement,
			int no_administrateur) {
		super(no_profil, pseudo, courriel, actif, dateEnregistrement);
		this.no_administrateur = no_administrateur;
	}

	public Administrateur(String pseudo, String courriel) {
		super(pseudo, courriel);
	}

	// Getters and Setters
	public int getNo_administrateur() {
		return no_administrateur;
	}

	public void setNo_administrateur(int no_administrateur) {
		this.no_administrateur = no_administrateur;
	}

	public List<Droit> getDroits() {
		return droits;
	}

	public void setDroits(List<Droit> droits) {
		this.droits = droits;
	}

	// Other methods

}
