package fr.eni.ventesauxencheres.bo.encheres;

import java.time.LocalDateTime;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;

public class Article {

	// Attributes
	private int no_article;
	private String designation;
	private String description;
	private String etat;
	private LocalDateTime dateEnregistrement;
	// Association attributes
	private Client proprietaire;
	private Categorie categorie;

	// Constructors
	public Article() {}

	public Article(int no_article, String designation, String description, String etat, LocalDateTime dateEnregistrement) {
		this.no_article =  no_article;
		this.designation = designation;
		this.description = description;
		this.etat = etat;
		this.dateEnregistrement = dateEnregistrement;
	}

	public Article(String designation, String description, String etat) {
		this.designation = designation;
		this.description = description;
		this.etat = etat;
		this.dateEnregistrement = LocalDateTime.now();
	}

	// Getters and Setters
	public int getNo_article() {
		return no_article;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public LocalDateTime getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(LocalDateTime dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	public Client getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Client proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	// Other methods

}
