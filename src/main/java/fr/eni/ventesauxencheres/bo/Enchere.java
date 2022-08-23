package fr.eni.ventesauxencheres.bo;

import java.time.LocalDateTime;

public class Enchere {

	private LocalDateTime dateEnchere;
	private int montantEnchere;
	// Association
	private Utilisateur encherisseur; // Correspondance SQL
	private Article article; // Correspondance SQL
	
	public Enchere() {}
	
	public Enchere(LocalDateTime dateEnchere, int montantEnchere, Utilisateur encherisseur, Article article) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.encherisseur = encherisseur;
		this.article = article;
	}

	public Enchere(LocalDateTime dateEnchere, int montantEnchere, Utilisateur encherisseur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.encherisseur = encherisseur;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", encherisseur="
				+ encherisseur + ", article=" + article + "]";
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Utilisateur getEncherisseur() {
		return encherisseur;
	}

	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
}
