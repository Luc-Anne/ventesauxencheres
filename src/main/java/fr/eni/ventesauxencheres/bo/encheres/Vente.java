package fr.eni.ventesauxencheres.bo.encheres;

import java.time.LocalDateTime;

import fr.eni.ventesauxencheres.bo.Adresse;

public class Vente {

	// Attributes
	private int noVente;
	private float montantDepart;
	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
	private int dateFinDeltaOrigine;
	private LocalDateTime dateChangementProprietaire;
	// Association attributes
	private Article article;
	private Adresse adresseRetrait;

	// Constructors
	public Vente() {}

	public Vente(int noVente, float montantDepart, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, int dateFinEnchereDeltaOrigine, LocalDateTime dateChangementProprietaire) {
		this.noVente = noVente;
		this.montantDepart = montantDepart;
		this.dateDebut = dateDebutEnchere;
		this.dateFin = dateFinEnchere;
		this.dateFinDeltaOrigine = dateFinEnchereDeltaOrigine;
		this.dateChangementProprietaire = dateChangementProprietaire;
	}

	public Vente(float montantDepart, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, int dateFinDeltaOrigine) {
		this.montantDepart = montantDepart;
		this.dateDebut = dateDebutEnchere;
		this.dateFin = dateFinEnchere;
		this.dateFinDeltaOrigine = dateFinDeltaOrigine;
	}
	
	public Vente(float montantDepart, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere) {
		this.montantDepart = montantDepart;
		this.dateDebut = dateDebutEnchere;
		this.dateFin = dateFinEnchere;
		this.dateFinDeltaOrigine = 0;
	}

	// Getters and Setters
	public int getNoVente() {
		return noVente;
	}

	public void setNoVente(int noVente) {
		this.noVente = noVente;
	}

	public float getMontantDepart() {
		return montantDepart;
	}

	public void setMontantDepart(float montantDepart) {
		this.montantDepart = montantDepart;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebutEnchere) {
		this.dateDebut = dateDebutEnchere;
	}

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDateTime dateFinEnchere) {
		this.dateFin = dateFinEnchere;
	}

	public int getDateFinDeltaOrigine() {
		return dateFinDeltaOrigine;
	}

	public void setDateFinDeltaOrigine(int dateFinEnchereDeltaOrigine) {
		this.dateFinDeltaOrigine = dateFinEnchereDeltaOrigine;
	}

	public LocalDateTime getDateChangementProprietaire() {
		return dateChangementProprietaire;
	}

	public void setDateChangementProprietaire(LocalDateTime dateChangementProprietaire) {
		this.dateChangementProprietaire = dateChangementProprietaire;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Adresse getAdresseRetrait() {
		return adresseRetrait;
	}

	public void setAdresseRetrait(Adresse adresseRetrait) {
		this.adresseRetrait = adresseRetrait;
	}

	// Other methods

}
