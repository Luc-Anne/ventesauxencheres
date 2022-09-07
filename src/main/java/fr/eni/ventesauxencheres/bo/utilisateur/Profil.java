package fr.eni.ventesauxencheres.bo.utilisateur;

import java.time.LocalDateTime;

public abstract class Profil {

	// Attributes
	private int no_profil;
	private String pseudo;
	private String courriel;
	private LocalDateTime dateEnregistrement;
	// Association attributes

	// Constructors
	public Profil() {}

	public Profil(int no_profil, String pseudo, String courriel, LocalDateTime dateEnregistrement) {
		this.no_profil = no_profil;
		this.pseudo = pseudo;
		this.courriel = courriel;
		this.dateEnregistrement = dateEnregistrement;
	}

	public Profil(String pseudo, String courriel) {
		this.pseudo = pseudo;
		this.courriel = courriel;
		this.dateEnregistrement = LocalDateTime.now();
	}

	// Getters and Setters
	public int getNo_profil() {
		return no_profil;
	}

	public void setNo_profil(int no_profil) {
		this.no_profil = no_profil;
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
