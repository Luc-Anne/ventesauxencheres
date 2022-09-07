package fr.eni.ventesauxencheres.bo.encheres;

import java.time.LocalDateTime;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;

public class Enchere {

	// Attributes
	private LocalDateTime date;
	private float montant;
	// Association attributes
	private Client encherisseur;
	private Vente vente;

	// Constructors
	public Enchere(){}

	public Enchere(LocalDateTime date, float montant){
		this.date = date;
		this.montant = montant;
	}

	// Getters and Setters
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public Client getEncherisseur() {
		return encherisseur;
	}

	public void setEncherisseur(Client encherisseur) {
		this.encherisseur = encherisseur;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

	// Other methods

}
