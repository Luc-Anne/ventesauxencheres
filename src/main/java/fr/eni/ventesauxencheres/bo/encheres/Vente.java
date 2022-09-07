package fr.eni.ventesauxencheres.bo.encheres;

import java.time.LocalDateTime;

import fr.eni.ventesauxencheres.bo.Adresse;

public class Vente {

	// Attributes
	private int no_vente;
	private int montantDepart;
	private LocalDateTime dateDebutEnchere;
	private LocalDateTime dateFinEnchere;
	private int dateFinEnchereDeltaOrigine;
	private LocalDateTime dateChangementProprietaire;
	// Association attributes
	private Article article;
	private Adresse adresseDeRetrait;

	// Constructors
	public Vente() {}

	public Vente(int no_vente, int montantDepart, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, int dateFinEnchereDeltaOrigine, LocalDateTime dateChangementProprietaire) {
		this.no_vente = no_vente;
		this.montantDepart = montantDepart;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.dateFinEnchereDeltaOrigine = dateFinEnchereDeltaOrigine;
		this.dateChangementProprietaire = dateChangementProprietaire;
	}

	public Vente(int montantDepart, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere) {
		this.montantDepart = montantDepart;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
	}

	// Getters and Setters
	public int getNo_vente() {
		return no_vente;
	}

	public void setNo_vente(int no_vente) {
		this.no_vente = no_vente;
	}

	public int getMontantDepart() {
		return montantDepart;
	}

	public void setMontantDepart(int montantDepart) {
		this.montantDepart = montantDepart;
	}

	public LocalDateTime getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public LocalDateTime getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(LocalDateTime dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	public int getDateFinEnchereDeltaOrigine() {
		return dateFinEnchereDeltaOrigine;
	}

	public void setDateFinEnchereDeltaOrigine(int dateFinEnchereDeltaOrigine) {
		this.dateFinEnchereDeltaOrigine = dateFinEnchereDeltaOrigine;
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

	public Adresse getAdresseDeRetrait() {
		return adresseDeRetrait;
	}

	public void setAdresseDeRetrait(Adresse adresseDeRetrait) {
		this.adresseDeRetrait = adresseDeRetrait;
	}

	// Other methods

}
