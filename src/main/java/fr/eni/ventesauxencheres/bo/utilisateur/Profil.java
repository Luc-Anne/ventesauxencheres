package fr.eni.ventesauxencheres.bo.utilisateur;

import java.time.LocalDateTime;

public abstract class Profil {

	// Attributes
	private int noProfil;
	private String pseudo;
	private String courriel;
	private LocalDateTime dateEnregistrement;
	// Association attributes

	// Constructors
	public Profil() {}

	public Profil(int noProfil, String pseudo, String courriel, LocalDateTime dateEnregistrement) {
		this.noProfil = noProfil;
		this.pseudo = pseudo;
		this.courriel = courriel;
		this.dateEnregistrement = dateEnregistrement;
	}

	public Profil(String pseudo, String courriel) {
		this.pseudo = pseudo;
		this.courriel = courriel;
	}

	// Getters and Setters
	public int getNoProfil() {
		return noProfil;
	}

	public void setNoProfil(int noProfil) {
		this.noProfil = noProfil;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public LocalDateTime getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(LocalDateTime dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	// Other methods

}
